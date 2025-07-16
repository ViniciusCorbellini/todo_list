package dao;

import exceptions.EntityNotFoundException;
import model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius Corbellini
 */
public class TarefaDAO {

    public void insert(Tarefa t) throws SQLException {
        String sql = """
                     INSERT INTO tarefa(
                     \ttitulo,
                         descricao,
                         data_criacao,
                         prazo,
                         concluida,
                         prioridade,
                         grupo_id
                     )
                     VALUES(?,?,?,?,?,?,?);""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, t.getTitulo());
        ps.setString(2, t.getDescricao());
        ps.setDate(3, java.sql.Date.valueOf(t.getDataCriacao()));
        ps.setDate(4, java.sql.Date.valueOf(t.getPrazo()));
        ps.setBoolean(5, t.getConcluida());
        ps.setInt(6, t.getPrioridade());
        ps.setLong(7, t.getGrupo().getId());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            long tId = rs.getLong(1);
            t.setId(tId);
        } else {
            throw new SQLException("Erro na inserção da tarefa no BD.");
        }
    }

    public Tarefa findById(Long id) throws SQLException, EntityNotFoundException {
        String sql = """
                     SELECT \t
                     \ttitulo,
                         descricao,
                         data_criacao,
                         prazo,
                         concluida,
                         prioridade,
                         grupo_id
                     FROM tarefa
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String titulo = rs.getString("titulo");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp.toLocalDateTime().toLocalDate();
            Timestamp timestamp_prazo = rs.getTimestamp("prazo");
            LocalDate prazo = timestamp_prazo.toLocalDateTime().toLocalDate();

            Boolean concluida = rs.getBoolean("concluida");
            Integer prioridade = rs.getInt("prioridade");
            Long grupo_id = rs.getLong("grupo_id");

            return new Tarefa(id, titulo, descricao, data_criacao, prazo, concluida, null, grupo_id, prioridade);
        }
        throw new EntityNotFoundException("Tarefa com id " + id);
    }

    public List<Tarefa> findAll(Long grupo_id) throws SQLException, EntityNotFoundException {
        List<Tarefa> ans = new ArrayList<>();

        String sql = """
                     SELECT * FROM tarefa
                     WHERE grupo_id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, grupo_id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String titulo = rs.getString("titulo");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp.toLocalDateTime().toLocalDate();
            Timestamp timestamp_prazo = rs.getTimestamp("prazo");
            LocalDate prazo = timestamp_prazo.toLocalDateTime().toLocalDate();

            Boolean concluida = rs.getBoolean("concluida");
            Integer prioridade = rs.getInt("prioridade");
            ans.add(new Tarefa(id, titulo, descricao, data_criacao, prazo, concluida, null, grupo_id, prioridade));
        }
        return ans;
    }

    public List<Tarefa> findAllFromUser(Long user_id) throws SQLException, EntityNotFoundException {
        List<Tarefa> ans = new ArrayList<>();

        String sql = """
                     SELECT t.*
                     FROM tarefa t
                     JOIN grupo_de_tarefas g ON t.grupo_id = g.id
                     WHERE g.dono_id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, user_id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String titulo = rs.getString("titulo");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp.toLocalDateTime().toLocalDate();
            Timestamp timestamp_prazo = rs.getTimestamp("prazo");
            LocalDate prazo = timestamp_prazo.toLocalDateTime().toLocalDate();

            Boolean concluida = rs.getBoolean("concluida");
            Long grupo_id = rs.getLong("grupo_id");
            Integer prioridade = rs.getInt("prioridade");
            ans.add(new Tarefa(id, titulo, descricao, data_criacao, prazo, concluida, null, grupo_id, prioridade));
        }
        return ans;
    }

    public boolean update(Tarefa t) throws SQLException, EntityNotFoundException {
        String sql = """
                     UPDATE TAREFA 
                     SET 
                     \ttitulo = ?,
                     \tdescricao = ?,
                         data_criacao = ?,
                         prazo = ?,
                         concluida = ?,
                         prioridade = ?,
                         grupo_id = ?
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, t.getTitulo());
        ps.setString(2, t.getDescricao());
        ps.setDate(3, java.sql.Date.valueOf(t.getDataCriacao()));
        ps.setDate(4, java.sql.Date.valueOf(t.getPrazo()));
        ps.setBoolean(5, t.getConcluida());
        ps.setInt(6, t.getPrioridade());
        ps.setLong(7, t.getGrupo_id());
        ps.setLong(8, t.getId());

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }

    public boolean delete(long id) throws SQLException, EntityNotFoundException {
        String sql = """
                     DELETE FROM tarefa
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, id);

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }
}
