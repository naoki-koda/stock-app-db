package org.stofli.jquants.mapper;

import java.util.List;

import org.stofli.domain.model.DailyQuote;
import org.stofli.jquants.dto.DailyQuoteDto;

public final class QuoteMapper {
    private QuoteMapper() {}

    public static List<DailyQuote> toDomain(List<DailyQuoteDto> dtoList) {
        if (dtoList == null) return List.of();
        return dtoList.stream()
            .map(QuoteMapper::toDomain)
            .toList();
    }

    public static DailyQuote toDomain(DailyQuoteDto dto) {
        return new DailyQuote(
            dto.getDate(),
            dto.getCode(),
            safeDouble(dto.getOpen()),
            safeDouble(dto.getHigh()),
            safeDouble(dto.getLow()),
            safeDouble(dto.getClose()),
            dto.getUpperLimit(),
            dto.getLowerLimit(),
            safeDouble(dto.getVolume()),
            safeDouble(dto.getTurnoverValue()),
            safeDouble(dto.getAdjustmentFactor()),
            safeDouble(dto.getAdjustmentOpen()),
            safeDouble(dto.getAdjustmentHigh()),
            safeDouble(dto.getAdjustmentLow()),
            safeDouble(dto.getAdjustmentClose()),
            safeDouble(dto.getAdjustmentVolume())
        );
    }

    private static Double parseNullableDouble(String value) {
        try {
            return value == null || value.isEmpty() ? null : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private static double safeDouble(Double value) {
        return value == null ? 0.0 : value;
    }
}