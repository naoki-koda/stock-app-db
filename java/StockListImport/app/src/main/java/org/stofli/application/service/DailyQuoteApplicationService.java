package org.stofli.application.service;

import java.time.LocalDate;
import java.util.List;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;
import org.stofli.domain.port.MarketDataClient;
import org.stofli.domain.repository.TseDataRepository;

public class DailyQuoteApplicationService {

    private final TseDataRepository tseDataRepository;
    private final MarketDataClient marketDataClient;

    public DailyQuoteApplicationService(TseDataRepository tseDataRepository, MarketDataClient marketDataClient) {
        this.tseDataRepository = tseDataRepository;
        this.marketDataClient = marketDataClient;
    }

    public int importAllCompany(LocalDate asOfDate) throws Exception {
        int registered = 0;
        List<CompanyCode> codes = tseDataRepository.getAllCode();

        for (CompanyCode code : codes) {
            List<DailyQuote> quotes = marketDataClient.fetchDailyQuotes(code.value(), asOfDate);
            for (DailyQuote q : quotes) {
                // upsert/重複スキップは Repository 側の責務で吸収
                // registered += dailyQuotdeRepository.save(q); 
            }
        }
        return registered;
    }
}
