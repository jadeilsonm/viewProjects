package br.edu.ifpe.viewprojects.Shareds;

public interface IConnection {
    public java.sql.Connection toConnect();
    public void disconnect(java.sql.Connection conn);
}
