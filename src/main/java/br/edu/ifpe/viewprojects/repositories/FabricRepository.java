package br.edu.ifpe.viewprojects.repositories;

public class FabricRepository {
    public static IProductRepository getProductRepository() {
        return new ProductRepository();
    }
}
