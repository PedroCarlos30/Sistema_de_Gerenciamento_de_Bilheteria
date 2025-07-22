package br.com.bilheteria.models.entidade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Evento{
    private int idEvento;
    private String nome;
    private String descricao;
    private Timestamp dataHoraInicio;
    private Timestamp dataHoraFim;
    private String local;
    private StatusEvento statusEvento;
    private int capacidadeMaximaPublico;
    private String cnpjOrganizador;

    private List<Ingresso> ingressosVendidos;

    private static int nextId = 1;

    public Evento(String nome, String descricao, Timestamp dataHoraInicio, Timestamp dataHoraFim, String local,int capacidadeMaximaPublico) {
        this.idEvento = nextId++;
        this.nome = nome;
        this.descricao = descricao;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.local = local;
        this.statusEvento = StatusEvento.AGENDADO;
        this.capacidadeMaximaPublico = capacidadeMaximaPublico;
        /* this.lotesDeIngresso = new ArrayList<>(); */
        this.ingressosVendidos = new ArrayList<>();
    }

    public Evento(){
        
    }

    public static Evento criarEvento(Organizador organizador, String nome, String descricao, Timestamp dataHoraInicio,Timestamp dataHoraFim, String local, int capacidadeMaximaPublico) {
        if (organizador == null) {
            throw new IllegalArgumentException("Organizador não pode ser nulo ao criar um evento.");
        }
        if (dataHoraInicio.after(dataHoraFim)) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
        }

        System.out.println("Evento criado pelo organizador: " + organizador.getNome());
        return new Evento(nome, descricao, dataHoraInicio, dataHoraFim, local, capacidadeMaximaPublico);
    }

    /* public LoteIngresso adicionarLote(CategoriaIngresso categoria, double preco, int quantidade) {
        if (quantidade <= 0 || preco <= 0) {
            throw new IllegalArgumentException("Quantidade e preço do lote devem ser positivos.");
        }
        LoteIngresso novoLote = new LoteIngresso(categoria, preco, quantidade);
        this.lotesDeIngresso.add(novoLote);
        System.out.println("Lote de ingressos adicionando ao evento '" + this.nome + "': " + novoLote);
        return novoLote;
    } */

    /* public boolean removerLote(LoteIngresso lote) {
        if (lote == null) {
            System.out.println("Lote inválido para remoção.");
            return false;
        }
        boolean removido = this.lotesDeIngresso.remove(lote);
        if (removido) {
            System.out.println("Lote de ingressos removido do evento '" + this.nome + "': " + lote);
        }
        return removido;
    } */

    public void atualizarDetalhesEvento(String novoNome, String novaDescricao, Timestamp novaDataHoraInicio,Timestamp novaDataHoraFim, String novoLocal, int novaCapacidadeMaximaPublico) {
        if (this.statusEvento == StatusEvento.REALIZADO || this.statusEvento == StatusEvento.EM_ANDAMENTO || (this.dataHoraInicio != null && new Timestamp(System.currentTimeMillis()).after(this.dataHoraInicio))) {
            System.out.println("Erro: Não é possível editar o evento após sua data/hora de início ou se já estiver em andamento/realizado");
            return;
        }

        if (novoNome != null && !novoNome.trim().isEmpty()) { this.nome = novoNome; }
        if (novaDescricao != null && !novaDescricao.trim().isEmpty()) { this.descricao = novaDescricao; }
        if (novaDataHoraInicio != null) { this.dataHoraInicio = novaDataHoraInicio; }
        if (novaDataHoraFim != null) { this.dataHoraFim = novaDataHoraFim; }
        if (novoLocal != null && !novoLocal.trim().isEmpty()) { this.local = novoLocal; }
        if (novaCapacidadeMaximaPublico > 0) { this.capacidadeMaximaPublico = novaCapacidadeMaximaPublico; }

        System.out.println("Detalhes do evento '" + this.nome + "' atualizados com sucesso.");
    }

    public void altrerarStatus(StatusEvento novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("Novo status não pode ser nulo.");
        }
        /* if (novoStatus == StatusEvento.VENDAS_ABERTAS && (this.lotesDeIngresso == null || this.lotesDeIngresso.isEmpty())) {
            System.out.println("Erro: Não é possível abrir vendas sem ter ao menos um Lote de Ingresso cadastrado.");
            return;
        } */
        this.statusEvento = novoStatus;
        System.out.println("Status do evento '" + this.nome + "' alterado para: " + novoStatus);
    }

    public double calcularReceitaTotal() {
        double receitaTotal = 0;
        for (Ingresso ingresso : ingressosVendidos) {
            receitaTotal += ingresso.getPrecoVenda();
        }
        return receitaTotal;
    }
    
    public boolean cancelarEvento() {
        if (this.statusEvento == StatusEvento.CANCELADO) {
            System.out.println("Evento '" + this.nome + "' já está cancelado.");
            return false;
        }
        this.statusEvento = StatusEvento.CANCELADO;
        System.out.println("Evento '" + this.nome + "' cancelado com sucesso.");
        return true;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String inicio = (dataHoraInicio != null) ? sdf.format(dataHoraInicio) : "N/A";
        String fim = (dataHoraFim != null) ? sdf.format(dataHoraFim) : "N/A";
        return "Evento:" + "\n" +
                "idEvento=" + idEvento + "\n" +
                "nome='" + nome + '\'' + "\n" +
                "descricao='" + descricao + '\'' + "\n" +
                "dataHoraInicio=" + inicio + "\n" +
                "dataHoraFim=" + fim + "\n" +
                "local='" + local + '\'' + "\n" +
                "statusEvento=" + statusEvento + "\n" +
                "capacidadeMaximaPublico=" + capacidadeMaximaPublico + "\n" +
                /* "numLotesIngresso=" + lotesDeIngresso.size() + "\n" + */
                "numIngressosVendidos=" + ingressosVendidos.size();
    }

    public int getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(int idEvento){
        this.idEvento = idEvento;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public Timestamp getDataHoraInicio() {
        return dataHoraInicio;
    }
    public void setDataHoraInicio(Timestamp dataHoraInicio){
        this.dataHoraInicio = dataHoraInicio;
    }
    public Timestamp getDataHoraFim() {
        return dataHoraFim;
    }
    public void setDataHoraFim(Timestamp dataHoraFim){
        this.dataHoraFim = dataHoraFim;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local){
        this.local = local;
    }
    public StatusEvento getStatusEvento() {
        return statusEvento;
    }
    public void setStatusEvento(StatusEvento statusEvento){
        this.statusEvento = statusEvento;
    }
    public int getCapacidadeMaximaPublico() {
        return capacidadeMaximaPublico;
    }
    public void setCapacidadeMaximaPublico(int capacidadeMaximaPublico){
        this.capacidadeMaximaPublico = capacidadeMaximaPublico;
    }
    /* public List<LoteIngresso> getLotesDeIngresso() {
        return new ArrayList<>(lotesDeIngresso);
    } */
    public List<Ingresso> getIngressosVendidos() {
        return new ArrayList<>(ingressosVendidos);
    }
    public String getCnpjOrganizador() {
        return cnpjOrganizador;
    }
    public void setCnpjOrganizador(String cnpjOrganizador) {
        this.cnpjOrganizador = cnpjOrganizador;
    }
}
