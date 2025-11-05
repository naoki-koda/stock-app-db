package org.stofli.adapter.excel.reader;

import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.stofli.adapter.excel.core.ExcelReader;
import org.stofli.adapter.excel.util.ExcelUtil;
import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.RegistrationDate;
import org.stofli.domain.model.TseData;

public class TseExcelReader extends ExcelReader<TseData> {


  @Override
  protected int getSheetNum() {
    return 0;
  }

  @Override
  protected int getReadStartRowNum() {
    return 1;
  }

  @Override
  protected TseData mapRowToEntity(Row row) {
    Cell dateCell = row.getCell(TseHeader.DATE.getColNumber());
    Cell code = row.getCell(TseHeader.CODE.getColNumber());
    Cell name = row.getCell(TseHeader.NAME.getColNumber());
    Cell marketKind = row.getCell(TseHeader.MARKET_KIND.getColNumber());
    RegistrationDate date = new RegistrationDate(ExcelUtil.readCell(dateCell));
    LocalDate localDate = date.value();

    return new TseData(
      localDate,
      new CompanyCode(ExcelUtil.readCell(code)), 
      ExcelUtil.readCell(name), 
      convertMarketId(ExcelUtil.readCell(marketKind)));
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

  public enum TseHeader {
      DATE(0,"日付"),
      CODE(1,"コード"),
      NAME(2,"銘柄名"),
      MARKET_KIND(3,"市場・商品区分");

      private int colNumber;
      private String headerText;

      private TseHeader(int colNumber, String headerText) {
          this.colNumber = colNumber;
          this.headerText = headerText;
      }

      public int getColNumber() {
          return colNumber;
      }

      public String getHeaderText() {
          return headerText;
      }
  }
  public enum TseMarketKind {
      PRIME_DOMESTIC(1,"プライム（内国株式）"),
      ETF_ETN(2,"ETF・ETN"),
      GROWTH_DOMESTIC(3,"グロース（内国株式）"),
      PRO_MARKET(4, "PRO Market"),
      STANDARD_DOMESTIC(5, "スタンダード（内国株式）"),
      PRIME_FOREIGN(6, "プライム（外国株式）"),
      REIT(7, "REIT・ベンチャーファンド・カントリーファンド・インフラファンド"),
      STANDARD_FOREIGN(8, "スタンダード（外国株式）"),
      GROWTH_FOREIGN(9, "グロース（外国株式）"),
      EQUITY_SEQURITIES(10, "出資証券");

      private final int id;
      private final String name;

      private TseMarketKind(int id, String name) {
          this.id = id;
          this.name = name;
      }

      public int getId() {
          return id;
      }

      public String getName() {
          return name;
      }

      public static TseMarketKind getEnumName(String str)
      {
          for(TseMarketKind v : values())
          {
              if(v.getName().equals(str))
              {
                  return v;
              }
          }
          throw new IllegalArgumentException("undefined : " + str);
      }
  }
}
