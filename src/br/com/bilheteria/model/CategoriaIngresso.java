package br.com.bilheteria.model;

public class CategoriaIngresso {
    private int idCategoria;
    private String nome;
    private String descricao;
    private double precoBase;

    public CategoriaIngresso(int idCategoria, String nome, String descricao, double precoBase) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    public static CategoriaIngresso criarCategoria(int id, String nome, String descricao, double preco) {
        return new CategoriaIngresso(id, nome, descricao, preco);
    }

    public void atualizarDados(String novoNome, String novaDescricao, double novoPreco) {
        if (novoNome != null && !novoNome.trim().isEmpty()) this.nome = novoNome;
        if (novaDescricao != null && !novaDescricao.trim().isEmpty()) this.descricao = novaDescricao;
        if (novoPreco > 0) this.precoBase = novoPreco;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return precoBase;
    }

    @Override
    public String toString() {
        return "CategoriaIngresso{" +
                "idCategoria=" + idCategoria +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoBase=" + precoBase +
                '}';
    }
}
