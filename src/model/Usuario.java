package model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Vinicius Corbellini
 */
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senhaHash;
    private LocalDate dataCriacao;
    private List<GrupoTarefas> grupos; // pastas ou diretórios
    private List<Desafio> desafios;
    private boolean admin;

    //===== Construtores
    public Usuario(Long id, String nome, String email, String senhaHash, LocalDate dataCriacao, List<GrupoTarefas> grupos, List<Desafio> desafios, boolean admin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.dataCriacao = dataCriacao;
        this.grupos = grupos;
        this.desafios = desafios;
        this.admin = admin;
    }
    
    public Usuario(Long id, String nome, String email, LocalDate dataCriacao, boolean admin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.admin = admin;
    }
    
    public Usuario(Long id, String nome, String email, String senhaHash, LocalDate dataCriacao, boolean admin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.dataCriacao = dataCriacao;
        this.admin = admin;
    }
    
    public Usuario(String nome, String email, String senhaHash, LocalDate dataCriacao, boolean admin) {
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.dataCriacao = dataCriacao;
        this.admin = admin;
    }

    public Usuario() {
    }

    //===== Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<GrupoTarefas> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoTarefas> grupos) {
        this.grupos = grupos;
    }

    public List<Desafio> getDesafios() {
        return desafios;
    }

    public void setDesafios(List<Desafio> desafios) {
        this.desafios = desafios;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senhaHash=" + senhaHash + ", dataCriacao=" + dataCriacao + ", grupos=" + grupos + ", desafios=" + desafios + ", admin=" + admin + '}';
    }
}
