/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: ServiceRepository.java
 * Author: Sanero.
 * Created date: May 11, 2019
 * Created time: 2:00:26 PM
 */

package dev.sanero.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import dev.sanero.entity.Employee;
import dev.sanero.entity.HouseHold;
import dev.sanero.entity.Room;
import dev.sanero.entity.Service;
import dev.sanero.entity.ServiceType;
import dev.sanero.entity.User;
import dev.sanero.repository.ServiceRepository;
import dev.sanero.repository.ServiceTypeRepository;

/*
 * @author Sanero.
 * Created date: May 11, 2019
 * Created time: 2:00:26 PM
 * Description: TODO - 
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceService {
  @Autowired
  ServiceTypeRepository typeRepository;

  @Autowired
  ServiceRepository repository;
  
  @Autowired
  EmployeeService empService;

  @Autowired
  MailService mailService;
  
  @Autowired
  FileService fileService;
  
  @Autowired
  RoomService roomService;
  
  @Autowired
  UserService userService;
  
  @Autowired 
  HouseholdService householdService;
  
  public List<ServiceType> findAllType() {
    try {
      return typeRepository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<ServiceType> findAllTypeFixed() {
    try {
      return typeRepository.findListServiceTypeIsFixed();
    } catch (Exception e) {
      return null;
    }
  }
  
  
  public List<Service> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean save(Service s) {
    try {
      if (s.getId() > 0) {
        s.setServiceType(s.getServiceType());
        s.setRoom(s.getRoom());
        s.setEmployee(s.getEmployee());
      }
      s.setEmployee(empService.findById(s.getEmployee().getId()));
      s = repository.save(s);
      if (s.getId() > 0) {
        return true;
      } 
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean saveType(ServiceType t) {
    try {
      t = typeRepository.save(t);
      if (t.getId() > 0) {
        return true;
      } 
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean deleteType(int id) {
    try {
      typeRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public Service findById(int id) {
    return repository.findById(id).get();
  }
  
  public boolean remind(Service s) {
    try {
      Room r = s.getRoom();
      if (r.getHousehold() == 0) return false;
      HouseHold h = householdService.findById(r.getHousehold());
      if (h.getUserId() == 0) return false;
      User u = userService.findById(h.getUserId());
      String mailTo = u.getEmail();
      if ("".equals(mailTo)) 
         return false;
      mailService.sendMail(mailTo, mailService.getInvoiceString(s, u.getName()).toString(), "Nhắc nhở đóng phí");
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  @SuppressWarnings("resource")
  public String readServiceFromFile(MultipartFile file, String fileExtension) {
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

      if (!checkNotFormatedFile(rowFirst)) {
        return "Không khớp với template"; // Not matching template
      }

      List<Service> services = readDataFromFile(sheet);
      if(services.size() == 0) {        
        return "Dữ liệu trên một số trường không khớp"; // read file failed - data missing in field
      }
      if(saveService(services)) {
        return "Import thành công"; // save success
      } else {
        return "Lỗi trong quá trình lưu"; // saving error.
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "Import không thành công";
    }
  }
  
  public boolean saveService(List<Service> lst) {
     try {
       for (int i = 0; i < lst.size(); i++) {
         save(lst.get(i));
       }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  private List<Service> readDataFromFile(Sheet sheet) {
    List<Service> services = new ArrayList<>();
    Employee emp = empService.findById(1);
    int rowCount = sheet.getLastRowNum();
    for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
      Row row = sheet.getRow(rowNum);
      if (fileService.checkRowIsEmpty(row)) {
        continue;
      }
     
      Service s = new Service();
      int col = 0;
      double newIndex = 0;
      double oldIndex = 0;
      boolean check = true;
      Iterator<Cell> cellIt = row.cellIterator();
      while (cellIt.hasNext()) {
        col++;
        Cell cell = cellIt.next();
        Object cellValue = fileService.getCellValue(cell);
        if (cellValue == null || cellValue.toString().isEmpty()) {
          continue;
        }

        int columnIndex = cell.getColumnIndex();
        String cellData = (String) fileService.getCellValue(cell);
        switch (columnIndex) {
          case 0:
            Room r = roomService.findByName(cellData);
            if (r == null) {
              check = false;
            } else {
              s.setRoom(r);
            }
            break;
          case 1:
            try {
              ServiceType type = typeRepository.findById(Integer.parseInt(cellData.split(" - ")[0])).get();
              s.setServiceType(type);
              s.setIncrease(type.getIncrease());
            } catch (Exception e) {
              check = false;
            }
            break;
          case 2:
            s.setCollectMonth(cellData);
            break;
          case 3:
            try {
              s.setPrice(Double.parseDouble(cellData));
            } catch (Exception e) {
              check = false;
            }
            break;
          case 4:
            s.setDescription(cellData);
            break;
          case 5:
            try {
              oldIndex = Double.parseDouble(cellData);
            } catch (Exception e) {
              check = false;
            }
            break;
          case 6:
            try {
              newIndex = Double.parseDouble(cellData);
            } catch (Exception e) { 
              check = false;
            }
            break;
          default:
            break;
        }
      }
      if((col == 7 || col == 5 ) && check) {
        if (oldIndex != 0 || newIndex != 0 && newIndex > oldIndex) {
              s.setDetail(oldIndex + "-" + newIndex);
         }
        s.setEmployee(emp);
        services.add(s);
      }
      col = 0;
      oldIndex = 0;
      newIndex = 0;
    }
    return services;
  }

  
  private boolean checkNotFormatedFile(Row row) {
    boolean checkCell0 = "Căn hộ".equals(row.getCell(0).toString());
    boolean checkCell1 = "Loại dịch vụ".equals(row.getCell(1).toString());
    boolean checkCell2 = "Tháng".equals(row.getCell(2).toString());
    boolean checkCell3 = "Tổng tiền".equals(row.getCell(3).toString());
    boolean checkCell4 = "Mô tả".equals(row.getCell(4).toString());
    boolean checkCell5 = "Chỉ số cũ".equals(row.getCell(5).toString());
    boolean checkCell6 = "Chỉ số mới".equals(row.getCell(6).toString());
    if (!checkCell0 || !checkCell1 || !checkCell2 || !checkCell3 || !checkCell4 || !checkCell5 || !checkCell6) {
      return false;
    }
    return true;
  }
  
  public List<Object> paidByMonthAndType(String month, int type) {
    return repository.paidByMonthAndType(month, type);
  }
  
  public List<Object> pricePaidByMonthAndType(String month, int type) {
    return repository.pricePaidByMonthAndType(month, type);
  }
  
  public List<Object> paidByMonth(String month, int paid) {
    return repository.paidByMonth(month, paid);
  }
  
  public List<Object> pricePaidByMonth(String month, int paid) {
    return repository.pricePaidByMonth(month, paid);
  }
  
  public String generateServiceFixed(Employee emp) {
    try {
      String month = new SimpleDateFormat("MM-yyyy").format(new Date());
      List<ServiceType> typesFixed = typeRepository.findListServiceTypeIsFixed();
      int generate = 0;
      for (int i = 0; i < typesFixed.size(); i++) {
        ServiceType serviceType = typesFixed.get(i);
        int typeId = serviceType.getId();
        long isGenerated = repository.isGeneratedFixedService(month, typeId);
        if (isGenerated > 0)
           continue;
        List<Room> generatedList = repository.listGeneratedFixedService(month, typeId);
        List<Room> roomsIsLive = roomService.findRoomsIsLive();
        for (int j = 0; j < generatedList.size(); j++) {
            Room r = generatedList.get(j);
            if (roomsIsLive.contains(r)) {
              roomsIsLive.remove(r);
            }
        }
        
        for (int j = 0; j < roomsIsLive.size(); j++) {
           Service service = new Service();
           service.setEmployee(emp);
           service.setCollectMonth(month);
           service.setDescription("Phí cố định tháng " + month);
           service.setServiceType(serviceType);
           service.setRoom(roomsIsLive.get(j));
           service.setPrice(serviceType.getPrice());
           save(service);
        }
        
        generate++;
      }
      if (generate == 0) 
        return "generated";
      return "success";
    } catch (Exception e) {
      return "error";
    }
  }
  
  public boolean notifyAllRoom(int empId) {
    try {
      String empName = empService.findById(empId).getName();
      String month = new SimpleDateFormat("MM-yyyy").format(new Date());
      List<Service> lst = repository.listServiceIsNotPay(month);
      StringBuilder builder = new StringBuilder();
      int roomId = 0;
      String mailTo = "";
      double total = 0;
      mailService.authorization();
      for (Service s : lst) {
        int rId = s.getRoom().getId();
        if (roomId != rId) {
          if (builder.length() > 0) {
            builder.append(mailService.getInvoiceFooter(empName, total));
            mailService.send(mailTo, builder.toString(), "Thông báo đóng phí");
          } 
          
          roomId = rId;
          Room r = s.getRoom();
          HouseHold h = householdService.findById(r.getHousehold());
          User u = userService.findById(h.getUserId());
          mailTo = u.getEmail();
          builder = new StringBuilder();
          builder.append(mailService.getInvoiceHeader(s, u.getName()));
          total = s.getPrice();
          builder.append(mailService.getInvoiceBody(s));
        } else {
          total += s.getPrice();
          builder.append(mailService.getInvoiceBody(s));
        }
      }
      
      if (!("".equals(mailTo)) && builder.length() > 0) {
        builder.append(mailService.getInvoiceFooter(empName, total));
        mailService.send(mailTo, builder.toString(), "Thông báo đóng phí");
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
