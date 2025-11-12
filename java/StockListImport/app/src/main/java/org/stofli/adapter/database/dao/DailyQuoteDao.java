package org.stofli.adapter.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.stofli.adapter.database.core.Dao;
import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;

public class DailyQuoteDao implements Dao<DailyQuote, Long>{

  private static final String INSERT = """
        INSERT INTO daily_quotes (
            date, code, open, high, low, close, upper_limit, lower_limit,
            volume, turnover_value, adjustment_factor, adjustment_open,
            adjustment_high, adjustment_low, adjustment_close, adjustment_volume
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT (date, code) DO NOTHING;
        """;
  public Connection conn;
  public DailyQuoteDao(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Optional<DailyQuote> get(Long id) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

  public boolean exist(CompanyCode code, LocalDate date) throws SQLException {
    String sql = "SELECT * FROM daily_quotes WHERE code = ? AND date = ? LIMIT 1";
    try(PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, code.value());
      stmt.setDate(2, Date.valueOf(date));
      try(ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    }
  }

  public Optional<CompanyCode> findLastCodeByDate(LocalDate date) throws SQLException {
    String sql = "SELECT code FROM daily_quotes WHERE date = ? ORDER BY code DESC LIMIT 1";
    try(PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setDate(1, Date.valueOf(date));
      try(ResultSet rs = stmt.executeQuery()) {
        if(rs.next()) {
          return Optional.of(new CompanyCode(rs.getString("code")));
        }
        return Optional.empty();
      }
    }
  }

  @Override
  public List<DailyQuote> findAll(int limit, int offset) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public int insert(DailyQuote entity) throws SQLException {
    try (PreparedStatement pstmt = conn.prepareStatement(INSERT)) {
      pstmt.setDate(1, java.sql.Date.valueOf(entity.getDate()));
      pstmt.setString(2, entity.getCode().value());
      pstmt.setBigDecimal(3, entity.getOpen());
      pstmt.setBigDecimal(4,  entity.getHigh());
      pstmt.setBigDecimal(5,  entity.getLow());
      pstmt.setBigDecimal(6,  entity.getClose());
      pstmt.setBigDecimal(7,  entity.getUpperLimit());
      pstmt.setBigDecimal(8,  entity.getLowerLimit());
      pstmt.setBigDecimal(9,  entity.getVolume());
      pstmt.setBigDecimal(10,  entity.getTurnoverValue());
      pstmt.setBigDecimal(11,  entity.getAdjustmentFactor());
      pstmt.setBigDecimal(12,  entity.getAdjustmentOpen());
      pstmt.setBigDecimal(13,  entity.getAdjustmentHigh());
      pstmt.setBigDecimal(14,  entity.getAdjustmentLow());
      pstmt.setBigDecimal(15,  entity.getAdjustmentClose());
      pstmt.setBigDecimal(16,  entity.getAdjustmentVolume());
      return pstmt.executeUpdate();
    }
  }

  @Override
  public int[] insertBatch(List<DailyQuote> entities) throws SQLException {
    int count = 0;
    try(PreparedStatement pstmt = conn.prepareStatement(INSERT)) {
      for(DailyQuote entity : entities) {
        count ++;
        pstmt.setDate(1, java.sql.Date.valueOf(entity.getDate()));
        pstmt.setString(2, entity.getCode().value());
        pstmt.setBigDecimal(3, entity.getOpen());
        pstmt.setBigDecimal(4,  entity.getHigh());
        pstmt.setBigDecimal(5,  entity.getLow());
        pstmt.setBigDecimal(6,  entity.getClose());
        pstmt.setBigDecimal(7,  entity.getUpperLimit());
        pstmt.setBigDecimal(8,  entity.getLowerLimit());
        pstmt.setBigDecimal(9,  entity.getVolume());
        pstmt.setBigDecimal(10,  entity.getTurnoverValue());
        pstmt.setBigDecimal(11,  entity.getAdjustmentFactor());
        pstmt.setBigDecimal(12,  entity.getAdjustmentOpen());
        pstmt.setBigDecimal(13,  entity.getAdjustmentHigh());
        pstmt.setBigDecimal(14,  entity.getAdjustmentLow());
        pstmt.setBigDecimal(15,  entity.getAdjustmentClose());
        pstmt.setBigDecimal(16,  entity.getAdjustmentVolume());
        pstmt.addBatch();
        if(count % 1000 == 0) {
          pstmt.executeBatch();
          pstmt.clearBatch();
        }
      }
      pstmt.executeBatch();
      pstmt.clearBatch();
    }
    return new int[]{count};
  }

  @Override
  public int partialUpdate(Long id, Map<String, Object> fields) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
  }

  @Override
  public int update(DailyQuote t, String[] params) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public int deleteById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public Optional<DailyQuote> get(long id) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }
}
