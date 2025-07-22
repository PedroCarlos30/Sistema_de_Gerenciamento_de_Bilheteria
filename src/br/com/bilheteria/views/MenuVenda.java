package br.com.bilheteria.views;

import br.com.bilheteria.models.DAO.VendaDAO;
import br.com.bilheteria.models.entidade.Venda;
import java.util.Date;
import java.util.Scanner;

public class MenuVenda {
    private static VendaDAO vendaDAO = new VendaDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        do {
            System.out.println("\n--- Menu de Vendas ---");
            System.out.println("1. Registrar Nova Venda");
            System.out.println("2. Listar Vendas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1: criarNovaVenda(scanner); break;
                case 2: listarVendas(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void criarNovaVenda(Scanner scanner) {
        System.out.println("\n--- Registrar Nova Venda ---");
        try {
            Venda novaVenda = new Venda();
            System.out.print("Digite o CPF do Cliente (ex: 11122233344): ");
            novaVenda.setCpfCliente(scanner.nextLine());
            System.out.print("Valor total: ");
            novaVenda.setValorTotal(scanner.nextDouble());
            scanner.nextLine();
            System.out.print("Forma de Pagamento: ");
            novaVenda.setFormaPagamento(scanner.nextLine());
            
            novaVenda.setDataHoraVenda(new Date());
            novaVenda.setStatusVenda("APROVADA"); // Status padrão
            
            vendaDAO.salvar(novaVenda);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao criar a venda: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void listarVendas() {
        System.out.println("\n--- Lista de Vendas ---");
        // Implementar a listagem se desejar...
        System.out.println("Funcionalidade de listar vendas ainda não implementada.");
    }
}