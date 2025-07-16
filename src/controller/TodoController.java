package controller;

import dao.DesafioDAO;
import dao.GrupoDAO;
import dao.TarefaDAO;
import dao.TarefaDesafioDAO;
import dao.UserDAO;
import exceptions.AccessDeniedException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidObjectException;
import java.security.NoSuchAlgorithmException;
import model.Desafio;
import model.GrupoTarefas;
import model.Tarefa;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Usuario;
import util.HashUtil;

/**
 *
 * @author Vinicius Corbellini
 */
public class TodoController {

    private DesafioDAO ddao;
    private GrupoDAO gdao;
    private TarefaDAO tdao;
    private UserDAO udao;
    private TarefaDesafioDAO tddao;

    public TodoController() {
        this.ddao = new DesafioDAO();
        this.gdao = new GrupoDAO();
        this.tdao = new TarefaDAO();
        this.udao = new UserDAO();
        this.tddao = new TarefaDesafioDAO();
    }

    //==========Controller da tarefa==========
    public void createTask(Tarefa t) throws SQLException, InvalidObjectException, EntityNotFoundException {
        if (!validTask(t)) {
            throw new InvalidObjectException("Tarefa inválida");
        }
        tdao.insert(t);
    }

    public Tarefa findTaskById(long id) throws SQLException, EntityNotFoundException {
        return tdao.findById(id);
    }

    public List<Tarefa> findAllTasks(Long grupo_id) throws SQLException, EntityNotFoundException {
        return tdao.findAll(grupo_id);
    }
    
