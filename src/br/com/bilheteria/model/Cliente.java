package br.com.bilheteria.model;

import java.util.List;

public class Cliente extends Usuario{

    private String cpf;
    private int dataNascimento;
    private String telefone;
    private List<Venda> historicoDeCompras;

    public Cliente(int idUsuario, String nome, String email, String senha, String cpf, int dataNascimento, String telefone) {
        super(idUsuario, nome, email, senha);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public List<Evento> visualizarEventosDisponiveis(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            // Retornar todos os eventos disponíveis se não houver filtro
        } else {
            // Implementar lógica para visualizar eventos disponíveis com base no filtro
        }
        return null; // Retornar lista de eventos filtrados
    }

    public String getCpf() {
        return cpf;
    }

    public int getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<Venda> getHistoricoDeCompras() {
        return historicoDeCompras;
    }
}
/*- cpf: String
- dataNascimento: int
- telefone: String
- historicoDeCompras: List<Venda>

+cadastroClientes(dados:Object)
+ visualizarEventosDisponiveis(filtro: String):
List<Evento>
+ visualizarHistoricoCompras():List<Venda>
+ toString(): String
+ excluirConta(): Boolean*/
