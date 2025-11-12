package org.stofli.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;
import org.stofli.domain.port.MarketDataClient;
import org.stofli.domain.repository.DailyQuoteRepository;
import org.stofli.domain.repository.TseDataRepository;

import com.google.common.util.concurrent.RateLimiter;

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
        Optional<CompanyCode> lastCode = dailyQuotdeRepository.findLastCodeByDate(asOfDate);
        var limiter = RateLimiter.create(3.0);
        boolean afterLast = false;
        for (CompanyCode code : codes) {
            if(!lastCode.isEmpty()) {
                if (!(afterLast)) {
                    if (code.equals(lastCode.get())) {
                        afterLast = true;
                    }
                    continue;
                }
            }
            limiter.acquire();
            Thread.sleep(ThreadLocalRandom.current().nextInt(50, 120));
            List<DailyQuote> quotes = marketDataClient.fetchDailyQuotes(code.value(), asOfDate);
            registered = dailyQuotdeRepository.saveAll(quotes); 
        }
        return registered;
    }
}
