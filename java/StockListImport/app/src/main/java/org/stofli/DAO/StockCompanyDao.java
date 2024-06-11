package org.stofli.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Collection;

import org.stofli.TSE.TseData;

import com.google.common.base.Optional;



public class StockCompanyDao implements Dao<TseData>{

    public Connection conn;

    public StockCompanyDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<TseData> get(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<TseData> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void save(TseData t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(TseData t, String[] params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(TseData t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    @Override
    public void insertBatch(TseData t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertBatch'");
    }

    @Override
    public void insert(Collection<TseData> dataList) {
       String sql = "INSERT INTO stock.company (code, name, marketid, date) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = null;

        for(TseData data: dataList) {
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,data.code());
                stmt.setString(2,data.name());
                stmt.setInt(3,data.marketId());
                stmt.setString(4,data.date());
                stmt.executeUpdate();
            }catch (SQLException e){
                System.out.println("SQLException:" + e.getMessage());
            }
        } 
    }
}
