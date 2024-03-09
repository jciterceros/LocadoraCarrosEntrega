package LocadoraCarros.classe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static Connection conn;

    public void conectar() {
        conectar("127.0.0.1", "5433", "lucaslocadora", "postgres", "postgres");
    }

    private void conectar(String HOST, String PORTA, String BANCO, String USUARIO, String SENHA) {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://" + HOST + ":" + PORTA + "/" + BANCO, USUARIO, SENHA);
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possivel estabelecer uma conexao com o Banco de Dados!", ex);
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
