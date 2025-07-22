package br.com.bilheteria.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.bilheteria.models.DAO.EventoDAO;
import br.com.bilheteria.models.entidade.Evento;
import br.com.bilheteria.models.entidade.StatusEvento;

public class MenuEvento {

    private static EventoDAO eventoDAO = new EventoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        
        do {
            System.out.println("\n--- Menu Principal de Eventos ---");
            System.out.println("1. Criar Novo Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Atualizar Detalhes de um Evento");
            System.out.println("4. Alterar Status de um Evento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpa o buffer de entrada incorreta
                continue;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarNovoEvento(scanner);
                    break;
                case 2:
                    listarEventos();
                    break;
                case 3:
                    atualizarDetalhesEvento(scanner);
                    break;
                case 4:
                    alterarStatusEvento(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do menu de eventos...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarNovoEvento(Scanner scanner) {
        System.out.println("\n--- Criar Novo Evento ---");
        try {
            Evento novoEvento = new Evento();

            System.out.print("Digite o CNPJ do Organizador: ");
            novoEvento.setCnpjOrganizador(scanner.nextLine());

            System.out.print("Nome do Evento: ");
            novoEvento.setNome(scanner.nextLine());

            System.out.print("Descrição do Evento: ");
            novoEvento.setDescricao(scanner.nextLine());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            System.out.print("Data e Hora de Início (dd/MM/yyyy HH:mm): ");
            // O objeto Evento usa java.util.Date, que é a prática correta
            novoEvento.setDataHoraInicio(sdf.parse(scanner.nextLine()));

            System.out.print("Data e Hora de Fim (dd/MM/yyyy HH:mm): ");
            novoEvento.setDataHoraFim(sdf.parse(scanner.nextLine()));
            
            System.out.print("Local do Evento: ");
            novoEvento.setLocal(scanner.nextLine());

            System.out.print("Capacidade Máxima de Público: ");
            novoEvento.setCapacidadeMaximaPublico(scanner.nextInt());
            scanner.nextLine(); 

            novoEvento.setStatusEvento(StatusEvento.VENDAS_ABERTAS);

            eventoDAO.salvar(novoEvento);
            System.out.println("Evento criado com sucesso!");

        } catch (ParseException e) {
            System.err.println("Formato de data inválido! Use dd/MM/yyyy HH:mm.");
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao criar o evento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarEventos() {
        System.out.println("\n--- Lista de Eventos ---");
        try {
            List<Evento> eventos = eventoDAO.listarTodos();
            if (eventos.isEmpty()) {
                System.out.println("Nenhum evento cadastrado.");
                return;
            }
            for (Evento evento : eventos) {
                // Para este print funcionar bem, a classe Evento precisa ter um método toString()
                System.out.println(evento);
                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao listar os eventos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void atualizarDetalhesEvento(Scanner scanner) {
        System.out.println("\n--- Atualizar Detalhes de um Evento ---");
        try {
            System.out.print("Digite o ID do evento que deseja atualizar: ");
            int idEvento = scanner.nextInt();
            scanner.nextLine();

            Evento evento = eventoDAO.buscarPorId(idEvento);
            if (evento == null) {
                System.out.println("Evento com ID " + idEvento + " não encontrado.");
                return;
            }

            System.out.println("Deixe em branco para não alterar o campo.");

            System.out.print("Novo nome (" + evento.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.trim().isEmpty()) evento.setNome(nome);

            System.out.print("Nova descrição (" + evento.getDescricao() + "): ");
            String descricao = scanner.nextLine();
            if (!descricao.trim().isEmpty()) evento.setDescricao(descricao);

            // Adicione outros campos que desejar atualizar aqui...

            eventoDAO.atualizar(evento);

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao atualizar o evento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void alterarStatusEvento(Scanner scanner) {
        System.out.println("\n--- Alterar Status de um Evento ---");
        try {
            System.out.print("Digite o ID do evento que deseja alterar o status: ");
            int idEvento = scanner.nextInt();
            scanner.nextLine();

            Evento evento = eventoDAO.buscarPorId(idEvento);
            if (evento == null) {
                System.out.println("Evento com ID " + idEvento + " não encontrado.");
                return;
            }

            System.out.println("Status atual: " + evento.getStatusEvento());
            System.out.println("Escolha o novo status:");
            
            int i = 1;
            for (StatusEvento status : StatusEvento.values()) {
                System.out.println(i++ + ". " + status);
            }
            System.out.print("Opção: ");
            int statusEscolhido = scanner.nextInt();
            scanner.nextLine();

            if (statusEscolhido < 1 || statusEscolhido > StatusEvento.values().length) {
                System.out.println("Opção de status inválida.");
                return;
            }

            StatusEvento novoStatus = StatusEvento.values()[statusEscolhido - 1];
            evento.setStatusEvento(novoStatus);
            eventoDAO.atualizar(evento);
            
            System.out.println("Status do evento alterado para " + novoStatus);

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao alterar o status do evento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}