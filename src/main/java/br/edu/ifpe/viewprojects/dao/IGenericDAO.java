package br.edu.ifpe.viewprojects.Dao;

import java.util.List;

public interface IGenericDAO<T> {
    public void insert(T object);
    public void update(T object);
    public void delete(T object);
    public T findById(Integer id);
    public List<T> findAll();
}
