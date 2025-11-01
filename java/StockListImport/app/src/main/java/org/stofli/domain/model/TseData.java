package org.stofli.domain.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 東証一部情報企業を表すドメインモデル。
 */
public final class TseData {

    private final LocalDate date;
    private final CompanyCode code;
    private final String name;
    private final int marketId;

    public TseData(LocalDate date, CompanyCode code, String name, int marketId) {
        if (code == null) {
            throw new IllegalArgumentException("銘柄コードは必須です。");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("銘柄名は必須です。");
        }
        if (marketId < 0) {
            throw new IllegalArgumentException("marketIdは0以上である必要があります。");
        }
        this.date = date;
        this.code = code;
        this.name = name;
        this.marketId = marketId;
    }
    public LocalDate getDate() {
        return date;
    }

    public CompanyCode getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getMarketId() {
        return marketId;
    }

    public boolean isMainMarket() {
        return marketId == 1; // 例：1 が東証プライムを意味する場合
    }

    public String formatDisplayName() {
        return "%s (%s)".formatted(name, code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TseData)) return false;
        TseData other = (TseData) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "TseData[date=%s, code=%s, name=%s, marketId=%d]"
                .formatted(date, code, name, marketId);
    }
}