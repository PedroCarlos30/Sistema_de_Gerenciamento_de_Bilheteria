/* package br.com.bilheteria.models.DAO;

import br.com.bilheteria.models.entidade.CategoriaIngresso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaIngressoDAO {

    public void salvar(CategoriaIngresso categoria) {
        String sql = "INSERT INTO categoria_ingresso (nome_categoria, descricao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    // Atualiza o id gerado pelo banco
                    // Se quiser sincronizar o id da classe com o banco, adicione um setIdCategoria(int id) na entidade
                    // categoria.setIdCategoria(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CategoriaIngresso> listarTodos() {
        List<CategoriaIngresso> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria_ingresso";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CategoriaIngresso categoria = new CategoriaIngresso(
                    rs.getString("nome_categoria"),
                    rs.getString("descricao")
                );
                // Se quiser sincronizar o id, adicione um setIdCategoria(int id) na entidade
                // categoria.setIdCategoria(rs.getInt("id_categoria"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public CategoriaIngresso buscarPorId(int id) {
        String sql = "SELECT * FROM categoria_ingresso WHERE id_categoria = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CategoriaIngresso categoria = new CategoriaIngresso(
                        rs.getString("nome_categoria"),
                        rs.getString("descricao")
                    );
                    // Se quiser sincronizar o id, adicione um setIdCategoria(int id) na entidade
                    // categoria.setIdCategoria(rs.getInt("id_categoria"));
                    return categoria;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} */