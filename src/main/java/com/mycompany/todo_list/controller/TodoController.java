package com.mycompany.todo_list.controller;

import com.mycompany.todo_list.dao.DesafioDAO;
import com.mycompany.todo_list.dao.GrupoDAO;
import com.mycompany.todo_list.dao.TarefaDAO;
import com.mycompany.todo_list.dao.UserDAO;
import com.mycompany.todo_list.exceptions.EntityNotFoundException;
import com.mycompany.todo_list.exceptions.InvalidObjectException;
import com.mycompany.todo_list.model.Desafio;
import com.mycompany.todo_list.model.GrupoTarefas;
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

    //Controller da tarefa
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

    //Controller do grupo 
    public void createGroup(GrupoTarefas g) throws SQLException, InvalidObjectException, EntityNotFoundException {
        if (!validGroup(g)) {
            throw new InvalidObjectException("Grupo inválido");
        }
        gdao.insert(g);
    }
    
    public GrupoTarefas findGroupById(long id) throws SQLException, EntityNotFoundException {
        return gdao.findById(id);
    }
    
    public List<GrupoTarefas> findAllGroups() throws SQLException, EntityNotFoundException {
        return gdao.findAll();
    }
    
    public void updateGroup(GrupoTarefas new_g) throws SQLException, EntityNotFoundException {
        GrupoTarefas g = gdao.findById(new_g.getId());
        updateAtributes(g, new_g);
        
        boolean updated = gdao.update(g);
        if (!updated) {
            throw new SQLException("Não foi possível realizar o update do grupo com id " + g.getId());
        }
    }
    
    public void deleteGrupo(long id) throws SQLException, EntityNotFoundException {
        boolean deleted = gdao.delete(id);
        if (!deleted) {
            throw new SQLException("Não foi possível realizar a deleção do grupo com id " + id);
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
    
    /**
     * Valida os atributos do grupo 
     * Função auxiliar para createGroup()
     *
     * @return true se o grupo possuir todos os campos validos
     */
    private boolean validGroup(GrupoTarefas g) {
        return !g.getNome().isBlank()
                && !g.getDescricao().isBlank()
                && (g.getDataCriacao() != null);
    }
    
    /**
     * Atualiza os atributos não nulos da nova tarefa para a tarefa base
     */
    private void updateAtributes(GrupoTarefas g, GrupoTarefas new_g) {
        if (!new_g.getNome().isBlank()) {
            g.setNome(new_g.getNome());
        }

        if (!new_g.getDescricao().isBlank()) {
            g.setDescricao(new_g.getDescricao());
        }
    }
}