    public List<Tarefa> findAllTasksFromUser(Long uid_id) throws SQLException, EntityNotFoundException {
        return tdao.findAllFromUser(uid_id);
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

    //==========Controller do grupo ==========
    public void createGroup(GrupoTarefas g) throws SQLException, InvalidObjectException, EntityNotFoundException {
        if (!validGroup(g)) {
            throw new InvalidObjectException("Grupo inválido");
        }
        gdao.insert(g);
    }

    public GrupoTarefas findGroupById(long id) throws SQLException, EntityNotFoundException {
        return gdao.findById(id);
    }
    
    public GrupoTarefas findGroupByName(String nome) throws SQLException, EntityNotFoundException {
        return gdao.findByName(nome);
    }

    public List<GrupoTarefas> findAllGroups(long dono_id) throws SQLException, EntityNotFoundException {
        return gdao.findAll(dono_id);
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

    //==========Controller do Desafio==========
    public void createDesafio(Desafio d) throws InvalidObjectException, SQLException {
        if (!validChallenge(d)) {
            throw new InvalidObjectException("Desafio inválido");
        }
        ddao.insert(d);
    }

    public Desafio findChallengeById(long id) throws SQLException, EntityNotFoundException {
        return ddao.findByID(id);
    }

    public List<Desafio> findAllChallenges(long dono_id) throws SQLException, EntityNotFoundException {
        return ddao.findAll(dono_id);
    }

    public void updateChallenge(Desafio new_d) throws SQLException, EntityNotFoundException {
        Desafio d = ddao.findByID(new_d.getId());
        updateAtributes(d, new_d);

        boolean updated = ddao.update(d);
        if (!updated) {
            throw new SQLException("Não foi possível realizar o update do desafio com id " + d.getId());
        }
    }

    public void deleteChallenge(long id) throws SQLException, EntityNotFoundException {
        boolean deleted = ddao.delete(id);
        if (!deleted) {
            throw new SQLException("Não foi possível realizar a deleção do desafio com id " + id);
        }
    }

    //==========Controller do user==========
    public void createUser(Usuario u) throws InvalidObjectException, SQLException, NoSuchAlgorithmException {
        if (!validUser(u)) {
            throw new InvalidObjectException("Desafio inválido");
        }
        String senha_hash = HashUtil.hashSenha(u.getSenhaHash());
        u.setSenhaHash(senha_hash);
        udao.insert(u);
    }
    
    public List<Usuario> findAllUsers() throws SQLException{
        return udao.findAll();
    }

    public void updateUser(Usuario new_u) throws SQLException, EntityNotFoundException {
        Usuario u = udao.findByID(new_u.getId()); //pega o user do banco de dados
        updateAtributes(u, new_u);

        boolean updated = udao.update(u);
        if (!updated) {
            throw new SQLException("Não foi possível realizar o update do usuario com id " + u.getId());
        }
    }

    public void deleteUser(long id) throws SQLException, EntityNotFoundException {
        boolean deleted = udao.delete(id);
        if (!deleted) {
            throw new SQLException("Não foi possível realizar a deleção do usuário com id " + id);
        }
    }

    public Usuario login(String senha, String email) throws NoSuchAlgorithmException, SQLException, EntityNotFoundException, AccessDeniedException {
        String senha_hash = HashUtil.hashSenha(senha);
        Usuario u = udao.findByEmail(email);

        if (u.getSenhaHash().equals(senha_hash)) {
            return u;
        }
        throw new AccessDeniedException("Senha incorreta");
    }

    public static void insertAdmin() throws InvalidObjectException, SQLException, NoSuchAlgorithmException {
        Usuario u = new Usuario("root", "root@teste", "root", LocalDate.now(), true);
        String senha_hash = HashUtil.hashSenha(u.getSenhaHash());
        u.setSenhaHash(senha_hash);
        UserDAO udao = new UserDAO();
        udao.insertRoot(u);
    }
    
    //==========Controller do TarefaDesafio==========
    public List<Tarefa> listarTarefasPorDesafio(long desafioId) throws SQLException {
        return tddao.listarTarefasPorDesafio(desafioId);
    }
    
    public void insertRelation(long desafio_id, long tarefa_id) throws SQLException{
        tddao.insert(tarefa_id, desafio_id);
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
        if (new_t.getTitulo() != null && !new_t.getTitulo().isBlank()) {
            t.setTitulo(new_t.getTitulo());
        }

        if (new_t.getDescricao()!= null && !new_t.getDescricao().isBlank()) {
            t.setDescricao(new_t.getDescricao());
        }

        if (new_t.getPrioridade()!= null && new_t.getPrioridade() != null) {
            t.setPrioridade(new_t.getPrioridade());
        }
        t.setConcluida(new_t.getConcluida());
    }

    /**
     * Valida os atributos do grupo Função auxiliar para createGroup()
     *
     * @return true se o grupo possuir todos os campos validos
     */
    private boolean validGroup(GrupoTarefas g) {
        return !g.getNome().isBlank()
                && !g.getDescricao().isBlank()
                && (g.getDataCriacao() != null);
    }

    /**
     * Atualiza os atributos não nulos do novo grupo para o grupo base
     */
    private void updateAtributes(GrupoTarefas g, GrupoTarefas new_g) {
        if (!new_g.getNome().isBlank()) {
            g.setNome(new_g.getNome());
        }

        if (!new_g.getDescricao().isBlank()) {
            g.setDescricao(new_g.getDescricao());
        }
    }

    /**
     * Valida os atributos do desafio Função auxiliar para createUser()
     *
     * @return true se o desafio possuir todos os campos invalidos
     */
    private boolean validChallenge(Desafio d) {
        return !d.getNome().isBlank()
                && !d.getDescricao().isBlank()
                && (d.getDataInicio() != null)
                && (d.getDataFim() != null);
    }

    /**
     * Atualiza os atributos não nulos do novo desafio para o desafio base
     */
    private void updateAtributes(Desafio d, Desafio new_d) {
        if (new_d.getNome() != null && !new_d.getNome().isBlank()) {
            d.setNome(new_d.getNome());
        }

        if (new_d.getDescricao()!= null && !new_d.getDescricao().isBlank()) {
            d.setDescricao(new_d.getDescricao());
        }

        if (new_d.getDataFim() != null && new_d.getDataFim() != null) {
            d.setDataFim(new_d.getDataFim());
        }

        if (new_d.getCompleto() != null && new_d.getCompleto() != null) {
            d.setCompleto(new_d.getCompleto());
        }
    }

    /**
     * Valida os atributos do user Função auxiliar para createUser()
     *
     * @return true se o user possuir todos os campos invalidos
     */
    private boolean validUser(Usuario u) {
        return !u.getNome().isBlank()
                && !u.getEmail().isBlank()
                && !u.getSenhaHash().isBlank()
                && (u.getDataCriacao() != null);
    }

    /**
     * Atualiza os atributos não nulos do novo user para o user base
     */
    private void updateAtributes(Usuario u, Usuario new_u) {
        if (!new_u.getNome().isBlank()) {
            u.setNome(new_u.getNome());
        }

        if (!new_u.getEmail().isBlank()) {
            u.setEmail(new_u.getEmail());
        }
        u.setAdmin(new_u.isAdmin());
    }
}
