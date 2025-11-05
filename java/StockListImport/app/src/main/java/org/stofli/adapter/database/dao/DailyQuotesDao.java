package org.stofli.adapter.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.stofli.adapter.database.core.Dao;
import org.stofli.domain.model.DailyQuote;

public class DailyQuotesDao implements Dao<DailyQuote, Long>{

  public Connection conn;
  public DailyQuotesDao(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Optional<DailyQuote> get(Long id) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

  @Override
  public List<DailyQuote> findAll(int limit, int offset) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public int insert(DailyQuote entity) throws SQLException {
    String sql = """
        INSERT INTO daily_quotes (
            date, code, open, high, low, close, upper_limit, lower_limit,
            volume, turnover_value, adjustment_factor, adjustment_open,
            adjustment_high, adjustment_low, adjustment_close, adjustment_volume
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT (date, code) DO NOTHING;
    """;
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setDate(1, java.sql.Date.valueOf(entity.getDate()));
      pstmt.setString(2, entity.getCode());
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
    throw new UnsupportedOperationException("Unimplemented method 'insertBatch'");
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
