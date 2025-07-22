package Sistema_de_Gerenciamento_de_Bilheteria;

import java.util.List;
import java.util.ArrayList;
import Sistema_de_Gerenciamento_de_Bilheteria.Usuario;

public class Cliente extends Usuario{
    private String cpf;
    private int dataNascimento;
    private String telefone; 
    private List<Venda> historicDeCompras = new ArrayList<>(); 


    public void cadastroClientes(int idUsuario, String nome, String email, String senha, String cpf, int dataNascimento, String telefone){
        super(idUsuario, nome, email, senha);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public ArrayList<Evento> visualizarEventosDisponiveis(String filtro){
        ArrayList<Evento> eventosFiltrados = new ArrayList<>();
        return eventosFiltrados;
    }
    
    public ArrayList<Venda> visualizarHistoricoDeCompras() {
        return new ArrayList<>(historicDeCompras);
    }

    public String toString(){
        return "Cliente{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", telefone='" + telefone + '\'' +
                '}';
    }
    
    public bollean excluirConta(){
        return true;
    }
    
}
