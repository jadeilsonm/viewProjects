package br.edu.ifpe.viewprojects.shareds;

public interface IConnection {
    public java.sql.Connection toConnect();
    public void disconnect(java.sql.Connection conn);
}
