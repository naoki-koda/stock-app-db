package org.stofli.adapter.excel.factory;

import org.stofli.adapter.excel.core.Excel;
import org.stofli.adapter.excel.reader.TseExcelReader;

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
