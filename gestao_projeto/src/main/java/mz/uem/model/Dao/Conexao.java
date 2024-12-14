package mz.uem.model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL="jdbc:mysql://localhost:3306/gestao_projetos";
    private static final String USER="root";
    private static final String PASSWORD="10000008";

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        }catch(ClassNotFoundException exc){
            throw new SQLException("O Driver nao foi encontrado!",exc);
        }
    }
}
