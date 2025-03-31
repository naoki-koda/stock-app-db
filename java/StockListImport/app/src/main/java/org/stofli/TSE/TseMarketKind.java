package org.stofli.TSE;

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
