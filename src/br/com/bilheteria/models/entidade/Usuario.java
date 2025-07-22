package br.com.bilheteria.models.entidade;

public abstract class Usuario {
    protected int idUsuario;
    protected String nome;
    protected String email;
    protected String senha;

    public Usuario(int idUsuario, String nome, String email, String senha){
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    /* Método para login */
    public boolean login(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }
    /* Método para logout */
    public void logout(){
        System.out.println("Usuário: "+ this.nome + " deslogado com sucesso.");
    }
    /* Método para atualizar perfil, verificando primeiro se a senha está correta. */
    public String atualizarPerfil(String nome, String email, String senha){
        if(this.senha.equals(senha)){       
            this.nome = nome;
            this.email = email;
            return "Perfil atualizado com sucesso.";
        } else{
            return "Senha incorreta. Não foi possível atualizar o perfil.";
        }
    }
    /* Método para alterar senha, verificando se a senha antiga está correta. */
    public String alterarSenha(String senhaAntiga, String novaSenha){
        if (this.senha.equals(senhaAntiga)){
            this.senha = novaSenha;
            return "Senha alterada com SUCESSO.";
        } else{
            return "Senha antiga INCORRETA.";
        }
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
