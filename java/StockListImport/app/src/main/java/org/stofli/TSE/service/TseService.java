package org.stofli.tse.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import org.stofli.excel.core.Excel;
import org.stofli.excel.factory.ExcelReaderFactory;
import org.stofli.tse.StockCompanyDao;
import org.stofli.tse.model.TseData;

public class TseService {

    private final StockCompanyDao dao;
    private Connection conn;

    public TseService(Connection conn) {
        this.conn = conn;
        this.dao = new StockCompanyDao(conn);
    }

    public List<TseData> importFromExcel(String filePath) {
        List<TseData> list = null;
        Excel<TseData> reader = ExcelReaderFactory.getReader("Tse");
        try {
            list = reader.read(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void truncateTable() {
        String sql = "TRUNCATE TABLE company";
        try (var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToDatabase(List<TseData> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            System.out.println("No data to save.");
            return;
        }
        try {
            dao.insertBatch(dataList);
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
