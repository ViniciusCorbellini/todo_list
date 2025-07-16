package model;

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
    private Boolean concluida;
    private GrupoTarefas grupo; // referência para o grupo (pasta)
    private Long grupo_id;
    private Integer prioridade;

    //===== Construtores
    public Tarefa(Long id, String titulo, String descricao, LocalDate dataCriacao, LocalDate prazo, Boolean concluida, GrupoTarefas grupo, Long grupo_id, Integer prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.prazo = prazo;
        this.concluida = concluida;
        this.grupo = grupo;
        this.grupo_id = grupo_id;
        this.prioridade = prioridade;
    }
    
    
    public Tarefa() {
    }
    
    //==== Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
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

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "titulo: " + titulo + ", descricao: " + descricao;
    }
}
