package org.stofli.DAO;

import java.sql.Connection;

import org.apache.poi.ss.formula.functions.T;
import org.stofli.TSE.TseData;

import java.util.List;


import com.google.common.base.Optional;
public interface Dao {

    Optional<T> get(long id);
    
    List<T> getAll();

    void insert(List<TseData> dataList);

    void insertBatch(T t);
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}
