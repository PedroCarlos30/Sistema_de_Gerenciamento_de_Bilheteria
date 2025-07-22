// package br.com.bilheteria.controllers;

// import br.com.bilheteria.models.DAO.LoteIngressoDAO;
// import br.com.bilheteria.models.entidade.LoteIngresso;

// import java.util.ArrayList;
// import java.util.Scanner;

// public class LoteIngressoControle {

//     public static void exibirMenu() {
//         Scanner scanner = new Scanner(System.in);
//         int opcao;
//         do {
//             System.out.println("\n--- Menu de Controle de Lotes de Ingresso ---");
//             System.out.println("1. Listar Lotes");
//             System.out.println("2. Visualizar Lote por ID");
//             System.out.println("3. Deletar Lote");
//             System.out.println("0. Voltar");
//             System.out.print("Escolha uma opção: ");
//             opcao = scanner.nextInt();
//             scanner.nextLine(); // Consumir quebra de linha

//             switch (opcao) {
//                 case 1:
//                     listarLotes();
//                     break;
//                 case 2:
//                     System.out.print("Digite o ID do lote: ");
//                     int id = scanner.nextInt();
//                     visualizarLote(id);
//                     break;
//                 case 3:
//                     System.out.print("Digite o ID do lote para deletar: ");
//                     int idDel = scanner.nextInt();
//                     deletarLote(idDel);
//                     break;
//                 case 0:
//                     System.out.println("Voltando ao menu anterior.");
//                     break;
//                 default:
//                     System.out.println("Opção inválida.");
//             }
//         } while (opcao != 0);
//         scanner.close();
//     }

//     public static void listarLotes() {
//     LoteIngressoDAO dao = new LoteIngressoDAO();
//     ArrayList<LoteIngresso> lotes = dao.listarTodos();
//     if (lotes.isEmpty()) {
//         System.out.println("Ainda não há lotes cadastrados.\n");
//     } else {
//         for (LoteIngresso lote : lotes) {
//             System.out.println(lote);
//         }
//     }
//     }

//     public static void visualizarLote(int idLote) {
//     LoteIngressoDAO dao = new LoteIngressoDAO();
//     LoteIngresso lote = dao.buscarPorId(idLote);
//     if (lote == null) {
//         System.out.println("Esse lote não existe.\n");
//     } else {
//         System.out.println("Informações do lote:");
//         System.out.println(lote);
//     }
//     }

//     public static void deletarLote(int idLote) {
//         LoteIngressoDAO dao = new LoteIngressoDAO();
//         LoteIngresso lote = dao.buscarPorId(idLote);
//         if (lote == null) {
//             System.out.println("Esse lote não existe.\n");
//         } else {
//             dao.deletar(idLote);
//             System.out.println("Lote deletado com sucesso!\n");
//         }
//     }
// }