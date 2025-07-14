package com.mycompany.todo_list.controller;

import com.mycompany.todo_list.dao.DesafioDAO;
import com.mycompany.todo_list.dao.GrupoDAO;
import com.mycompany.todo_list.dao.TarefaDAO;
import com.mycompany.todo_list.dao.UserDAO;
import com.mycompany.todo_list.exceptions.EntityNotFoundException;
import com.mycompany.todo_list.exceptions.InvalidObjectException;
import com.mycompany.todo_list.model.Desafio;
import com.mycompany.todo_list.model.Tarefa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vinicius Corbellini
 */
public class TodoController {

    private DesafioDAO ddao;
    private GrupoDAO gdao;
    private TarefaDAO tdao;
    private UserDAO udao;

    public TodoController(DesafioDAO ddao, GrupoDAO gdao, TarefaDAO tdao, UserDAO udao) {
        this.ddao = ddao;
        this.gdao = gdao;
        this.tdao = tdao;
        this.udao = udao;
    }

    public void createTask(Tarefa t) throws SQLException, InvalidObjectException, EntityNotFoundException {
        if (!validTask(t)) {
            throw new InvalidObjectException("Tarefa inválida");
        }
        tdao.insert(t);
    }

    public Tarefa findTaskById(long id) throws SQLException, EntityNotFoundException {
        return tdao.findById(id);
    }

    public List<Tarefa> findAllTasks() throws SQLException, EntityNotFoundException {
        return tdao.findAll();
    }

    public void updateTask(Tarefa new_t) throws SQLException, EntityNotFoundException {
        Tarefa t = tdao.findById(new_t.getId());
        updateAtributes(t, new_t);
        
        boolean updated = tdao.update(t);
        if (!updated) {
            throw new SQLException("Não foi possível realizar o update da tarefa com id " + t.getId());
        }
    }

    public void deleteTask(long id) throws SQLException, EntityNotFoundException {
        boolean deleted = tdao.delete(id);
        if (!deleted) {
            throw new SQLException("Não foi possível realizar a deleção da tarefa com id " + id);
        }
    }

    //===== Funções auxiliares
    /**
     * Valida os atributos da task Função auxiliar para createTask()
     *
     * @return true se a tarefa possuir todos os campos invalidos
     */
    private boolean validTask(Tarefa t) {
        return !t.getTitulo().isBlank()
                && !t.getDescricao().isBlank()
                && (t.getDataCriacao() != null)
                && (t.getPrazo() != null);
    }

    /**
     * Atualiza os atributos não nulos da nova tarefa para a tarefa base
     */
    private void updateAtributes(Tarefa t, Tarefa new_t) {
        if (!new_t.getTitulo().isBlank()) {
            t.setTitulo(new_t.getTitulo());
        }

        if (!new_t.getDescricao().isBlank()) {
            t.setDescricao(new_t.getDescricao());
        }

        if (new_t.getPrioridade() != null) {
            t.setPrioridade(new_t.getPrioridade());
        }
        
        if(new_t.getDesafio_id() != null){
            t.setDesafio_id(new_t.getDesafio_id());
        }
        t.setConcluida(new_t.isConcluida());
    }
}
