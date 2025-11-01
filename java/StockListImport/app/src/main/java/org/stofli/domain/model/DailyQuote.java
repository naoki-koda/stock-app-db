package org.stofli.domain.model;

import java.util.Objects;

/**
 * ドメイン層の「日次株価」エンティティ（または値オブジェクト）。
 * 外部APIやJSON形式に依存しない純粋なモデル。
 */
public class DailyQuote {

    private final String date;
    private final String code;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final Double upperLimit;
    private final Double lowerLimit;
    private final double volume;
    private final double turnoverValue;
    private final double adjustmentFactor;
    private final double adjustmentOpen;
    private final double adjustmentHigh;
    private final double adjustmentLow;
    private final double adjustmentClose;
    private final double adjustmentVolume;

    public DailyQuote(
        String date,
        String code,
        double open,
        double high,
        double low,
        double close,
        Double upperLimit,
        Double lowerLimit,
        double volume,
        double turnoverValue,
        double adjustmentFactor,
        double adjustmentOpen,
        double adjustmentHigh,
        double adjustmentLow,
        double adjustmentClose,
        double adjustmentVolume
    ) {
        this.date = date.toString();
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

    public String getDate() { return date; }
    public String getCode() { return code; }
    public double getOpen() { return open; }
    public double getHigh() { return high; }
    public double getLow() { return low; }
    public double getClose() { return close; }
    public Double getUpperLimit() { return upperLimit; }
    public Double getLowerLimit() { return lowerLimit; }
    public double getVolume() { return volume; }
    public double getTurnoverValue() { return turnoverValue; }
    public double getAdjustmentFactor() { return adjustmentFactor; }
    public double getAdjustmentOpen() { return adjustmentOpen; }
    public double getAdjustmentHigh() { return adjustmentHigh; }
    public double getAdjustmentLow() { return adjustmentLow; }
    public double getAdjustmentClose() { return adjustmentClose; }
    public double getAdjustmentVolume() { return adjustmentVolume; }

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
