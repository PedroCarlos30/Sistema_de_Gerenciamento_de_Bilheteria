package br.com.bilheteria.models.entidade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento {

    private int idEvento;
    private String cnpjOrganizador;
    private String nome;
    private String descricao;
    private Date dataHoraInicio;
    private Date dataHoraFim;
    private String local;
    private StatusEvento statusEvento;
    private int capacidadeMaximaPublico;

    public Evento() {}

    // Getters e Setters
    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }
    public String getCnpjOrganizador() { return cnpjOrganizador; }
    public void setCnpjOrganizador(String cnpjOrganizador) { this.cnpjOrganizador = cnpjOrganizador; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getDataHoraInicio() { return dataHoraInicio; }
    public void setDataHoraInicio(Date dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }
    public Date getDataHoraFim() { return dataHoraFim; }
    public void setDataHoraFim(Date dataHoraFim) { this.dataHoraFim = dataHoraFim; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public StatusEvento getStatusEvento() { return statusEvento; }
    public void setStatusEvento(StatusEvento statusEvento) { this.statusEvento = statusEvento; }
    public int getCapacidadeMaximaPublico() { return capacidadeMaximaPublico; }
    public void setCapacidadeMaximaPublico(int capacidadeMaximaPublico) { this.capacidadeMaximaPublico = capacidadeMaximaPublico; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Evento ID: " + idEvento + "\n" +
               "  - Nome: " + nome + "\n" +
               "  - Descrição: " + descricao + "\n" +
               "  - Início: " + sdf.format(dataHoraInicio) + "\n" +
               "  - Fim: " + sdf.format(dataHoraFim) + "\n" +
               "  - Local: " + local + "\n" +
               "  - Status: " + statusEvento + "\n" +
               "  - Capacidade: " + capacidadeMaximaPublico;
    }
}
