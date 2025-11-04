package org.stofli.infrastructure.persistence.repository;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.ExcelFilePath;
import org.stofli.domain.model.TseData;
import org.stofli.domain.repository.TseDataRepository;
import org.stofli.infrastructure.configuration.DbUtil;
import org.stofli.infrastructure.excel.core.Excel;
import org.stofli.infrastructure.excel.factory.ExcelReaderFactory;
import org.stofli.infrastructure.persistence.dao.StockCompanyDao;

public class TseDataRepositoryImpl implements TseDataRepository {


    public List<TseData> readTseData(ExcelFilePath excel) {
        List<TseData> list = null;
        Excel<TseData> reader = ExcelReaderFactory.getReader("Tse");
        try {
            list = reader.read(excel.getFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveAll(List<TseData> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            System.out.println("No data to save.");
            return;
        }
        try(Connection conn = DbUtil.getConnection()) {
            StockCompanyDao dao = new StockCompanyDao(conn);
            dao.insertBatch(dataList);
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllRecord() {
        try (Connection conn = DbUtil.getConnection()) {
            StockCompanyDao dao = new StockCompanyDao(conn);
            dao.deleteAllRecord();
            System.out.println("All data deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CompanyCode> getAllCode() {
        List<CompanyCode> codes = null;
        try (Connection conn = DbUtil.getConnection()) {
            StockCompanyDao dao = new StockCompanyDao(conn);
            codes = dao.findAllCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codes;
    }
}
