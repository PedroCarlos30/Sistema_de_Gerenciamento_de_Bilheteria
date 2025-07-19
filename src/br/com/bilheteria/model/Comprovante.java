package br.com.bilheteria.model;

import java.sql.Timestamp;
import java.util.List;

public class Comprovante {
    private int idComprovante;
    private Timestamp dataHora;
    private float valorTotal;
    private String formaPagamento;
    private List<ItemVenda> itens;

    private static int contador = 1;

    public Comprovante(Venda venda) {
        this.idComprovante = contador++;
        this.dataHora = venda.getDataHoraVenda();
        this.valorTotal = venda.getValorTotal();
        this.formaPagamento = venda.getFormaPagamento();
        this.itens = venda.getItensVenda();
    }

    public void imprimirComprovante() {
        System.out.println("====== COMPROVANTE DE VENDA ======");
        System.out.println("ID: " + idComprovante);
        System.out.println("Data/Hora: " + dataHora);
        System.out.println("Valor Total: R$ " + valorTotal);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("--- Itens da Venda ---");
        for (ItemVenda item : itens) {
            System.out.println(item);
        }
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

    public List<ItemVenda> getItens() {
        return itens;
    }
}
