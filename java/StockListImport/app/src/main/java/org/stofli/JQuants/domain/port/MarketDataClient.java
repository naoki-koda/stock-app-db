package org.stofli.jquants.domain.port;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.stofli.domain.model.DailyQuote;

/**
 * Port that abstracts the J-Quants daily quote API.
 * Implementations encapsulate protocol and authentication concerns.
 */
public interface MarketDataClient {

    List<DailyQuote> fetchDailyQuotes(String code, LocalDate date) throws IOException, InterruptedException;
}
