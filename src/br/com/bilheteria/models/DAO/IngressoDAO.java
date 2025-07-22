package br.com.bilheteria.models.DAO;

import br.com.bilheteria.models.entidade.Ingresso;
import br.com.bilheteria.models.entidade.StatusIngresso;
import br.com.bilheteria.models.entidade.Evento;
import br.com.bilheteria.models.entidade.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO {

    // CORREÇÃO: Removido o JOIN e as colunas de lote_ingresso, pois não são mais usadas
    private static final String SELECT_INGRESSO_COMPLETO = 
        "SELECT i.id_ingresso, i.codigo_unico, i.data_hora_venda, i.status_ingresso, i.preco_venda, " +
        "e.id_evento, e.nome AS nome_evento, " +
        "v.id_venda, v.data_hora_venda AS data_da_venda " +
        "FROM ingresso i " +
        "LEFT JOIN evento e ON i.id_evento = e.id_evento " +
        "LEFT JOIN venda v ON i.id_venda = v.id_venda ";

    // CORREÇÃO: Método salvar ajustado para não receber idLote e idCategoria
    public void salvar(Ingresso ingresso, int idVenda, int idEvento) {
        String sql = "INSERT INTO ingresso (id_venda, id_evento, codigo_unico, data_hora_venda, preco_venda, status_ingresso) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);
            stmt.setInt(2, idEvento);
            stmt.setString(3, ingresso.getCodigoUnico());
            stmt.setTimestamp(4, new java.sql.Timestamp(ingresso.getDataHoraVenda().getTime()));
            stmt.setDouble(5, ingresso.getPrecoVenda());
            stmt.setString(6, ingresso.getStatusIngresso().name());

            stmt.executeUpdate();
            System.out.println("LOG: Ingresso salvo com sucesso no banco de dados.");

        } catch (SQLException e) {
            System.err.println("LOG DE ERRO: Falha ao salvar o ingresso.");
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar ingresso no banco de dados.", e);
        }
    }
    
    public void atualizar(Ingresso ingresso) {
        String sql = "UPDATE ingresso SET status_ingresso = ? WHERE id_ingresso = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ingresso.getStatusIngresso().name());
            stmt.setInt(2, ingresso.getIdIngresso());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("LOG DE ERRO: Falha ao atualizar o ingresso.");
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar ingresso no banco de dados.", e);
        }
    }

    public Ingresso buscarPorId(int id) {
        String sql = SELECT_INGRESSO_COMPLETO + "WHERE i.id_ingresso = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarIngressoDeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ingresso por ID.", e);
        }
        return null;
    }

    public List<Ingresso> listarTodos() {
        List<Ingresso> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(SELECT_INGRESSO_COMPLETO);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(criarIngressoDeResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("LOG DE ERRO: Falha ao listar todos os ingressos.");
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar todos os ingressos.", e);
        }
        return lista;
    }
    
    private Ingresso criarIngressoDeResultSet(ResultSet rs) throws SQLException {
        Ingresso ingresso = new Ingresso();
        ingresso.setIdIngresso(rs.getInt("id_ingresso"));
        ingresso.setCodigoUnico(rs.getString("codigo_unico"));
        ingresso.setDataHoraVenda(rs.getTimestamp("data_hora_venda"));
        ingresso.setStatusIngresso(StatusIngresso.valueOf(rs.getString("status_ingresso")));
        ingresso.setPrecoVenda(rs.getDouble("preco_venda"));

        if (rs.getInt("id_evento") > 0) {
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            ingresso.setEvento(evento);
        }

        if (rs.getInt("id_venda") > 0) {
            Venda venda = new Venda();
            venda.setIdVenda(rs.getInt("id_venda"));
            venda.setDataHoraVenda(rs.getTimestamp("data_da_venda"));
            ingresso.setVenda(venda);
        }
        
        return ingresso;
    }
}
