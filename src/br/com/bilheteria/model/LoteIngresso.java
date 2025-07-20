package br.com.bilheteria.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LoteIngresso {
    private int idLote;
    private double preco;
    private int quantidadeTotalDisponivel;
    private int quantidadeVendida;

    private CategoriaIngresso categoria;

    private static int nextIdLote = 1;

    public LoteIngresso(CategoriaIngresso categoria, double preco, int quantidadeTotalDisponivel) {
        this.idLote = nextIdLote++;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeTotalDisponivel = quantidadeTotalDisponivel;
        this.quantidadeVendida = 0;
    }

    public void atualizarPreco(double novoPreco) {
        if (novoPreco <= 0) {
            System.out.println("Erro: O novo preço deve ser positivo.");
            return;
        }
        this.preco = novoPreco;
        System.out.println("Preço do lote " + idLote + " atualizado para R$" + String.format("%.2f", novoPreco));
    }

    public void atualizarQuantidadeDisponivel(int novaQuantidade) {
        if (novaQuantidade < this.quantidadeVendida) {
            System.out.println("Erro: A nova quantidade disponível (" + novaQuantidade + ") não pode ser menor que a quantidade já vendida (" + this.quantidadeVendida + ").");
            return;
        }
        if (novaQuantidade < 0) {
            System.out.println("Erro: A nova quantidade disponível deve ser positiva.");
            return;
        }
        this.quantidadeTotalDisponivel = novaQuantidade;
        System.out.println("Quantidade total disponível do lote " + idLote + " atualizado para " + novaQuantidade + ".");
    }

    public boolean registrarVenda(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Erro: A quantidade para registrar venda deve ser positiva.");
            return false;
        }
        if ( temDisponibilidade(quantidade)) {
            this.quantidadeVendida += quantidade;
            System.out.println(quantidade + " ingressos registrados como vendidos do lote " + idLote + ".");
            return true;
        } else {
            System.out.println("Não foi possível registrar a venda de " + quantidade + " ingressos para o lote " + idLote + ". Disponibilidade insuficiente.");
            return false;
        }
    }

    public boolean temDisponibilidade(int quantidadeDesejada) {
        if (quantidadeDesejada <= 0) {
            return false;
        }
        return (this.quantidadeTotalDisponivel - this.quantidadeVendida) >= quantidadeDesejada;
    }

    @Override
    public String toString() {
        return "LoteIngresso{" +
                "ID=" + idLote +
                ", categoria='" + (categoria != null ? categoria.getNomeCategoria() : "N/A") + '\'' +
                ", preco=R$" + String.format("%.2f", preco) +
                ", qtdTotalDisponivel=" + quantidadeTotalDisponivel +
                ", qtdVendida=" + quantidadeVendida +
                ", disponivelParaVenda=" + (quantidadeTotalDisponivel - quantidadeVendida) +
                '}';
    }

    public int getIdLote() {
        return idLote;
    }
    public double getPreco() {
        return preco;
    }
    public int getQuantidadeTotalDisponivel() {
        return quantidadeTotalDisponivel;
    }
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public int getQuantidadeDisponivel() {
        return quantidadeTotalDisponivel - quantidadeVendida;
    }
    public CategoriaIngresso getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        return true;
        if (o == null || getClass() != o.getClass()) 
        return false;
        LoteIngresso that = (LoteIngresso) o;
        return idLote == that.idLote;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLote);
    }
}