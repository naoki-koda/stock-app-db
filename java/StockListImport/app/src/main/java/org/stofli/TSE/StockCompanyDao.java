package org.stofli.tse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.stofli.dao.Dao;
import org.stofli.tse.model.TseData;
import org.stofli.util.DbUtil;

public class StockCompanyDao implements Dao<TseData, Long> {

    public Connection conn;

    public StockCompanyDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<TseData> get(long id) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'get'"); 
    }

    @Override
    public    List<TseData> findAll(int limit, int offset) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public int update(TseData t, String[] params) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int[] insertBatch(List<TseData> tList) throws SQLException {
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
            return stmt.executeBatch();
        }
    }

    @Override
    public int insert(TseData t) throws SQLException {
        return insertBatch(List.of(t))[0];
    }

    @Override
    public int partialUpdate(Long id, Map<String, Object> fields) throws SQLException {
        if (fields.isEmpty()) return 0;
        String set = fields.keySet().stream()
                .map(k -> k + "=?")
                .collect(Collectors.joining(", "));
        String sql = "UPDATE company SET " + set + " WHERE id=?";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int idx = 1;
            for (Object v : fields.values()) {
                ps.setObject(idx++, v);
                ps.setLong(idx, id);
            }
            return ps.executeUpdate();
        }
    }

    public void truncateTable() throws SQLException {
        String sql = "TRUNCATE TABLE company";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        }
    }

    @Override
    public Optional<TseData> get(Long id) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}
