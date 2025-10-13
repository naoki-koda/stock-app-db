package org.stofli.tse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.stofli.dao.Dao;
import org.stofli.tse.model.TseData;

public class StockCompanyDao implements Dao<TseData> {

    public Connection conn;

    public StockCompanyDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<TseData> get(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'"); 
        // return Optional.empty();
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
    public void insertBatch(List<TseData> tList) throws SQLException {
        String sql = "INSERT INTO company (code, name, marketid, date) VALUES (?, ?, ?, ?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
           for(TseData td: tList) {
                LocalDate localDate = LocalDate.parse(td.date(), formatter);
                stmt.setString(1, td.code());
                stmt.setString(2, td.name());
                stmt.setInt(3, td.marketId());
                stmt.setDate(4, java.sql.Date.valueOf(localDate));
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    @Override
    public void insert(TseData t) throws SQLException {
        insertBatch(List.of(t));
    }

    public void truncateTable() throws SQLException {
        String sql = "TRUNCATE TABLE company";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        }
    }
}
