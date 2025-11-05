package org.stofli.adapter.database.repository;

import java.util.List;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;
import org.stofli.domain.repository.DailyQuoteRepository;

public class DailyQuoteRepositoryImpl implements DailyQuoteRepository {

    @Override
    public int saveAll(List<DailyQuote> dataList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public void deleteByCode(CompanyCode code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByCode'");
    }
    
}
