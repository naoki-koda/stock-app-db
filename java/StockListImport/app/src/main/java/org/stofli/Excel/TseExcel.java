package org.stofli.Excel;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.stofli.TSE.TseData;
import org.stofli.TSE.TseHeader;
import org.stofli.TSE.TseMarketKind;

public class TseExcel extends Excel {

    public TseExcel(String filePath) {
        super(filePath);
    }

    @Override
    public List<TseData> readData() {
        
        List<TseData> dataList = new ArrayList<TseData>();

        Sheet sheet = workbook.getSheetAt(FIRST_SHEET);

        Iterator<Row> rows = sheet.rowIterator();
        //Excelの1行目はヘッダーが記載されているため飛ばす。
        rows.next();
        while(rows.hasNext()) {
            Row row = rows.next();
            Cell date = row.getCell(TseHeader.DATE.getColNumber());
            Cell code = row.getCell(TseHeader.CODE.getColNumber());
            Cell name = row.getCell(TseHeader.NAME.getColNumber());
            Cell marketKind = row.getCell(TseHeader.MARKET_KIND.getColNumber());
            TseData data = new TseData(readCell(date), readCell(code), readCell(name), convertMarketId(readCell(marketKind)));
            dataList.add(data);
        }
        return dataList;
    }

    private int convertMarketId(String marketKind) {
        return switch (TseMarketKind.getEnumName(marketKind)) {
            case PRIME_DOMESTIC -> TseMarketKind.PRIME_DOMESTIC.getId();
            case ETF_ETN -> TseMarketKind.ETF_ETN.getId();
            case GROWTH_DOMESTIC -> TseMarketKind.GROWTH_DOMESTIC.getId();
            case PRO_MARKET -> TseMarketKind.PRO_MARKET.getId();
            case STANDARD_DOMESTIC -> TseMarketKind.STANDARD_DOMESTIC.getId();
            case PRIME_FOREIGN -> TseMarketKind.PRIME_FOREIGN.getId();
            case REIT -> TseMarketKind.REIT.getId();
            case STANDARD_FOREIGN -> TseMarketKind.STANDARD_FOREIGN.getId();
            case GROWTH_FOREIGN -> TseMarketKind.GROWTH_FOREIGN.getId();
            case EQUITY_SEQURITIES -> TseMarketKind.EQUITY_SEQURITIES.getId();
            default -> 0;
        };
    }
}
