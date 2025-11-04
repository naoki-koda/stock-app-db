package org.stofli.infrastructure.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.TseData;
import org.stofli.infrastructure.configuration.DbUtil;
import org.stofli.infrastructure.persistence.core.Dao;

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
    public List<TseData> findAll(int limit, int offset) throws SQLException {
        String sql = "SELECT * FROM stock.company";
        List<TseData> companies = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TseData tseData = new TseData(
                    resultSet.getDate("date").toLocalDate(),
                    new CompanyCode(resultSet.getString("code")),
                    resultSet.getString("name"),
                    resultSet.getInt("marketid")
                );
                companies.add(tseData);
            }
        }
        return companies;
    }

    public List<CompanyCode> findAllCode() throws SQLException {
        String sql = "SELECT * FROM stock.company";
        List<CompanyCode> codes = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                codes.add(new CompanyCode(resultSet.getString("code")));
            }
        }
        return codes;
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
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
           for(TseData td: tList) {
                stmt.setString(1, td.getCode().value());
                stmt.setString(2, td.getName());
                stmt.setInt(3, td.getMarketId());
                stmt.setDate(4, java.sql.Date.valueOf(td.getDate()));
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

    public void deleteAllRecord() throws SQLException {
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
