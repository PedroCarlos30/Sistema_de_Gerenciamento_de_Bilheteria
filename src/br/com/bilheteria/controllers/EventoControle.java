package br.com.bilheteria.controllers;

import br.com.bilheteria.models.DAO.EventoDAO;
import br.com.bilheteria.models.entidade.Evento;

import java.sql.Timestamp;
import java.util.List;

public class EventoControle {

    public static void cadastrarEvento(String nome, String descricao, Timestamp dataHoraInicio, Timestamp dataHoraFim, String local, int capacidadeMaximaPublico) {
        Evento evento = new Evento(nome, descricao, dataHoraInicio, dataHoraFim, local, capacidadeMaximaPublico);
        EventoDAO.salvar(evento);
        System.out.println("\nEvento cadastrado com sucesso!\n");
    }

    // ...existing code...
    public static void atualizarEvento(int idEvento, String nome, String descricao, Timestamp dataHoraInicio, Timestamp dataHoraFim, String local, int capacidadeMaximaPublico) {
        EventoDAO eventoDAO = new EventoDAO();
        Evento evento = eventoDAO.buscarPorId(idEvento);
        if (evento == null) {
            System.out.println("Esse evento não existe.\n");
        } else {
            evento.setNome(nome);
            evento.setDescricao(descricao);
            evento.setDataHoraInicio(dataHoraInicio);
            evento.setDataHoraFim(dataHoraFim);
            evento.setLocal(local);
            evento.setCapacidadeMaximaPublico(capacidadeMaximaPublico);
            EventoDAO.salvar(evento);
            System.out.println("\nEvento atualizado com sucesso!\n");
        }
    }

    public static void deletarEvento(int idEvento) {
        EventoDAO eventoDAO = new EventoDAO();
        Evento evento = eventoDAO.buscarPorId(idEvento);
        if (evento == null) {
            System.out.println("Esse evento não existe.\n");
        } else {
            eventoDAO.deletar(idEvento);
            System.out.println("\nEvento deletado com sucesso!\n");
        }
    }

    public static void listarEventos() {
        EventoDAO eventoDAO = new EventoDAO();
        List<Evento> eventos = eventoDAO.listarTodos();
        if (eventos.isEmpty()) {
            System.out.println("Ainda não há eventos cadastrados.\n");
        } else {
            for (Evento evento : eventos) {
                System.out.println(evento);
            }
        }
    }

    public static void visualizarEvento(int idEvento) {
        EventoDAO eventoDAO = new EventoDAO();
        Evento evento = eventoDAO.buscarPorId(idEvento);
        if (evento == null) {
            System.out.println("Esse evento não existe.\n");
        } else {
            System.out.println("Informações do evento:");
            System.out.println(evento);
        }
    }

    public static Evento buscarEvento(int idEvento) {
        EventoDAO eventoDAO = new EventoDAO();
        Evento evento = eventoDAO.buscarPorId(idEvento);
        if (evento == null) {
            System.out.println("Esse evento não existe.\n");
        }
        return evento;
    }
}