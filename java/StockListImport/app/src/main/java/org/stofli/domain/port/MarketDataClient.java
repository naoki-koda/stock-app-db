package org.stofli.domain.port;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.stofli.domain.model.DailyQuote;

public interface MarketDataClient {
    List<DailyQuote> fetchDailyQuotes(String code, LocalDate date) throws IOException, InterruptedException;
}
