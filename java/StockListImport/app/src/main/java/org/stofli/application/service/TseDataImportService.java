package org.stofli.application.service;

import java.util.List;

import org.stofli.domain.model.ExcelFilePath;
import org.stofli.domain.model.TseData;
import org.stofli.domain.repository.TseDataRepository;

public class TseDataImportService {
    private final TseDataRepository repository;

    public TseDataImportService(TseDataRepository repository) {
        this.repository = repository;
    }

    public int importFrom(ExcelFilePath path) {
        List<TseData> list = repository.readTseData(path);
        if (list == null || list.isEmpty()) return 0;
            repository.saveAll(list);
        return list.size();
    }
}
