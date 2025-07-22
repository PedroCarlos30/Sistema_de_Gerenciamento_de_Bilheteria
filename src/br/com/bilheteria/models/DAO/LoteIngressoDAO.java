/* package br.com.bilheteria.models.DAO;

import br.com.bilheteria.models.entidade.LoteIngresso;
import java.sql.*;
import java.util.ArrayList;

public class LoteIngressoDAO {

    public ArrayList<LoteIngresso> listarTodos() {
        ArrayList<LoteIngresso> lotes = new ArrayList<>();
        String sql = "SELECT * FROM lote_ingresso";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lotes.add(criarLote(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lotes;
    }

    public void salvar(LoteIngresso lote) {
        String sql = "INSERT INTO lote_ingresso (preco, quantidade_total_disponivel, quantidade_vendida, id_categoria, id_evento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, lote.getPreco());
            stmt.setInt(2, lote.getQuantidadeTotalDisponivel());
            stmt.setInt(3, lote.getQuantidadeVendida());
            stmt.setInt(4, lote.getCategoria().getIdCategoria());
            stmt.setInt(5, lote.getIdEvento());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lote.setIdLote(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LoteIngresso buscarPorId(int id) {
        String sql = "SELECT * FROM lote_ingresso WHERE id_lote = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarLote(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LoteIngresso criarLote(ResultSet rs) throws SQLException {
        LoteIngresso lote = new LoteIngresso(
            null, // categoria (busque pelo id_categoria se necessário)
            rs.getDouble("preco"),
            rs.getInt("quantidade_total_disponivel")
        );
        lote.setIdLote(rs.getInt("id_lote"));
        // lote.setQuantidadeVendida(rs.getInt("quantidade_vendida")); // se tiver setter
        // lote.setIdEvento(rs.getInt("id_evento")); // se tiver setter
        return lote;
    }

    public void deletar(int idLote) {
        String sql = "DELETE FROM lote_ingresso WHERE id_lote = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLote);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} */