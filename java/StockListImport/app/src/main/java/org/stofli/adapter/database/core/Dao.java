package org.stofli.adapter.database.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<T, ID> {

    Optional<T> get(ID id) throws SQLException;
    
    List<T> findAll(int limit, int offset) throws SQLException;

    int insert(T entity) throws SQLException;

    int[] insertBatch(List<T> entities) throws SQLException;

    int partialUpdate(ID id, Map<String, Object> fields) throws SQLException;    
    
    int update(T t, String[] params);
    
    int deleteById(ID id) throws SQLException;

    Optional<T> get(long id) throws SQLException;
}
