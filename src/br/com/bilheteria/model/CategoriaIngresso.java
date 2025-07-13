package br.com.bilheteria.model;

public class CategoriaIngresso {
    private int idCategoria;
    private String nomeCategoria;
    private double precoBase;

    public CategoriaIngresso(int idCategoria, String nomeCategoria, double precoBase) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.precoBase = precoBase;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    @Override
    public String toString() {
        return "CategoriaIngresso{" +
                "idCategoria=" + idCategoria +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", precoBase=" + precoBase +
                '}';
    }
}
