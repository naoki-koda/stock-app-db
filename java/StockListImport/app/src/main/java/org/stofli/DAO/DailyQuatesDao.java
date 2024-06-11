package org.stofli.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.stofli.HttpClient.DailyQuates.DailyQuate;

import com.google.common.base.Optional;


public class DailyQuatesDao implements Dao<DailyQuate> {

    public Connection conn;

    public DailyQuatesDao(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void delete(DailyQuate t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<DailyQuate> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DailyQuate> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Collection<DailyQuate> dataList) {

        String sql = "INSERT INTO stock.daily_quotes ("
            + "code, date, open, high, low, close, "
            + "upper_limit, lower_limit, volume, turnover_value, "
            + "adjustment_factor, adjustment_Open, adjustment_High, "
            + "adjustment_low, adjustment_Close, adjustment_volume) VALUES ("
            + "?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, "
            + "?, ?, ?, "
            + "?, ?, ?"
            + ")";
        
        PreparedStatement stmt = null;

        int cnt = 0;
        for(DailyQuate data: dataList) {
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,data.Code);
                stmt.setString(2,data.Date);
                stmt.setDouble(3,data.Open);
                stmt.setDouble(4,data.High);
                stmt.setDouble(5,data.Low);
                stmt.setDouble(6,data.Close);
                stmt.setString(7,data.UpperLimit);
                stmt.setString(8,data.LowerLimit);
                stmt.setDouble(9,data.Volume);
                stmt.setDouble(10,data.TurnoverValue);
                stmt.setDouble(11,data.AdjustmentFactor);
                stmt.setDouble(12,data.AdjustmentOpen);
                stmt.setDouble(13,data.AdjustmentHigh);
                stmt.setDouble(14,data.AdjustmentLow);
                stmt.setDouble(15,data.AdjustmentClose);
                stmt.setDouble(16,data.AdjustmentVolume);
                stmt.addBatch();
                if(cnt % 100 == 0 | cnt == dataList.size()) {
                    stmt.executeBatch();
                }
            }catch (SQLException e){
                System.out.println("SQLException:" + e.getMessage());
            }
        }
    }

    @Override
    public void insertBatch(DailyQuate t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void save(DailyQuate t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(DailyQuate t, String[] params) {
        // TODO Auto-generated method stub
        
    }

}
