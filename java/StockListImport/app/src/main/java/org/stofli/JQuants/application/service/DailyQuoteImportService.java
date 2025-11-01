package org.stofli.jquants.application.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.stofli.domain.model.DailyQuote;
import org.stofli.jquants.domain.port.CompanyCodeRepository;
import org.stofli.jquants.domain.port.MarketDataClient;

/**
 * Application service that coordinates fetching J-Quants quotes for every listed company.
 * Acts as a Facade over the underlying ports.
 */
public class DailyQuoteImportService {

    private final CompanyCodeRepository companyCodeRepository;
    private final MarketDataClient marketDataClient;

    public DailyQuoteImportService(CompanyCodeRepository companyCodeRepository, MarketDataClient marketDataClient) {
        this.companyCodeRepository = companyCodeRepository;
        this.marketDataClient = marketDataClient;
    }

    public List<DailyQuote> importAll(LocalDate asOfDate) throws SQLException, IOException, InterruptedException {
        List<String> companyCodes = companyCodeRepository.findAllCodes();
        List<DailyQuote> quotes = new ArrayList<>();

        for (String code : companyCodes) {
            quotes.addAll(marketDataClient.fetchDailyQuotes(code, asOfDate));
        }
        return quotes;
    }
}
