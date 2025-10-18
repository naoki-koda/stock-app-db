package org.stofli.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.stofli.tse.model.TseData;
public interface Dao<T, ID> {

    Optional<T> get(ID id) throws SQLException;
    
    List<T> findAll(int limit, int offset) throws SQLException;

    int insert(T entity) throws SQLException;

    int[] insertBatch(List<T> entities) throws SQLException;

    int partialUpdate(ID id, Map<String, Object> fields) throws SQLException;    
    
    int update(T t, String[] params);
    
    int deleteById(ID id) throws SQLException;

    Optional<TseData> get(long id) throws SQLException;
}
