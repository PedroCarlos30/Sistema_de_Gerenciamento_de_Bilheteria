package br.com.bilheteria.model;

import java.util.ArrayList;
import java.util.List;
public class Organizador extends Usuario{   
    private String cnpj;
    private String nomeEmpresa;
    private String telefoneComercial;
    private List<Evento> eventosCriados;

    public Organizador(int idUsuario, String nome, String email, String senha, String cnpj, String nomeEmpresa, String telefoneComercial) {
        super(idUsuario, nome, email, senha);
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.telefoneComercial = telefoneComercial;
        this.eventosCriados = new ArrayList<>();
    }
 
    public void cadastroOrganizador(DadosOrganizador dados) {
        // Lógica para cadastrar o organizador
    }

    public Relatorio visualizarRelatorioVendas(int idEvento) {
        // Lógica para visualizar o relatório de vendas
        return new Relatorio();
    }

    public void publicarEvento(int idEvento) {
        // Lógica para publicar o evento
    }

    public void encerrarVendasEvento(int idEvento) {
        // Lógica para encerrar as vendas do evento
    }

    public String toString() {
        return "Organizador{" +
                    "idUsuario=" + idUsuario +
                    ", nome='" + nome + '\'' +
                    ", email='" + email + '\'' +
                    ", cnpj='" + cnpj + '\'' +
                    ", nomeEmpresa='" + nomeEmpresa + '\'' +
                    ", telefoneComercial='" + telefoneComercial + '\'' +
                    ", eventosCriados=" + eventosCriados +
                    '}';
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public List<Evento> getEventosCriados() {
        return eventosCriados;
    }
}