package br.edu.ifpe.viewprojects.shareds;

public class FabricConnect {

    private static IConnection connection;

    private FabricConnect() {
        if (connection == null) {
            connection = new H2Connection();
        }
    }

    public static IConnection getConnection() {
        System.out.println("Conectou no banco de dados.");
        return new FabricConnect().connection;
    }
}
