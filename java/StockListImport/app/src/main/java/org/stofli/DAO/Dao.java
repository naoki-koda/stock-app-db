package org.stofli.DAO;


import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Collection;


import com.google.common.base.Optional;
public interface Dao<T> {

    Optional<T> get(long id);
    
    List<T> getAll();

    void insert(Collection<T> dataList);

    void insertBatch(T t);
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}
