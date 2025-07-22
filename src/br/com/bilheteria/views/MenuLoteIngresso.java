/* package br.com.bilheteria.views;

import java.util.Scanner;
import java.util.List;
import br.com.bilheteria.models.entidade.CategoriaIngresso;
import br.com.bilheteria.models.entidade.LoteIngresso;
import br.com.bilheteria.models.DAO.LoteIngressoDAO;
import br.com.bilheteria.models.DAO.CategoriaIngressoDAO;

public class MenuLoteIngresso {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n--- Menu Principal de Lotes de Ingresso ---");
            System.out.println("1. Criar Nova Categoria de Ingresso (Necessário para criar Lotes)");
            System.out.println("2. Criar Novo Lote de Ingresso");
            System.out.println("3. Listar Lotes de Ingresso");
            System.out.println("4. Atualizar Preço de um Lote");
            System.out.println("5. Atualizar Quantidade Disponível de um Lote");
            System.out.println("6. Registrar Venda em um Lote");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    criarNovaCategoria(scanner);
                    break;
                case 2:
                    criarNovoLote(scanner);
                    break;
                case 3:
                    listarLotes();
                    break;
                case 4:
                    atualizarPrecoLote(scanner);
                    break;
                case 5:
                    atualizarQuantidadeLote(scanner);
                    break;
                case 6:
                    registrarVendaLote(scanner);
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

    // --- Métodos Auxiliares ---

    private static void criarNovaCategoria(Scanner scanner) {
        System.out.println("\n--- Criar Nova Categoria de Ingresso ---");
        System.out.print("Nome da Categoria: ");
        String nomeCategoria = scanner.nextLine();
        System.out.print("Descrição da Categoria: ");
        String descricaoCategoria = scanner.nextLine();

        CategoriaIngresso novaCategoria = new CategoriaIngresso(nomeCategoria, descricaoCategoria);
        new CategoriaIngressoDAO().salvar(novaCategoria);
        System.out.println("Categoria '" + novaCategoria.getNomeCategoria() + "' criada com sucesso! ID: " + novaCategoria.getIdCategoria());
        }   

    private static void criarNovoLote(Scanner scanner) {
        System.out.println("\n--- Criar Novo Lote de Ingresso ---");

        List<CategoriaIngresso> categorias = new CategoriaIngressoDAO().listarTodos();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria de ingresso cadastrada. Crie uma categoria primeiro.");
            return;
        }

        System.out.println("Categorias disponíveis:");
        for (CategoriaIngresso cat : categorias) {
            System.out.println("ID: " + (cat.getIdCategoria() - 1) + " - " + cat.getNomeCategoria());
        }
        System.out.print("Digite o ID da categoria para este lote: ");
        int idCategoriaEscolhida = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        CategoriaIngresso categoriaEscolhida = null;
        for (CategoriaIngresso cat : categorias) {
            if (cat.getIdCategoria() == idCategoriaEscolhida) {
                categoriaEscolhida = cat;
                break;
            }
        }

        if (categoriaEscolhida == null) {
            System.out.println("Categoria com ID " + idCategoriaEscolhida + " não encontrada.");
            return;
        }

        System.out.print("Preço por ingresso neste lote: R$");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade total de ingressos neste lote: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        try {
            LoteIngresso novoLote = new LoteIngresso(categoriaEscolhida, preco, quantidade);
            new LoteIngressoDAO().salvar(novoLote);
            System.out.println("Lote de ingresso criado com sucesso! " + novoLote);
        } catch (Exception e) {
            System.out.println("Erro ao criar lote: " + e.getMessage());
        }
    }

    private static void listarLotes() {
        System.out.println("\n--- Lista de Lotes de Ingresso ---");
        List<LoteIngresso> lotes = new LoteIngressoDAO().listarTodos();
        if (lotes.isEmpty()) {
            System.out.println("Nenhum lote de ingresso cadastrado.");
            return;
        }
        for (LoteIngresso lote : lotes) {
            System.out.println(lote);
        }
    }

    private static void atualizarPrecoLote(Scanner scanner) {
        System.out.println("\n--- Atualizar Preço de um Lote ---");
        System.out.print("Digite o ID do lote que deseja atualizar: ");
        int idLote = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        LoteIngresso loteParaAtualizar = new LoteIngressoDAO().buscarPorId(idLote);

        if (loteParaAtualizar == null) {
            System.out.println("Lote com ID " + idLote + " não encontrado.");
            return;
        }

        System.out.print("Digite o novo preço para o lote " + loteParaAtualizar.getIdLote() + ": R$");
        double novoPreco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        loteParaAtualizar.atualizarPreco(novoPreco);
        new LoteIngressoDAO().salvar(loteParaAtualizar);
        System.out.println("Preço atualizado com sucesso!");
    }

    private static void atualizarQuantidadeLote(Scanner scanner) {
        System.out.println("\n--- Atualizar Quantidade Disponível de um Lote ---");
        System.out.print("Digite o ID do lote que deseja atualizar: ");
        int idLote = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        LoteIngresso loteParaAtualizar = new LoteIngressoDAO().buscarPorId(idLote);

        if (loteParaAtualizar == null) {
            System.out.println("Lote com ID " + idLote + " não encontrado.");
            return;
        }

        System.out.print("Digite a nova quantidade total de ingressos para o lote " + loteParaAtualizar.getIdLote() + ": ");
        int novaQuantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        loteParaAtualizar.atualizarQuantidadeDisponivel(novaQuantidade);
        new LoteIngressoDAO().salvar(loteParaAtualizar);
        System.out.println("Quantidade atualizada com sucesso!");
    }

    private static void registrarVendaLote(Scanner scanner) {
        System.out.println("\n--- Registrar Venda em um Lote ---");
        System.out.print("Digite o ID do lote para registrar a venda: ");
        int idLote = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        LoteIngresso loteParaVenda = new LoteIngressoDAO().buscarPorId(idLote);

        if (loteParaVenda == null) {
            System.out.println("Lote com ID " + idLote + " não encontrado.");
            return;
        }

        System.out.print("Quantos ingressos deseja vender deste lote? (Disponível: " + loteParaVenda.getQuantidadeDisponivel() + "): ");
        int quantidadeVender = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        loteParaVenda.registrarVenda(quantidadeVender);
        new LoteIngressoDAO().salvar(loteParaVenda);
        System.out.println("Venda registrada com sucesso!");
    }
} */