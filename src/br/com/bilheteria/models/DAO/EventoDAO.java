package br.com.bilheteria.models.DAO;

import br.com.bilheteria.models.entidade.Evento;
import br.com.bilheteria.models.entidade.StatusEvento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public void salvar(Evento evento) {
        String sql = "INSERT INTO evento (cnpj_organizador, nome, descricao, data_hora_inicio, data_hora_fim, local, status_evento, capacidade_maxima_publico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, evento.getCnpjOrganizador());
            stmt.setString(2, evento.getNome());
            stmt.setString(3, evento.getDescricao());
            stmt.setTimestamp(4, new java.sql.Timestamp(evento.getDataHoraInicio().getTime()));
            stmt.setTimestamp(5, new java.sql.Timestamp(evento.getDataHoraFim().getTime()));
            stmt.setString(6, evento.getLocal());
            stmt.setString(7, evento.getStatusEvento().name());
            stmt.setInt(8, evento.getCapacidadeMaximaPublico());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("Evento '" + evento.getNome() + "' criado com sucesso! ID: " + generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("LOG DE ERRO: Falha ao salvar o evento.");
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar evento no banco de dados.", e);
        }
    }

    public List<Evento> listarTodos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM evento";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                eventos.add(criarEventoDeResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
    
    // Adicione os outros métodos como buscarPorId e atualizar se precisar...

    private Evento criarEventoDeResultSet(ResultSet rs) throws SQLException {
        Evento evento = new Evento();
        evento.setIdEvento(rs.getInt("id_evento"));
        evento.setCnpjOrganizador(rs.getString("cnpj_organizador"));
        evento.setNome(rs.getString("nome"));
        evento.setDescricao(rs.getString("descricao"));
        evento.setDataHoraInicio(rs.getTimestamp("data_hora_inicio"));
        evento.setDataHoraFim(rs.getTimestamp("data_hora_fim"));
        evento.setLocal(rs.getString("local"));
        evento.setStatusEvento(StatusEvento.valueOf(rs.getString("status_evento")));
        evento.setCapacidadeMaximaPublico(rs.getInt("capacidade_maxima_publico"));
        return evento;
    }
}