/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: FileServie.java
 * Author: Sanero.
 * Created date: Mar 25, 2019
 * Created time: 8:29:17 PM
 */

package dev.sanero.service;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 8:29:17 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class FileService {
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
}
