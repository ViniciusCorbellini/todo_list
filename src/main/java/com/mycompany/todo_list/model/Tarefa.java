package com.mycompany.todo_list.model;

import java.time.LocalDate;

/**
 *
 * @author Vinicius Corbellini
 */
public class Tarefa {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate prazo;
    private boolean concluida;
    private GrupoTarefas grupo; // referência para o grupo (pasta)
    private Long grupo_id;
    private Desafio desafio; // se fizer parte de um desafio
    private Long desafio_id;
    private Integer prioridade;

    //===== Construtores
    public Tarefa(long id, String titulo, String descricao, LocalDate dataCriacao, LocalDate prazo, boolean concluida, GrupoTarefas grupo, Long grupo_id, Desafio desafio, Long desafio_id, Integer prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.prazo = prazo;
        this.concluida = concluida;
        this.grupo = grupo;
        this.grupo_id = grupo_id;
        this.desafio = desafio;
        this.desafio_id = desafio_id;
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

    public Long getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(Long grupo_id) {
        this.grupo_id = grupo_id;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public Long getDesafio_id() {
        return desafio_id;
    }

    public void setDesafio_id(Long desafio_id) {
        this.desafio_id = desafio_id;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
}
