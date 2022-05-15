package app;

import java.util.List;

public interface AbstractRepository <T>{
    void save(T t);
    T findById(Long id);
    List<T> findByName(String name);
}
