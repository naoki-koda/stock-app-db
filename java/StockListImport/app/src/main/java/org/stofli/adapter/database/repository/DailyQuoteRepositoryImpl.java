package org.stofli.adapter.database.repository;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.stofli.adapter.database.core.DbUtil;
import org.stofli.adapter.database.dao.DailyQuoteDao;
import org.stofli.domain.model.CompanyCode;
import org.stofli.domain.model.DailyQuote;
import org.stofli.domain.repository.DailyQuoteRepository;

public class DailyQuoteRepositoryImpl implements DailyQuoteRepository {

    @Override
    public int saveAll(List<DailyQuote> dataList) {
        int insertedCount = 0;
        try(Connection conn = DbUtil.getConnection()) {
            DailyQuoteDao dao = new DailyQuoteDao(conn);
            dao.insertBatch(dataList);
            return insertedCount;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save daily quotes", e);
        }
    }

    @Override
    public void deleteByCode(CompanyCode code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByCode'");
    }
    
    @Override
    public boolean exist(CompanyCode code, LocalDate date) {
        try(Connection conn = DbUtil.getConnection()) {
            DailyQuoteDao dao = new DailyQuoteDao(conn);
            return dao.exist(code, date);
        } catch(Exception e) {
            throw new RuntimeException("Failed to select daily quotes code" + code + "date= " + date , e);
        }
    }
    public Optional<CompanyCode> findLastCodeByDate(LocalDate date) {
        try(Connection conn = DbUtil.getConnection()) {
            DailyQuoteDao dao = new DailyQuoteDao(conn);
            return dao.findLastCodeByDate(date);
        } catch(Exception e) {
            throw new RuntimeException("Failed to select last code by date");
        }
    }
}
