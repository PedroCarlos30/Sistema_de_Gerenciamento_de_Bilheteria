package br.com.bilheteria.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int idVenda;
    private Timestamp dataHoraVenda;
    private List<Ingresso> itens;
    private float valorTotal;
    private String formaPagamento;
    private StatusPagamento statusPagamento;

    public Venda(int idVenda, Timestamp dataHoraVenda, String formaPagamento) {
        this.idVenda = idVenda;
        this.dataHoraVenda = dataHoraVenda;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.itens = new ArrayList<>();
    }

    public boolean cancelarCompra() {
        if (statusPagamento == StatusPagamento.CONFIRMADO) {
            this.statusPagamento = StatusPagamento.CANCELADO;
            return true;
        }
        return false;
    }

    public boolean cancelarCompraPorId(int id) {
        return this.idVenda == id && cancelarCompra();
    }

    public void adicionarItem(Ingresso ingresso, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            itens.add(ingresso);
        }
        calcularTotal();
    }

    public void calcularTotal() {
        float total = 0;
        for (Ingresso ingresso : itens) {
            total += ingresso.getPrecoVenda();
        }
        this.valorTotal = total;
    }

    public void confirmarVenda() {
        this.statusPagamento = StatusPagamento.CONFIRMADO;
        gerarComprovante();
    }

    public Comprovante gerarComprovante() {
        return new Comprovante(this);
    }

    public int getIdVenda() {
        return idVenda;
    }

    public Timestamp getDataHoraVenda() {
        return dataHoraVenda;
    }

    public List<Ingresso> getItens() {
        return itens;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idVenda=" + idVenda +
                ", dataHoraVenda=" + dataHoraVenda +
                ", quantidadeItens=" + itens.size() +
                ", valorTotal=" + valorTotal +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", statusPagamento=" + statusPagamento +
                '}';
    }
}
