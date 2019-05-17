/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: FileServie.java
 * Author: Sanero.
 * Created date: Mar 25, 2019
 * Created time: 8:29:17 PM
 */

package dev.sanero.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.ServiceType;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 8:29:17 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class FileService {
  @Autowired
  BuildingService buildingService;
  
  @Autowired
  RoomService roomService;
  
  @Autowired
  ServiceService serviceService;
  
  public boolean checkRowIsEmpty(Row row) {
    try {
      if (row == null || row.getLastCellNum() <= 0) {
        return true;
      }
      Cell cell = row.getCell((int) row.getFirstCellNum());
      if (cell == null
          || "".equals(cell.getRichStringCellValue().getString())) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return true;
    }
  }

  public Object getCellValue(Cell cell) {
    CellType cellType = cell.getCellTypeEnum();
    Object cellValue = null;
    switch (cellType) {
      case BOOLEAN:
        cellValue = cell.getBooleanCellValue() + "";
        break;
      case FORMULA:
//        Workbook workbook = cell.getSheet().getWorkbook();
//        FormulaEvaluator evaluator = workbook.getCreationHelper()
//            .createFormulaEvaluator();
//        cellValue = evaluator.evaluate(cell).getNumberValue();
        break;
      case NUMERIC:
        cellValue = cell.getNumericCellValue() + "";
        break;
      case STRING:
        cellValue = cell.getStringCellValue();
        break;
      case _NONE:
      case BLANK:
      case ERROR:
        break;
      default:
        break;
    }

    return cellValue;
  }
  
  @SuppressWarnings("resource")
  public boolean genServiceExcel() {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet("service");
      XSSFFont font = workbook.createFont();
      font.setBold(true);
      XSSFCellStyle style = workbook.createCellStyle();
      style.setFont(font);
     
      List<ServiceType> serviceTypes = serviceService.findAllType();
      int sSize = serviceTypes.size();
      String[] serviceTypeStrs = new String[sSize];
      
      for(int i = 0; i < sSize; i++) {
        ServiceType v = serviceTypes.get(i);
        serviceTypeStrs[i] = v.getId() + " - " + v.getName();
      }
      XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
      XSSFDataValidationConstraint serviceTypeConstaint = 
          (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(serviceTypeStrs);
      CellRangeAddressList serviceTypeAddressList = new CellRangeAddressList(1, 100000, 1, 1);
      XSSFDataValidation serviceTypeValidation = (XSSFDataValidation)dvHelper.createValidation(serviceTypeConstaint, serviceTypeAddressList);
      serviceTypeValidation.setShowErrorBox(true);
      sheet.addValidationData(serviceTypeValidation);
      int rownum = 0;
      Cell cell;
      Row row;
      row = sheet.createRow(rownum);
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("Căn hộ");
      cell.setCellStyle(style);
      cell = row.createCell(1, CellType.STRING);
      cell.setCellValue("Loại dịch vụ");
      cell.setCellStyle(style);
      cell = row.createCell(2, CellType.STRING);
      cell.setCellValue("Tháng");
      cell.setCellStyle(style);
      cell = row.createCell(3, CellType.STRING);
      cell.setCellValue("Tổng tiền");
      cell.setCellStyle(style);
      cell = row.createCell(4, CellType.STRING);
      cell.setCellValue("Mô tả");
      cell.setCellStyle(style);
      cell = row.createCell(5, CellType.STRING);
      cell.setCellValue("Chỉ số cũ");
      cell.setCellStyle(style);
      
      cell = row.createCell(6, CellType.STRING);
      cell.setCellValue("Chỉ số mới");
      cell.setCellStyle(style);
      
      // example: row 1
      rownum++;
      row = sheet.createRow(rownum);
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("201 - Tầng 2 - A1");
      cell = row.createCell(1, CellType.STRING);
      cell.setCellValue(serviceTypeStrs[0]);
      cell = row.createCell(2, CellType.STRING);
      cell.setCellValue("05-2019");
      cell = row.createCell(3, CellType.NUMERIC);
      cell.setCellValue(200000);
      cell = row.createCell(4, CellType.STRING);
      cell.setCellValue("Thu tiền tháng 5");
      cell = row.createCell(5, CellType.NUMERIC);
      cell.setCellValue(20);
      cell = row.createCell(6, CellType.NUMERIC);
      cell.setCellValue(40);
      rownum++;
      row = sheet.createRow(rownum);
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("202 - Tầng 2 - A1");
      cell = row.createCell(1, CellType.STRING);
      cell.setCellValue(serviceTypeStrs[3]);
      cell = row.createCell(2, CellType.STRING);
      cell.setCellValue("05-2019");
      cell = row.createCell(3, CellType.NUMERIC);
      cell.setCellValue(150000);
      cell = row.createCell(4, CellType.STRING);
      cell.setCellValue("Thu tiền tháng 5");
      
      File file = new File("template/" + "Service.xlsx");
      file.getParentFile().mkdirs();
      
      FileOutputStream outFile;
      try {
        outFile = new FileOutputStream(file);
        workbook.write(outFile);
        return true;
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }
}
