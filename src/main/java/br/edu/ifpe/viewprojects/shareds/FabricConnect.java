package br.edu.ifpe.viewprojects.Shareds;

public class FabricConnect {

    private static IConnection connection;

    private FabricConnect() {
        if (connection == null) {
            connection = new Connection();
        }
    }

    public static IConnection getConnection() {
        return connection;
    }
}
