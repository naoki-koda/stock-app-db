package org.stofli.TSE;

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
