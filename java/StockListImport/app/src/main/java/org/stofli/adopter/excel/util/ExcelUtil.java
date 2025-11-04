package org.stofli.infrastructure.excel.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


public class ExcelUtil {
  public static String readCell(Cell cell) {
    if(cell == null) return "";
    return switch (cell.getCellType()) {
      case STRING -> cell.getStringCellValue();
      case NUMERIC -> checkNumericAsString(cell);
      case BLANK -> "";
      default -> throw new IllegalArgumentException("Unexpected value: " + cell.getCellType());
    };
  }

  private static String checkNumericAsString(Cell cell) {
    if (DateUtil.isCellDateFormatted(cell)) {
      String date = cell.getDateCellValue().toString();
      return date;
    } else {
      return String.valueOf((int) cell.getNumericCellValue());
    }
  }
}
