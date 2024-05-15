package br.edu.ifpe.viewprojects.services;

import br.edu.ifpe.viewprojects.dao.FabricDAO;
import br.edu.ifpe.viewprojects.dao.IGenericDAO;

public class ScholServices {
    private IGenericDAO genericDAO;

    public ScholServices() {
        this.genericDAO = FabricDAO.getGenericDAO();
    }
}
