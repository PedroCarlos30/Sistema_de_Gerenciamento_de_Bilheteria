package br.com.bilheteria.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int idVenda;
    private Timestamp dataHoraVenda;
    private List<ItemVenda> itensVenda;
    private float valorTotal;
    private String formaPagamento;
    private String statusPagamento;

    public Venda(int idVenda, Timestamp dataHoraVenda, String formaPagamento) {
        this.idVenda = idVenda;
        this.dataHoraVenda = dataHoraVenda;
        this.formaPagamento = formaPagamento;
        this.itensVenda = new ArrayList<>();
        this.statusPagamento = "Pendente";
    }

    public void adicionarItem(ItemVenda item) {
        this.itensVenda.add(item);
        calcularTotal();
    }

    public void calcularTotal() {
        float total = 0;
        for (ItemVenda item : itensVenda) {
            total += item.getSubtotal();
        }
        this.valorTotal = total;
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

    @Override
    public String toString() {
        return "Venda ID: " + idVenda + ", Valor: " + valorTotal + ", Status: " + statusPagamento;
    }
}
