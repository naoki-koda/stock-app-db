package org.stofli.jquants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class QuoteResponse {
    @JsonProperty("daily_quotes")
    private List<DailyQuote> dailyQuotes;
    
    public List<DailyQuote> getDailyQuotes() {
        return dailyQuotes;
    }
}
