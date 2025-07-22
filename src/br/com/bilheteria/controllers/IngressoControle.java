package br.com.bilheteria.controllers;

import br.com.bilheteria.models.DAO.IngressoDAO;
import br.com.bilheteria.models.entidade.Ingresso;

import java.util.ArrayList;
import java.util.Scanner;

public class IngressoControle {

    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu de Controle de Ingressos ---");
            System.out.println("1. Listar Ingressos");
            System.out.println("2. Visualizar Ingresso por ID");
            System.out.println("3. Deletar Ingresso");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    listarIngressos();
                    break;
                case 2:
                    System.out.print("Digite o ID do ingresso: ");
                    int id = scanner.nextInt();
                    visualizarIngresso(id);
                    break;
                case 3:
                    System.out.print("Digite o ID do ingresso para deletar: ");
                    int idDel = scanner.nextInt();
                    deletarIngresso(idDel);
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    public static void listarIngressos() {
        IngressoDAO ingressoDAO = new IngressoDAO();
        ArrayList<Ingresso> ingressos = (ArrayList<Ingresso>) ingressoDAO.listarTodos();
        if (ingressos.isEmpty()) {
            System.out.println("Ainda não há ingressos cadastrados.\n");
        } else {
            for (Ingresso ingresso : ingressos) {
                System.out.println(ingresso);
            }
        }
    }

    public static void visualizarIngresso(int idIngresso) {
        IngressoDAO ingressoDAO = new IngressoDAO();
        Ingresso ingresso = ingressoDAO.buscarPorId(idIngresso);
        if (ingresso == null) {
            System.out.println("Esse ingresso não existe.\n");
        } else {
            System.out.println("Informações do ingresso:");
            System.out.println(ingresso);
        }
    }

    public void deletar(int idIngresso) {
    String sql = "DELETE FROM ingresso WHERE id_ingresso = ?";
    try (Connection conn = Conexao.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idIngresso);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}