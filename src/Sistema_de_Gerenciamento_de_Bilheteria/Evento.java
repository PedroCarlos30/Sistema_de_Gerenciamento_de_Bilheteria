package Sistema_de_Gerenciamento_de_Bilheteria;
import java.util.Scanner;

import Sistema_de_Gerenciamento_de_Bilheteria.DemoEvento.LoteIngresso;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum StatusEvento {
    AGENDADO,
    VENDAS_ABERTAS,
    EM_ANDAMENTO,
    REALIZADO,
    CANCELADO,
    ADIADO,
    VENDAS_ENCERRADAS
}

public class Evento {
    private int idEvento;
    private String nome;
    private String descricao;
    private Timestamp dataHoraInicio;
    private Timestamp dataHoraFim;
    private String local;
    private StatusEvento statusEvento;
    private int capacidadeMaximaPublico;

    private List<LoteIngresso> lotesDeIngresso;
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
        this.lotesDeIngresso = new ArrayList<>();
        this.ingressosVendidos = new ArrayList<>();
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

    public LoteIngresso adicionarLote(CategoriaIngresso categoria, double preco, int quantidade) {
        if (quantidade <= 0 || preco <= 0) {
            throw new IllegalArgumentException("Quantidade e preço do lote devem ser positivos.");
        }
        LoteIngresso novoLote = new LoteIngresso(categoria, preco, quantidade);
        this.lotesDeIngresso.add(novoLote);
        System.out.println("Lote de ingressos adicionando ao evento '" + this.nome + "': " + novoLote);
        return novoLote;
    }

    public boolean removerLote(LoteIngresso lote) {
        if (lote == null) {
            System.out.println("Lote inválido para remoção.");
            return false;
        }
        boolean removido = this.lotesDeIngresso.remove(lote);
        if (removido) {
            System.out.println("Lote de ingressos removido do evento '" + this.nome + "': " + lote.getLote());
        }
        return removido;
    }

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
        if (novoStatus == StatusEvento.VENDAS_ABERTAS && (this.lotesDeIngresso == null || this.lotesDeIngresso.isEmpty())) {
            System.out.println("Erro: Não é possível abrir vendas sem ter ao menos um Lote de Ingresso cadastrado.");
            return;
        }
        this.statusEvento = novoStatus;
        System.out.println("Status do evento '" + this.nome + "' alterado para: " + novoStatus);
    }

    public boolean publicarEvento() {
        if (this.statusEvento == StatusEvento.VENDAS_ABERTAS) {
            System.out.println("Evento '" + this.nome + "' já está publicando com vendas abertas.");
            return false;
        }
        try {
            alterarStatus(StatusEvento.VENDAS_ABERTAS);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao publicar evento: " + e.getMessage());
            return false;
        }
    }

    public boolean encerrarVendas() {
        if (this.statusEvento == StatusEvento.VENDAS_ENCERRADAS || this.statusEvento == StatusEvento.REALIZADO || this.statusEvento == StatusEvento.CANCELADO) {
            System.out.println("Vendas para o evento '" + this.nome + "' já estão encerradas ou o evento já foi realizado/cancelado.");
            return false;
        }
        alterarStatus(StatusEvento.VENDAS_ENCERRADAS);
        return true;
    }

    public List<Ingresso> obterIngressosVendidos() {
        return new ArrayList<>(this.ingressosVendidos);
    }

    public double calcularReceitaTotal() {
        double receitaTotal = 0;
        for (Ingressso ingresso : ingressosVendidos) {
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

    public boolean venderIngresso(LoteIngresso lote, int quantidade) {
        if (this.statusEvento != StatusEvento.VENDAS_ABERTAS) {
            System.out.println("Não é possível vender ingressos para o evento '" + this.nome + "' com status: " + this.statusEvento);
            return false;
        }
        if (lote == null || !this.lotesDeIngresso.contains(lote)) {
            System.out.prinln("Lote de ingresso inválido ou não pertence a este evento.");
            return false;
        }
        if (quantidade <= 0) {
            System.out.prinln("A quantidade de ingressos para venda deve ser maior que zero.");
            return false;
        }
        if (lote.venderIngresso(quantidade)) {
            for (int i =0; i < quantidade; i++) {
                ingressosVendidos.add(new Ingresso(lote));
            }
            System.out.println(quantidade + "ingressos vendidos do lote " + lote.getIdLote() + " para o evento '" + this.nome + "'.");
            return true;
        } else {
            System.out.println("Não há ingressos suficientes no lote " + lote.getIdLote() + " para vender " + quantidade + ".");
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", local='" + local + '\'' +
                ", statusEvento=" + statusEvento +
                ", capacidadeMaximaPublico=" + capacidadeMaximaPublico +
                ", numLotesIngresso=" + lotesDeIngresso.size() +
                ", numIngressosVendidos=" + ingressosVendidos.size() +
                '}';
    }

    public int getIdEvento() {
        return idEvento;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public Timestamp getDataHoraInicio() {
        return dataHoraInicio;
    }
    public Timestamp getDataHoraFim() {
        return dataHoraFim;
    }
    public String getLocal() {
        return local;
    }
    public StatusEvento getStatusEvento() {
        return statusEvento;
    }
    public int getCapacidadeMaximaPublico() {
        return capacidadeMaximaPublico;
    }
    public List<LoteIngresso> getLotesDeIngresso() {
        return new ArrayList<>(lotesDeIngresso);
    }
    public List<Ingresso> getIngressosVendidos() {
        return new ArrayList<>(ingressosVendidos);
    }

    public static void main(String[] args) {
        Organizador organizador = new Organizador("Organizador Festas S.A."); [cite: 19]
        System.out.println(organizador);

        Timestamp inicio = Timestamp.valueOf("2025-12-10 19:00:00");
    }
}