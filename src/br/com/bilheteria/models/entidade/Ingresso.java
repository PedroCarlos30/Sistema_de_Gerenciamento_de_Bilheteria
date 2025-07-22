package br.com.bilheteria.models.entidade;

import java.sql.Timestamp;

// O enum foi removido daqui e colocado em seu próprio arquivo.

public class Ingresso {

    private int idIngresso; // Será 0 por padrão, o banco de dados irá gerar o valor.
    private String codigoUnico;
    private Timestamp dataHoraVenda;
    private StatusIngresso statusIngresso;
    private Evento evento;
    private Venda venda;
    private double precoVenda;

    // Construtor principal foi removido pois não precisamos mais dele.
    // Usaremos o construtor vazio e os setters.

    public Ingresso() {
        // O construtor vazio não precisa fazer nada.
        // Os valores serão definidos através dos métodos 'set'.
    }

    // Métodos de negócio permanecem os mesmos
    public boolean validarNoEvento() {
        if (this.statusIngresso == StatusIngresso.VALIDO) { // Ajustado de VENDIDO para VALIDO
            this.statusIngresso = StatusIngresso.UTILIZADO;
            String nomeEvento = (evento != null) ? evento.getNome() : "Evento Desconhecido";
            System.out.println("Ingresso " + codigoUnico + " validado com sucesso para o evento " + nomeEvento + ".");
            return true;
        } else {
            System.out.println("Ingresso " + codigoUnico + " não pode ser validado. Status atual: " + this.statusIngresso);
            return false;
        }
    }

    public void cancelar() {
        if (this.statusIngresso != StatusIngresso.UTILIZADO && this.statusIngresso != StatusIngresso.CANCELADO) {
            this.statusIngresso = StatusIngresso.CANCELADO;
            System.out.println("Ingresso " + codigoUnico + " cancelado.");
        } else {
            System.out.println("Não é possível cancelar o ingresso " + codigoUnico + " pois ele já está " + this.statusIngresso + ".");
        }
    }
    
    @Override
    public String toString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataVendaFormatada = (dataHoraVenda != null) ? sdf.format(dataHoraVenda) : "N/A";
        return "Ingresso ID: " + idIngresso + "\n" +
               "  - Código: " + codigoUnico + "\n" +
               "  - Evento: " + (evento != null ? evento.getNome() : "N/A") + "\n" +
               "  - Preço: R$" + String.format("%.2f", precoVenda) + "\n" +
               "  - Status: " + statusIngresso + "\n" +
               "  - Data da Venda: " + dataVendaFormatada + "\n" +
               "------------------------------------";
    }

    // --- GETTERS E SETTERS ---

    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public Timestamp getDataHoraVenda() {
        return dataHoraVenda;
    }

    public void setDataHoraVenda(Timestamp dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    public StatusIngresso getStatusIngresso() {
        return statusIngresso;
    }

    public void setStatusIngresso(StatusIngresso statusIngresso) {
        this.statusIngresso = statusIngresso;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}
