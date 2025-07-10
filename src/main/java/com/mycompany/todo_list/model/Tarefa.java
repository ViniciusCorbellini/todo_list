package com.mycompany.todo_list.model;

import java.time.LocalDate;

/**
 *
 * @author Vinicius Corbellini
 */
public class Tarefa {

    private long id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate prazo;
    private boolean concluida;
    private GrupoTarefas grupo; // referência para o grupo (pasta)
    private Desafio desafio; // se fizer parte de um desafio
    private int prioridade; // 1 a 5, por exemplo

    //===== Construtores
    public Tarefa(long id, String titulo, String descricao, LocalDate dataCriacao, LocalDate prazo, boolean concluida, GrupoTarefas grupo, Desafio desafio, int prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.prazo = prazo;
        this.concluida = concluida;
        this.grupo = grupo;
        this.desafio = desafio;
        this.prioridade = prioridade;
    }
    
    public Tarefa() {
    }
    
    //==== Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public GrupoTarefas getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoTarefas grupo) {
        this.grupo = grupo;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
