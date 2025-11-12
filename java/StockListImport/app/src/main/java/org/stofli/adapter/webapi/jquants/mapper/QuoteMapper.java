package org.stofli.adapter.webapi.jquants.mapper;

import java.util.List;

import org.stofli.adapter.webapi.jquants.dto.DailyQuoteDto;
import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;

public final class QuoteMapper {
    private QuoteMapper() {}

    public static List<DailyQuote> toDomain(List<DailyQuoteDto> dtoList) {
        if (dtoList == null) return List.of();
        return dtoList.stream()
            .map(QuoteMapper::toDomain)
            .toList();
    }

    public static DailyQuote toDomain(DailyQuoteDto dto) {
        CompanyCode code = new CompanyCode(dto.getCode());
        return new DailyQuote(
            dto.getDate(),
            code,
            dto.getOpen(),
            dto.getHigh(),
            dto.getLow(),
            dto.getClose(),
            dto.getUpperLimit(),
            dto.getLowerLimit(),
            dto.getVolume(),
            dto.getTurnoverValue(),
            dto.getAdjustmentFactor(),
            dto.getAdjustmentOpen(),
            dto.getAdjustmentHigh(),
            dto.getAdjustmentLow(),
            dto.getAdjustmentClose(),
            dto.getAdjustmentVolume()
        );
    }
}
