package org.stofli.application.service;

import java.time.LocalDate;
import java.util.List;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;
import org.stofli.domain.port.MarketDataClient;
import org.stofli.domain.repository.DailyQuoteRepository;
import org.stofli.domain.repository.TseDataRepository;

public class DailyQuoteApplicationService {

    private final TseDataRepository tseDataRepository;
    private final DailyQuoteRepository dailyQuotdeRepository;
    private final MarketDataClient marketDataClient;

    public DailyQuoteApplicationService(TseDataRepository tseDataRepository, DailyQuoteRepository dailyQuotdeRepository, MarketDataClient marketDataClient) {
        this.tseDataRepository = tseDataRepository;
        this.dailyQuotdeRepository = dailyQuotdeRepository;
        this.marketDataClient = marketDataClient;
    }

    public int importAllCompany(LocalDate asOfDate) throws Exception {
        int registered = 0;
        List<CompanyCode> codes = tseDataRepository.getAllCode();

        for (CompanyCode code : codes) {
            List<DailyQuote> quotes = marketDataClient.fetchDailyQuotes(code.value(), asOfDate);
            registered = dailyQuotdeRepository.saveAll(quotes); 
        }
        return registered;
    }
}
