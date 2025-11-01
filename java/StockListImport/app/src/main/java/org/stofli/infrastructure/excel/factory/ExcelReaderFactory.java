package org.stofli.infrastructure.excel.factory;

import org.stofli.infrastructure.excel.core.Excel;
import org.stofli.infrastructure.excel.reader.TseExcelReader;

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
