package br.edu.ifpe.viewprojects.services;

import br.edu.ifpe.viewprojects.entites.Project;
import br.edu.ifpe.viewprojects.repositories.FabricRepository;
import br.edu.ifpe.viewprojects.repositories.IProductRepository;

import java.time.LocalDate;

public class ProjectsService {
    private IProductRepository repository;

    public ProjectsService() {
        this.repository = FabricRepository.getProductRepository();
    }

    public void Add(Project p) {
        if (p != null) {
            if (p.getStartDate() == null)
                p.setStartDate(LocalDate.now());
            if (p.getEndDate() == null)
                p.setEndDateDate(LocalDate.now().plusDays(20));
            if (p.getUserId() == null || p.getUserId() == 0)
                p.setUserId(1);

            repository.add(p);
        }
    }

    public void Delete(Integer id) {
        repository.delete(id);
    }

}
