package br.edu.ifpe.viewprojects.shareds;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection implements IConnection {

    private static final String URL = "jdbc:h2:~/viewprojects";
    private static final String USER = "SA";


    public java.sql.Connection toConnect() {

        java.sql.Connection conn = null;
        try {
            System.out.println("Tentando se connectar");
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(URL, USER, "");
            System.out.println("Conectou no banco de dados.");
        } catch (SQLException ex) {
            System.out.println("Erro: Não conseguiu conectar no BD.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Não encontrou o driver do BD.");
        }

        return conn;
    }

    public void disconnect(java.sql.Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectou do banco de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Não conseguiu desconectar do BD.");
        }
    }
}
