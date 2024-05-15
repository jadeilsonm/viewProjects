package br.edu.ifpe.viewprojects.repositories;

import br.edu.ifpe.viewprojects.entites.Project;
import java.util.List;

public interface IProductRepository {
    List<Project> get();
    Project getById(Integer id);
    void add(Project project);
    void delete(Integer id);
    void update(Project project);
}
