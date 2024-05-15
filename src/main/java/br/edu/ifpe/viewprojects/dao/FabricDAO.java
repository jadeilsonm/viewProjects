package br.edu.ifpe.viewprojects.Dao;

public class FabricDAO {

    public static <T> IGenericDAO getGenericDAO() {
        return new GenericDAO<T>();
    }

}
