package br.com.bilheteria.model;

import java.sql.Timestamp;

public class Comprovante {
    private String idComprovante;
    private Venda vendaAssociada;
    private Timestamp dataEmissao;
    private String detalhesTransacao;
    private String codigoAutenticacao;

    public Comprovante(Venda venda) {
        this.vendaAssociada = venda;
        this.idComprovante = "COMP-" + venda.getIdVenda();
        this.dataEmissao = new Timestamp(System.currentTimeMillis());
        this.detalhesTransacao = venda.toString();
        this.codigoAutenticacao = gerarCodigoAutenticacao();
    }

    private String gerarCodigoAutenticacao() {
        return "AUTH" + System.currentTimeMillis();
    }

    public void imprimir() {
        System.out.println("====== COMPROVANTE DE VENDA ======");
        System.out.println("ID: " + idComprovante);
        System.out.println("Data: " + dataEmissao);
        System.out.println("Detalhes: " + detalhesTransacao);
        System.out.println("Código de Autenticação: " + codigoAutenticacao);
        System.out.println("==================================");
    }

    @Override
    public String toString() {
        return "Comprovante{" +
                "idComprovante='" + idComprovante + '\'' +
                ", dataEmissao=" + dataEmissao +
                ", codigoAutenticacao='" + codigoAutenticacao + '\'' +
                '}';
    }

    public String getIdComprovante() {
        return idComprovante;
    }

    public Venda getVendaAssociada() {
        return vendaAssociada;
    }

    public Timestamp getDataEmissao() {
        return dataEmissao;
    }

    public String getDetalhesTransacao() {
        return detalhesTransacao;
    }

    public String getCodigoAutenticacao() {
        return codigoAutenticacao;
    }
}
