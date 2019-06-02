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
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
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

      for (int i = 0; i < sSize; i++) {
        ServiceType v = serviceTypes.get(i);
        serviceTypeStrs[i] = v.getId() + " - " + v.getName();
      }
      XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
      XSSFDataValidationConstraint serviceTypeConstaint = (XSSFDataValidationConstraint) dvHelper
          .createExplicitListConstraint(serviceTypeStrs);
      CellRangeAddressList serviceTypeAddressList = new CellRangeAddressList(1,
          100000, 1, 1);
      XSSFDataValidation serviceTypeValidation = (XSSFDataValidation) dvHelper
          .createValidation(serviceTypeConstaint, serviceTypeAddressList);
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

  @SuppressWarnings("resource")
  public boolean exportInfoRoomHasNoEmail(List<Map<String, String>> rooms) {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet("room");
      XSSFFont font = workbook.createFont();
      font.setBold(true);
      XSSFCellStyle style = workbook.createCellStyle();
      style.setFont(font);

      int rownum = 0;
      Cell cell;
      Row row;
      row = sheet.createRow(rownum);
      sheet.addMergedRegion((new CellRangeAddress(0, 0, 0, 3)));
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("DANH SÁCH CĂN HỘ KHÔNG CÓ THƯ ĐIỆN TỬ");
      XSSFCellStyle cStyle = workbook.createCellStyle();
      cStyle.setFont(font);
      cStyle.setAlignment(HorizontalAlignment.CENTER);
      cell.setCellStyle(cStyle);
      rownum++;
      row = sheet.createRow(rownum);
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("Căn hộ");
      cell.setCellStyle(style);
      cell = row.createCell(1, CellType.STRING);
      cell.setCellValue("Tầng");
      cell.setCellStyle(style);
      cell = row.createCell(2, CellType.STRING);
      cell.setCellValue("Toà nhà");
      cell.setCellStyle(style);

      cell = row.createCell(3, CellType.STRING);
      cell.setCellValue("Chủ hộ");
      cell.setCellStyle(style);

      for (Map<String, String> map : rooms) {
        rownum++;
        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(map.get("ROOM"));

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(map.get("FLOOR"));

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue(map.get("BUILDING"));

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue(map.get("USER"));
      }

      File file = new File("template/" + "RoomNoEmail.xlsx");
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

  @SuppressWarnings("resource")
  public boolean exportStatistic(List<Map<String, Object>> prices,
      List<Map<String, Object>> prices1, List<Map<String, Object>> percent,
      List<Map<String, Object>> percent1) {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet("thu phí");
      XSSFFont font = workbook.createFont();
      font.setBold(true);
      XSSFCellStyle style = workbook.createCellStyle();
      style.setFont(font);
      String month = new SimpleDateFormat("MM-yyyy").format(new Date());
      int rownum = 0;
      Cell cell;
      Row row;
      row = sheet.createRow(rownum);
      sheet.addMergedRegion((new CellRangeAddress(0, 0, 0, 3)));
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("THỐNG KÊ THU PHÍ DỊCH VỤ THÁNG " + month);
      XSSFCellStyle cStyle = workbook.createCellStyle();
      cStyle.setFont(font);
      cStyle.setAlignment(HorizontalAlignment.CENTER);
      cell.setCellStyle(cStyle);
      rownum++;
      row = sheet.createRow(rownum);
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("Dịch vụ");
      cell.setCellStyle(style);
      cell = row.createCell(1, CellType.STRING);
      cell.setCellValue("Tổng tiền đã thanh toán");
      cell.setCellStyle(style);
      cell = row.createCell(2, CellType.STRING);
      cell.setCellValue("Tổng tiền chưa thanh toán");
      cell.setCellStyle(style);

      cell = row.createCell(3, CellType.STRING);
      cell.setCellValue("Tổng tiền");
      cell.setCellStyle(style);
      for (int i = 0; i < prices.size(); i++) {
        rownum++;
        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(prices.get(i).get("name").toString());
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue((Double)prices.get(i).get("price"));
        String formular = "SUM(" + cell.getAddress().toString() + ":";
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue((Double)prices1.get(i).get("price"));
        formular += cell.getAddress().toString() + ")";
        
        cell = row.createCell(3, CellType.FORMULA);
        cell.setCellFormula(formular);
      }
      rownum++;
      row = sheet.createRow(rownum);
      
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("Tổng cộng");
      cell.setCellStyle(style);
      
      
      cell = row.createCell(1, CellType.FORMULA);
      cell.setCellFormula("SUM(B2:B" + rownum + ")");
      
      cell = row.createCell(2, CellType.FORMULA);
      cell.setCellFormula("SUM(C2:C" + rownum + ")");
      
      cell = row.createCell(3, CellType.FORMULA);
      cell.setCellFormula("SUM(D2:D" + rownum + ")");
     
      /////////
      rownum = 0;
      XSSFSheet sheet1 = workbook.createSheet("Tỉ lệ");
      row = sheet1.createRow(rownum);
      sheet1.addMergedRegion((new CellRangeAddress(0, 0, 0, 5)));
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("THỐNG KÊ TỈ LỆ THU PHÍ DỊCH VỤ THÁNG " + month);
      cell.setCellStyle(cStyle);
      rownum++;
      row = sheet1.createRow(rownum);
      cell = row.createCell(0, CellType.STRING);
      cell.setCellValue("Dịch vụ");
      cell.setCellStyle(style);
      cell = row.createCell(1, CellType.STRING);
      cell.setCellValue("Số căn hộ đã thanh toán");
      cell.setCellStyle(style);
      cell = row.createCell(2, CellType.STRING);
      cell.setCellValue("Số căn hộ chưa thanh toán");
      cell.setCellStyle(style);

      cell = row.createCell(3, CellType.STRING);
      cell.setCellValue("Tổng cộng");
      cell.setCellStyle(style);
      
      cell = row.createCell(4, CellType.STRING);
      cell.setCellValue("Tỉ lệ đã thanh toán (%)");
      cell.setCellStyle(style);
      
      cell = row.createCell(5, CellType.STRING);
      cell.setCellValue("Tỉ lệ chưa thanh toán (%)");
      cell.setCellStyle(style);
      for (int i = 0; i < percent.size(); i++) {
        rownum++;
        row = sheet1.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(prices.get(i).get("name").toString());
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(((BigInteger)percent.get(i).get("numRoom")).intValue());
        String formular = "SUM(" + cell.getAddress().toString() + ":";
        String percentFormular1 = cell.getAddress().toString() + " / ";
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue(((BigInteger)percent1.get(i).get("numRoom")).intValue());
        formular += cell.getAddress().toString() + ")";
        String percentFormular2 =  cell.getAddress().toString() + " / ";
        
        cell = row.createCell(3, CellType.FORMULA);
        cell.setCellFormula(formular);
        percentFormular1 +=  cell.getAddress().toString() + " * 100";
        percentFormular1 = " IF(" + cell.getAddress().toString() + "=0,0 ," + percentFormular1 + " )";
        percentFormular2 +=  cell.getAddress().toString() + " * 100";
        percentFormular2 = " IF(" + cell.getAddress().toString() + "=0,0 ," + percentFormular2 + " )";
        
        cell = row.createCell(4, CellType.FORMULA);
        cell.setCellFormula(percentFormular1);
        
        cell = row.createCell(5, CellType.FORMULA);
        cell.setCellFormula(percentFormular2);
      }
      
      File file = new File("template/" + "statistic.xlsx");
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
      System.out.println(e.getMessage());
      return false;
    }
  }
}
