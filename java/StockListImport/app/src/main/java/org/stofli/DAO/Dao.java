package org.stofli.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
public interface Dao<T> {

    Optional<T> get(long id) throws SQLException;
    
    List<T> getAll();

    void insert(T t) throws SQLException;

    void insertBatch(List<T> tList) throws SQLException;
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}
