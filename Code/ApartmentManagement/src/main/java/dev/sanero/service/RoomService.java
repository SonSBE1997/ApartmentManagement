/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: RoomService.java
 * Author: Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:52:52 PM
 */

package dev.sanero.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.sanero.entity.Building;
import dev.sanero.entity.Floor;
import dev.sanero.entity.Room;
import dev.sanero.repository.RoomRepository;

/*
 * @author Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:52:52 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class RoomService {
  @Autowired
  RoomRepository repository;
  
  @Autowired
  BuildingService buildingService;
  @Autowired
  FloorService floorService;
  @Autowired
  FileService fileService;
  
  public Room findById(int roomId) {
    try {
      Optional<Room> optional = repository.findById(roomId);
      if(optional.isPresent()) {
        return optional.get();
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<Room> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean changeDisale(int id) {
    try {
      Optional<Room> op = repository.findById(id);
      if(op.isPresent()) {
        Room r = op.get();
        r.setDisable(!r.isDisable());
        repository.save(r);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean save(List<Room> rooms) {
    try {
      for (Room r : rooms) {
        saveRoom(r);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean saveRoom(Room room) {
    try {
        if (room.getId() > 0) {
          Room origin = repository.findById(room.getId()).get();
          room.setHouseholds(origin.getHouseholds());
          room.setBuilding(origin.getBuilding());
          room.setFloor(origin.getFloor());
        }
        repository.save(room);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean update(Room room) {
    try {
        if (room.getId() != 0) {
          Room origin = repository.findById(room.getId()).get();
          room.setHouseholds(origin.getHouseholds());
        }
        repository.saveAndFlush(room);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
 
  @SuppressWarnings("resource")
  public String readFileRoom(MultipartFile file, String fileExtension) {
    try {
      InputStream inputStream = file.getInputStream();
      Workbook workbook = null;
      if ("xlsx".equals(fileExtension)) {
        workbook = new XSSFWorkbook(inputStream);
      } else if ("xls".equals(fileExtension)) {
        workbook = new HSSFWorkbook(inputStream);
      } else {
        return "Tệp tải lên không có định dạng của tệp excel"; // not a type of excel
      }

      Sheet sheet = workbook.getSheetAt(0);
      Row rowFirst = sheet.getRow(0);
      if (fileService.checkRowIsEmpty(rowFirst)) {
        return "Tệp không có dữ liệu"; // File has no data
      }

      if (!checkNotFormatedRoomFile(rowFirst)) {
        return "Không khớp với template"; // Not matching template
      }

      List<Room> rooms = readListRoom(sheet);
      if(rooms.size() == 0) {        
        return "Dữ liệu trên một số trường không khớp"; // read file failed - data missing in field
      }
      if(save(rooms)) {
        return "Import thành công"; // save success
      } else {
        return "Lỗi trong quá trình lưu"; // saving error.
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "Import không thành công";
    }
  }
  
  private List<Room> readListRoom(Sheet sheet) {
    String buildingStr = "";
    String floorStr = "";
    Building building = null;
    Floor floor = null;
    List<Room> rooms = new ArrayList<>();
    int rowCount = sheet.getLastRowNum();
    for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
      Row row = sheet.getRow(rowNum);
      if (fileService.checkRowIsEmpty(row)) {
        continue;
      }
      Room room = new Room();
      int col = 0;
      Iterator<Cell> cellIt = row.cellIterator();
      while (cellIt.hasNext()) {
        col++;
        Cell cell = cellIt.next();
        Object cellValue = fileService.getCellValue(cell);
        if (cellValue == null || cellValue.toString().isEmpty()) {
          continue;
        }

        int columnIndex = cell.getColumnIndex();
        System.out.println(columnIndex);
        String cellData = (String) fileService.getCellValue(cell);
        switch (columnIndex) {
          case 0:
            if(!buildingStr.equalsIgnoreCase(cellData)) {
              buildingStr = cellData;
              building = buildingService.findByName(buildingStr);
              if(building == null) {
                building = new Building();
                building.setName(buildingStr);
                building = buildingService.save(building);
              }
            }
            room.setBuilding(building);
            break;
          case 1:
            if(!floorStr.equalsIgnoreCase(cellData)) {
              floorStr = cellData;
              floor = floorService.findByNameAndBuilding(floorStr, building.getId());
              if(floor == null) {
                floor = new Floor();
                floor.setName(floorStr);
                floor.setBuilding(building);
                floor = floorService.save(floor);
              }
            }  
            room.setFloor(floor);
            break;
          case 2:
            room.setName(cellData);
            break;
          case 3:
            try {
              room.setArea(Double.parseDouble(cellData));
            } catch (Exception e) {
              col--;
            }
            break;
          default:
            break;
        }
      }
      if(col == 4) {
        rooms.add(room);
      }
      col = 0;
    }
    return rooms;
  }

  
  private boolean checkNotFormatedRoomFile(Row row) {
    boolean checkCell0 = "Toà nhà".equals(row.getCell(0).toString());
    boolean checkCell1 = "Tầng".equals(row.getCell(1).toString());
    boolean checkCell2 = "Tên căn hộ".equals(row.getCell(2).toString());
    boolean checkCell3 = "Diện tích".equals(row.getCell(3).toString());
    if (!checkCell0 || !checkCell1 || !checkCell2 || !checkCell3) {
      return false;
    }
    return true;
  }
  
  public Room findByName(String name) {
    try {
      String arr[] = name.split(" - ");
      name = arr[0].trim();
      String floor = arr[1].trim();
      String building = arr[2].trim();
      return repository.findRoomByName(name, floor, building);
    } catch (Exception e) {
      return null;
    }
  }
}
