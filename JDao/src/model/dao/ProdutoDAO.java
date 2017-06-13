package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Categoria;
import model.bean.Produto;

public class ProdutoDAO {

    private Connection con = null;

    public ProdutoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Produto produto) {
        String sql = "INSERT INTO produto (descricao, qtd, valor, id_categoria) VALUES (?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, produto.getDescricao());
            st.setInt(2, produto.getQtd());
            st.setDouble(3, produto.getValor());
            st.setInt(4, produto.getCategoria().getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, st);
        }
    }

    public List<Produto> findAll() {
        String sql = "SELECT p.id as pid, p.descricao as pdesc, qtd, valor, id_categoria,                                                                                                                     c.id as cid, c.descricao as cdesc FROM produto p inner join categoria c on c.id = p.id_categoria";
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("pid"));
                produto.setDescricao(rs.getString("pdesc"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setValor(rs.getDouble("valor"));
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("cid"));
                categoria.setDescricao(rs.getString("cdesc"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, st, rs);
        }
        return produtos;
    }

    /* public List<Produto> findAll() {
        String sql = "SELECT p.id as pid, p.descricao as pdesc, qtd, valor, categoria_id, "
                + "c.id as cid, c.descricao as cdesc FROM produto p inner join categoria c on c.id = p.categoria_id";
        //String sql = "SELECT * FROM vw_prodcad";
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, st, rs);
        }
        return produtos;
    }
     */
    public boolean update(Produto produto) {
        String sql = "UPDATE produto SET descricao = ?, qtd = ?, valor = ?, id_categoria = ? WHERE id = ?";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, produto.getDescricao());
            st.setInt(2, produto.getQtd());
            st.setDouble(3, produto.getValor());
            st.setInt(4, produto.getCategoria().getId());
            st.setInt(5, produto.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, st);
        }
    }

    public boolean delete(Produto produto) {
        String sql = "DELETE FROM produto WHERE id = ?";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, produto.getId());
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
