package org.stofli.HttpClient;

import java.util.List;

public class DailyQuates {

    public List<DailyQuate> daily_quotes;

    public List<DailyQuate> getDailyQuates() {
        return daily_quotes;
    }

    public static class DailyQuate {
        public String Date;
        public String Code;
        public double Open;
        public double High;
        public double Low;
        public double Close;
        public String UpperLimit;
        public String LowerLimit;
        public double Volume;
        public double TurnoverValue;
        public double AdjustmentFactor;
        public double AdjustmentOpen;
        public double AdjustmentHigh;
        public double AdjustmentLow;
        public double AdjustmentClose;
        public double AdjustmentVolume;
    }
}

