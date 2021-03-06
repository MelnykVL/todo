package edu.jpahiber.dao;

public interface DAO<T> {

    T get(int id);
    void save(T model);
    void update(T model);
    void delete(T model);

}
