package br.edu.ifpe.viewprojects.repositories;


import br.edu.ifpe.viewprojects.dao.FabricDAO;
import br.edu.ifpe.viewprojects.dao.IGenericDAO;
import br.edu.ifpe.viewprojects.entites.Project;

import java.util.List;

public class ProductRepository implements IProductRepository{

    private IGenericDAO<Project> dao;

    public ProductRepository() {
        this.dao = FabricDAO.getGenericDAO();
    }

    @Override
    public List<Project> get() {
        return dao.findAll();
    }

    @Override
    public Project getById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void add(Project project) {
        dao.insert(project);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void update(Project project) {

    }
}
