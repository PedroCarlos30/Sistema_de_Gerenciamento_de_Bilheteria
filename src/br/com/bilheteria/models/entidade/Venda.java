package br.com.bilheteria.models.entidade;

import java.util.Date;

public class Venda {
    private int idVenda;
    private String cpfCliente;
    private Date dataHoraVenda;
    private double valorTotal;
    private String formaPagamento;
    private String statusVenda;

    public Venda() {}

    // Getters e Setters
    public int getIdVenda() { return idVenda; }
    public void setIdVenda(int idVenda) { this.idVenda = idVenda; }
    public String getCpfCliente() { return cpfCliente; }
    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }
    public Date getDataHoraVenda() { return dataHoraVenda; }
    public void setDataHoraVenda(Date dataHoraVenda) { this.dataHoraVenda = dataHoraVenda; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public String getStatusVenda() { return statusVenda; }
    public void setStatusVenda(String statusVenda) { this.statusVenda = statusVenda; }
}