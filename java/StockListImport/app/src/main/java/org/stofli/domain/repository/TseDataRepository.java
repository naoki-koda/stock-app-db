package org.stofli.domain.repository;

import java.util.List;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.ExcelFilePath;
import org.stofli.domain.model.TseData;

public interface TseDataRepository {
    List<TseData> readTseData(ExcelFilePath path);
    void saveAll(List<TseData> dataList);
    void deleteAllRecord();
    List<CompanyCode> getAllCode();
}
