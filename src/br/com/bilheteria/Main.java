package br.com.bilheteria;

import java.util.Scanner;
import br.com.bilheteria.views.MenuEvento;
import br.com.bilheteria.views.MenuIngresso;
import br.com.bilheteria.views.MenuVenda;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n=== Sistema de Bilheteria ===");
            System.out.println("1. Menu de Eventos");
            System.out.println("2. Menu de Ingressos");
            System.out.println("3. Menu de Vendas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    MenuEvento.main(args);
                    break;
                case 2:
                    MenuIngresso.main(args);
                    break;
                case 3:
                    MenuVenda.main(args);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        scanner.close();
    }
}
