package org.stofli.adopter.webapi.jquants.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteResponseDto {
  @JsonProperty("daily_quotes")
  private List<DailyQuoteDto> dailyQuotes;
  public List<DailyQuoteDto> getDailyQuotes() {
    return dailyQuotes;
  }
}