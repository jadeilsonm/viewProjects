package br.edu.ifpe.viewprojects.dao;

public class FabricDAO {

    public static <T> IGenericDAO getGenericDAO() {
        return new GenericDAO<T>();
    }

}
