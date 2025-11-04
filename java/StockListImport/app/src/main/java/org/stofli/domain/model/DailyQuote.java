package org.stofli.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * ドメイン層の「日次株価」エンティティ（または値オブジェクト）。
 * 外部APIやJSON形式に依存しない純粋なモデル。
 */
public class DailyQuote {

    private final LocalDate date;
    private final String code;
    private final BigDecimal open;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal close;
    private final BigDecimal upperLimit;
    private final BigDecimal lowerLimit;
    private final BigDecimal volume;
    private final BigDecimal turnoverValue;
    private final BigDecimal adjustmentFactor;
    private final BigDecimal adjustmentOpen;
    private final BigDecimal adjustmentHigh;
    private final BigDecimal adjustmentLow;
    private final BigDecimal adjustmentClose;
    private final BigDecimal adjustmentVolume;

    public DailyQuote(
        LocalDate date,
        String code,
        BigDecimal open,
        BigDecimal high,
        BigDecimal low,
        BigDecimal close,
        BigDecimal upperLimit,
        BigDecimal lowerLimit,
        BigDecimal volume,
        BigDecimal turnoverValue,
        BigDecimal adjustmentFactor,
        BigDecimal adjustmentOpen,
        BigDecimal adjustmentHigh,
        BigDecimal adjustmentLow,
        BigDecimal adjustmentClose,
        BigDecimal adjustmentVolume
    ) {
        this.date = date;
        this.code = Objects.requireNonNull(code);
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.volume = volume;
        this.turnoverValue = turnoverValue;
        this.adjustmentFactor = adjustmentFactor;
        this.adjustmentOpen = adjustmentOpen;
        this.adjustmentHigh = adjustmentHigh;
        this.adjustmentLow = adjustmentLow;
        this.adjustmentClose = adjustmentClose;
        this.adjustmentVolume = adjustmentVolume;
    }

    public LocalDate getDate() { return date; }
    public String getCode() { return code; }
    public BigDecimal getOpen() { return open; }
    public BigDecimal getHigh() { return high; }
    public BigDecimal getLow() { return low; }
    public BigDecimal getClose() { return close; }
    public BigDecimal getUpperLimit() { return upperLimit; }
    public BigDecimal getLowerLimit() { return lowerLimit; }
    public BigDecimal getVolume() { return volume; }
    public BigDecimal getTurnoverValue() { return turnoverValue; }
    public BigDecimal getAdjustmentFactor() { return adjustmentFactor; }
    public BigDecimal getAdjustmentOpen() { return adjustmentOpen; }
    public BigDecimal getAdjustmentHigh() { return adjustmentHigh; }
    public BigDecimal getAdjustmentLow() { return adjustmentLow; }
    public BigDecimal getAdjustmentClose() { return adjustmentClose; }
    public BigDecimal getAdjustmentVolume() { return adjustmentVolume; }

    @Override
    public String toString() {
        return "DailyQuote[" +
                "date=" + date +
                ", code='" + code + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ']';
    }
}
