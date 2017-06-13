package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Categoria;

public class CategoriaDAO {

    private Connection con = null;

    public CategoriaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Categoria categoria) {
        String sql = "INSERT INTO categoria (descricao) VALUES (?)";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, categoria.getDescricao());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, st);
        }
    }

    public List<Categoria> findAll() {
        String sql = "SELECT * FROM categoria";
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setDescricao(rs.getString("descricao"));
                categoria.setId(rs.getInt("id"));
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, st, rs);
        }
        return categorias;
    }
    public boolean update(Categoria categoria){
        String sql = "UPDATE categoria SET descricao = ? WHERE id = ?";
        PreparedStatement st = null;
        try{
            st = con.prepareStatement(sql);
            st.setString(1, categoria.getDescricao());
            st.setInt(2, categoria.getId());
            st.executeUpdate();
            return true;
        }catch (SQLException ex) {
            System.err.println("Erro" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, st);
        }
    }
    public boolean delete(Categoria categoria){
        String sql = "DELETE FROM categoria WHERE id = ?";
        PreparedStatement st = null;
        try{
            st = con.prepareStatement(sql);
            st.setInt(1, categoria.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, st);
        }
    }
}
