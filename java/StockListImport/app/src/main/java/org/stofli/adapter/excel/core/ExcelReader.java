package org.stofli.adapter.excel.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.stofli.App;

public abstract class ExcelReader<T> implements Excel<T> {
  @Override
  public List<T> read(String filePath) throws IOException {
    List<T> result = new ArrayList<>();
    try(InputStream fis = App.class.getClassLoader().getResourceAsStream(filePath)) {
      Workbook wk = WorkbookFactory.create(fis);
      Sheet sheet = wk.getSheetAt(getSheetNum());
      for(Row row: sheet) {
        if(row == null) {
          continue;
        }
        if (row.getRowNum() < getReadStartRowNum()) {
          continue;
        }
        T record = mapRowToEntity(row);
        if (record != null) {
          result.add(record);
        }
      }
    }
    return result;
  }
  @Override
  public void write(String filePath, List<T> data) throws IOException {
    throw new UnsupportedOperationException("This reader does not support writing");
  }

  protected abstract int getSheetNum();
  protected abstract int getReadStartRowNum();
  protected abstract T mapRowToEntity(Row row);
}
