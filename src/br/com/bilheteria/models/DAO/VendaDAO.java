package br.com.bilheteria.models.DAO;

import br.com.bilheteria.models.entidade.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    public void salvar(Venda venda) {
        String sql = "INSERT INTO venda (cpf_cliente, data_hora_venda, valor_total, forma_pagamento, status_venda) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, venda.getCpfCliente());
            stmt.setTimestamp(2, new java.sql.Timestamp(venda.getDataHoraVenda().getTime()));
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setString(4, venda.getFormaPagamento());
            stmt.setString(5, venda.getStatusVenda());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("Venda criada com sucesso! ID: " + generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar venda.", e);
        }
    }
    
    public List<Venda> listarTodas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setCpfCliente(rs.getString("cpf_cliente"));
                venda.setDataHoraVenda(rs.getTimestamp("data_hora_venda"));
                venda.setValorTotal(rs.getDouble("valor_total"));
                venda.setFormaPagamento(rs.getString("forma_pagamento"));
                venda.setStatusVenda(rs.getString("status_venda"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }
}