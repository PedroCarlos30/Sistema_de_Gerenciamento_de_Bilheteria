package br.com.bilheteria.model;

public class ItemVenda {
    private String nomeIngresso;
    private int quantidade;
    private float precoUnitario;

    public ItemVenda(String nomeIngresso, int quantidade, float precoUnitario) {
        this.nomeIngresso = nomeIngresso;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public float getSubtotal() {
        return quantidade * precoUnitario;
    }

    public String getNomeIngresso() {
        return nomeIngresso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public String toString() {
        return nomeIngresso + " x" + quantidade + " - R$" + precoUnitario + " (Subtotal: R$" + getSubtotal() + ")";
    }
}
