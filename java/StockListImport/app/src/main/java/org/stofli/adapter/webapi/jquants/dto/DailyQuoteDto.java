package org.stofli.adapter.webapi.jquants.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyQuoteDto {

    /** 日付 (例: 2023-03-24) */
    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;

    /** 銘柄コード（J-Quants形式: 5桁など） */
    @JsonProperty("Code")
    private String code;

    /** 始値 */
    @JsonProperty("Open")
    private BigDecimal open;

    /** 高値 */
    @JsonProperty("High")
    private BigDecimal high;

    /** 安値 */
    @JsonProperty("Low")
    private BigDecimal low;

    /** 終値 */
    @JsonProperty("Close")
    private BigDecimal close;

    /** 値幅上限（"0" など文字列でも BigDecimal に自動変換される） */
    @JsonProperty("UpperLimit")
    private BigDecimal upperLimit;

    /** 値幅下限 */
    @JsonProperty("LowerLimit")
    private BigDecimal lowerLimit;

    /** 出来高 */
    @JsonProperty("Volume")
    private BigDecimal volume;

    /** 売買代金 */
    @JsonProperty("TurnoverValue")
    private BigDecimal turnoverValue;

    /** 補正係数 */
    @JsonProperty("AdjustmentFactor")
    private BigDecimal adjustmentFactor;

    /** 補正後始値 */
    @JsonProperty("AdjustmentOpen")
    private BigDecimal adjustmentOpen;

    /** 補正後高値 */
    @JsonProperty("AdjustmentHigh")
    private BigDecimal adjustmentHigh;

    /** 補正後安値 */
    @JsonProperty("AdjustmentLow")
    private BigDecimal adjustmentLow;

    /** 補正後終値 */
    @JsonProperty("AdjustmentClose")
    private BigDecimal adjustmentClose;

    /** 補正後出来高 */
    @JsonProperty("AdjustmentVolume")
    private BigDecimal adjustmentVolume;

    /** Jackson 用デフォルトコンストラクタ */
    public DailyQuoteDto() {}

    // ===== Getters =====

    public String getDate() { return date; }
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