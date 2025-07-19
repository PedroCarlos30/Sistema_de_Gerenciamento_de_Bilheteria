package br.com.bilheteria.model;

import java.sql.Timestamp;

public class Comprovante {
    private int idComprovante;
    private Timestamp dataHora;
    private float valorTotal;
    private String formaPagamento;
    private String nomeCategoria;
    private int quantidade;
    private float precoUnitario;

    private static int contador = 1;

    public Comprovante(Venda venda) {
        this.idComprovante = contador++;
        this.dataHora = venda.getDataHoraVenda();
        this.valorTotal = venda.getValorTotal();
        this.formaPagamento = venda.getFormaPagamento();
        this.nomeCategoria = venda.getCategoria().getNomeCategoria();
        this.precoUnitario = (float) venda.getCategoria().getPrecoBase();
        this.quantidade = venda.getQuantidade();
    }

    public void imprimirComprovante() {
        System.out.println("====== COMPROVANTE DE VENDA ======");
        System.out.println("ID: " + idComprovante);
        System.out.println("Data/Hora: " + dataHora);
        System.out.println("Categoria de Ingresso: " + nomeCategoria);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Preço Unitário: R$" + precoUnitario);
        System.out.println("Total: R$" + valorTotal);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("==================================");
    }

    public int getIdComprovante() {
        return idComprovante;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }
}
