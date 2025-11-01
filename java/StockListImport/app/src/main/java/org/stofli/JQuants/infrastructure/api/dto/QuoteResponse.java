package org.stofli.jquants.infrastructure.api.dto;

import java.util.List;

import org.stofli.domain.model.DailyQuote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteResponse {
    @JsonProperty("daily_quotes")
    private List<DailyQuote> dailyQuotes;
    
    public List<DailyQuote> getDailyQuotes() {
        return dailyQuotes == null ? List.of() : dailyQuotes;
    }
}
