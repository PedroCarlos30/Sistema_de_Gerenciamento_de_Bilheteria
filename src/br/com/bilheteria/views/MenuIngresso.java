package br.com.bilheteria.views;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import br.com.bilheteria.models.DAO.IngressoDAO;
import br.com.bilheteria.models.entidade.Ingresso;
import br.com.bilheteria.models.entidade.StatusIngresso;

public class MenuIngresso {

    private static IngressoDAO ingressoDAO = new IngressoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        
        do {
            System.out.println("\n--- Menu Principal de Ingressos ---");
            System.out.println("1. Criar Novo Ingresso");
            System.out.println("2. Listar Ingressos");
            System.out.println("3. Validar Ingresso");
            System.out.println("4. Cancelar Ingresso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpa o buffer de entrada incorreta
                continue; // Volta para o início do loop
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarNovoIngresso(scanner);
                    break;
                case 2:
                    listarIngressos();
                    break;
                case 3:
                    validarIngresso(scanner);
                    break;
                case 4:
                    cancelarIngresso(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void criarNovoIngresso(Scanner scanner) {
        System.out.println("\n--- Criar Novo Ingresso ---");
        try {
            Ingresso novoIngresso = new Ingresso();
            
            novoIngresso.setCodigoUnico(UUID.randomUUID().toString());
            // O Timestamp agora é gerado e definido dentro da classe Ingresso
            novoIngresso.setDataHoraVenda(new java.sql.Timestamp(System.currentTimeMillis()));
            
            System.out.print("Digite o preço do ingresso (ex: 150,00): ");
            double preco = scanner.nextDouble();
            scanner.nextLine();
            novoIngresso.setPrecoVenda(preco);
            
            novoIngresso.setStatusIngresso(StatusIngresso.VALIDO);
            
            System.out.print("Digite o ID da Venda: ");
            int idVenda = scanner.nextInt();
            
            System.out.print("Digite o ID do Evento: ");
            int idEvento = scanner.nextInt();
            scanner.nextLine();

            ingressoDAO.salvar(novoIngresso, idVenda, idEvento);
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao criar o ingresso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarIngressos() {
        System.out.println("\n--- Lista de Ingressos ---");
        try {
            List<Ingresso> ingressos = ingressoDAO.listarTodos();
            if (ingressos.isEmpty()) {
                System.out.println("Nenhum ingresso cadastrado.");
                return;
            }
            for (Ingresso ingresso : ingressos) {
                System.out.println(ingresso); 
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao listar os ingressos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validarIngresso(Scanner scanner) {
        System.out.println("\n--- Validar Ingresso ---");
        System.out.print("Digite o ID do ingresso para validar: ");
        int idIngresso = scanner.nextInt();
        scanner.nextLine();

        try {
            Ingresso ingresso = ingressoDAO.buscarPorId(idIngresso);
            if (ingresso == null) {
                System.out.println("Ingresso com ID " + idIngresso + " não encontrado.");
                return;
            }

            ingresso.setStatusIngresso(StatusIngresso.UTILIZADO);
            ingressoDAO.atualizar(ingresso);
            
        } catch (Exception e) {
             System.err.println("Ocorreu um erro ao validar o ingresso: " + e.getMessage());
             e.printStackTrace();
        }
    }

    private static void cancelarIngresso(Scanner scanner) {
        System.out.println("\n--- Cancelar Ingresso ---");
        System.out.print("Digite o ID do ingresso para cancelar: ");
        int idIngresso = scanner.nextInt();
        scanner.nextLine();

        try {
            Ingresso ingresso = ingressoDAO.buscarPorId(idIngresso);
            if (ingresso == null) {
                System.out.println("Ingresso com ID " + idIngresso + " não encontrado.");
                return;
            }

            ingresso.setStatusIngresso(StatusIngresso.CANCELADO);
            ingressoDAO.atualizar(ingresso);

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao cancelar o ingresso: " + e.getMessage());
            e.printStackTrace();
        }
    }
}