package br.com.bilheteria.models.entidade;

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

    // Construtor padrão (necessário para DAO)
    public Venda() {
        this.itens = new ArrayList<>();
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public Venda(int idVenda, Timestamp dataHoraVenda, String formaPagamento) {
        this.idVenda = idVenda;
        this.dataHoraVenda = dataHoraVenda;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.itens = new ArrayList<>();
    }

    public boolean cancelarCompra() {
        if (statusPagamento == StatusPagamento.APROVADA) {
            this.statusPagamento = StatusPagamento.CANCELADA;
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

    /* public void confirmarVenda() {
        this.statusPagamento = StatusPagamento.CONFIRMADO;
        gerarComprovante();
    }
 */
    /* public Comprovante gerarComprovante() {
        return new Comprovante(this);
    } */

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Timestamp getDataHoraVenda() {
        return dataHoraVenda;
    }

    public void setDataHoraVenda(Timestamp dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    public List<Ingresso> getItens() {
        return itens;
    }

    public void setItens(List<Ingresso> itens) {
        this.itens = itens;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idVenda=" + idVenda +
                ", dataHoraVenda=" + dataHoraVenda +
                ", quantidadeItens=" + (itens != null ? itens.size() : 0) +
                ", valorTotal=" + valorTotal +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", statusPagamento=" + statusPagamento +
                '}';
    }
}
