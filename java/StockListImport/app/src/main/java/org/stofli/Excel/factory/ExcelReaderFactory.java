package org.stofli.excel.factory;

import org.stofli.excel.core.Excel;
import org.stofli.excel.reader.TseExcelReader;

public class ExcelReaderFactory {
  @SuppressWarnings("unchecked")
  public static <T> Excel<T> getReader(String excelType) {
    switch (excelType) {
      case "Tse":
        return (Excel<T>) new TseExcelReader();
      default:
        throw new IllegalArgumentException("Unknown Excel :" + excelType);
    }
  }

}
