package org.stofli.domain.repository;

import java.util.List;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;

public interface DailyQuoteRepository {
    void saveAll(List<DailyQuote> dataList);
    void deleteByCode(CompanyCode code);
}
