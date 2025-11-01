package org.stofli.jquants.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * J-Quants APIの日次株価データ (daily_quotes) の要素DTO。
 * APIレスポンス(JSON)をそのまま受け取る構造体です。
 * 
 * Domain層に依存しない「外部API専用DTO」として設計。
 */
public class DailyQuoteDto {

    /** 日付 */
    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyy-MM-dd") // APIが "2025-10-25" 形式ならOK。
    private String date;

    /** 銘柄コード */
    @JsonProperty("Code")
    private String code;

    /** 始値 */
    @JsonProperty("Open")
    private Double open;

    /** 高値 */
    @JsonProperty("High")
    private Double high;

    /** 安値 */
    @JsonProperty("Low")
    private Double low;

    /** 終値 */
    @JsonProperty("Close")
    private Double close;

    /** 値幅上限 */
    @JsonProperty("UpperLimit")
    private Double upperLimit;

    /** 値幅下限 */
    @JsonProperty("LowerLimit")
    private Double lowerLimit;

    /** 出来高 */
    @JsonProperty("Volume")
    private Double volume;

    /** 売買代金 */
    @JsonProperty("TurnoverValue")
    private Double turnoverValue;

    /** 補正係数 */
    @JsonProperty("AdjustmentFactor")
    private Double adjustmentFactor;

    /** 補正後始値 */
    @JsonProperty("AdjustmentOpen")
    private Double adjustmentOpen;

    /** 補正後高値 */
    @JsonProperty("AdjustmentHigh")
    private Double adjustmentHigh;

    /** 補正後安値 */
    @JsonProperty("AdjustmentLow")
    private Double adjustmentLow;

    /** 補正後終値 */
    @JsonProperty("AdjustmentClose")
    private Double adjustmentClose;

    /** 補正後出来高 */
    @JsonProperty("AdjustmentVolume")
    private Double adjustmentVolume;

    // ==== Getters ====

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public Double getOpen() {
        return open;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getClose() {
        return close;
    }

    public Double getUpperLimit() {
        return upperLimit;
    }

    public Double getLowerLimit() {
        return lowerLimit;
    }

    public Double getVolume() {
        return volume;
    }

    public Double getTurnoverValue() {
        return turnoverValue;
    }

    public Double getAdjustmentFactor() {
        return adjustmentFactor;
    }

    public Double getAdjustmentOpen() {
        return adjustmentOpen;
    }

    public Double getAdjustmentHigh() {
        return adjustmentHigh;
    }

    public Double getAdjustmentLow() {
        return adjustmentLow;
    }

    public Double getAdjustmentClose() {
        return adjustmentClose;
    }

    public Double getAdjustmentVolume() {
        return adjustmentVolume;
    }

    @Override
    public String toString() {
        return "DailyQuoteDto[" +
                "date=" + date +
                ", code='" + code + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", upperLimit=" + upperLimit +
                ", lowerLimit=" + lowerLimit +
                ", volume=" + volume +
                ", turnoverValue=" + turnoverValue +
                ']';
    }
}