package org.stofli.Excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.stofli.TSE.TseHeader;
import org.stofli.App;
import org.stofli.TSE.TseData;
public abstract class Excel {

    public static final int FIRST_SHEET = 0;
    public Workbook workbook;

    public Excel(String filePath){
        InputStream is = App.class.getClassLoader().getResourceAsStream(filePath);
        try {
            this.workbook = WorkbookFactory.create(is);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    abstract public List<TseData> readData();

    public String readCell(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> checkNumeric(cell);
            case BLANK -> "";
            default -> throw new IllegalArgumentException("Unexpected value: " + cell.getCellType());
        };
    }

    private String checkNumeric(Cell cell) {
        if(DateUtil.isCellDateFormatted(cell)){
            String date = cell.getDateCellValue().toString();
            return date;
         }else{
            return String.valueOf((int) cell.getNumericCellValue());
         }
    }
}
