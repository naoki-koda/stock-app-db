package org.stofli.JQuants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DailyQuote(
    @JsonProperty("Date") String date,
    @JsonProperty("Code") String code,
    @JsonProperty("Open") double open,
    @JsonProperty("High") double high,
    @JsonProperty("Low") double low,
    @JsonProperty("Close") double close,
    @JsonProperty("UpperLimit") String upperLimit,
    @JsonProperty("LowerLimit") String lowerLimit,
    @JsonProperty("Volume") double volume,
    @JsonProperty("TurnoverValue") double turnoverValue,
    @JsonProperty("AdjustmentFactor") double adjustmentFactor,
    @JsonProperty("AdjustmentOpen") double adjustmentOpen,
    @JsonProperty("AdjustmentHigh") double adjustmentHigh,
    @JsonProperty("AdjustmentLow") double adjustmentLow,
    @JsonProperty("AdjustmentClose") double adjustmentClose,
    @JsonProperty("AdjustmentVolume") double adjustmentVolume
) {}
