package br.com.bilheteria.controllers;

import br.com.bilheteria.models.DAO.VendaDAO;
import br.com.bilheteria.models.entidade.Venda;
import java.util.List;

public class VendaControle {
    private VendaDAO vendaDAO;

    public VendaControle() {
        this.vendaDAO = new VendaDAO();
    }

    public void salvarVenda(Venda venda, String cpfCliente) {
        vendaDAO.salvar(venda, cpfCliente);
    }

    public Venda buscarVendaPorId(int idVenda) {
        return vendaDAO.buscarPorId(idVenda);
    }

    public List<Venda> listarTodasVendas() {
        return vendaDAO.listarTodas();
    }

    public void atualizarStatusVenda(int idVenda, String novoStatus) {
        vendaDAO.atualizarStatus(idVenda, novoStatus);
    }
}