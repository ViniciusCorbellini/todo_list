package com.mycompany.todo_list.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Vinicius Corbellini
 */
public class GrupoTarefas {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataCriacao;
    private Usuario dono;
    private List<Tarefa> tarefas;

    
    //===== Construtores
    public GrupoTarefas(Long id, String nome, String descricao, LocalDate dataCriacao, Usuario dono, List<Tarefa> tarefas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dono = dono;
        this.tarefas = tarefas;
    }

    public GrupoTarefas() {
    }

    //===== Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
