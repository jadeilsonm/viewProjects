package br.edu.ifpe.viewprojects.dao;

import java.util.List;

public interface IGenericDAO<T> {
    public void insert(T object);
    public void update(T object, Integer id);
    public void delete(Integer id);
    public T findById(Integer id);
    public List<T> findAll();
}
