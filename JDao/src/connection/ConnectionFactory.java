package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/produto";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        }
    }
    
    public static void closeConnection(Connection con){
        if(con != null){
            try{
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro na conexão" + ex);
            }
        }
    }
    public static void closeConnection(Connection con, PreparedStatement st){
        if(st != null){
            try{
                st.close();
            } catch (SQLException ex) {
                System.err.println("Erro na conexão" + ex);
            }
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con, PreparedStatement st, ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro na conexão" + ex);
            }
        }
        closeConnection(con, st);
    }
}
