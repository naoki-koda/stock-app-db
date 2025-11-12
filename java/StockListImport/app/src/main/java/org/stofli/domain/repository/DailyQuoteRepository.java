package org.stofli.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;

public interface DailyQuoteRepository {
    int saveAll(List<DailyQuote> dataList);
    void deleteByCode(CompanyCode code);
    boolean exist(CompanyCode code, LocalDate date);
    Optional<CompanyCode> findLastCodeByDate(LocalDate date);
}