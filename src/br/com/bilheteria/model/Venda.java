package br.com.bilheteria.model;

import java.sql.Timestamp;

public class Venda {
    private int idVenda;
    private Timestamp dataHoraVenda;
    private CategoriaIngresso categoria;
    private int quantidade;
    private float valorTotal;
    private String formaPagamento;
    private String statusPagamento;

    public Venda(int idVenda, Timestamp dataHoraVenda, CategoriaIngresso categoria, int quantidade, String formaPagamento) {
        this.idVenda = idVenda;
        this.dataHoraVenda = dataHoraVenda;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = "Pendente";
        calcularTotal();
    }

    public void calcularTotal() {
        this.valorTotal = (float) (quantidade * categoria.getPrecoBase());
    }

    public void confirmarVenda() {
        this.statusPagamento = "Confirmado";
        gerarComprovante();
    }

    public void cancelarVenda() {
        this.statusPagamento = "Cancelado";
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

    public CategoriaIngresso getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idVenda=" + idVenda +
                ", dataHoraVenda=" + dataHoraVenda +
                ", categoria=" + categoria.getNomeCategoria() +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", statusPagamento='" + statusPagamento + '\'' +
                '}';
    }
}
