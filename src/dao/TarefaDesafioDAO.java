package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import model.Tarefa;

/**
 *
 * @author Vinicius Corbellini
 */
public class TarefaDesafioDAO {

    public void insert(long tarefaId, long desafioId) throws SQLException {
        String sql = "INSERT INTO tarefa_desafio (tarefa_id, desafio_id) VALUES (?, ?)";
        Connection conn = ConnectionDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setLong(1, tarefaId);
        stmt.setLong(2, desafioId);
        stmt.executeUpdate();
    }

    public void remove(long tarefaId, long desafioId) throws SQLException {
        String sql = "DELETE FROM tarefa_desafio WHERE tarefa_id = ? AND desafio_id = ?";
        Connection conn = ConnectionDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setLong(1, tarefaId);
        stmt.setLong(2, desafioId);
        stmt.executeUpdate();
    }

    public List<Tarefa> listarTarefasPorDesafio(long desafioId) throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT t.* FROM tarefa t JOIN tarefa_desafio td ON t.id = td.tarefa_id WHERE td.desafio_id = ?";

        Connection conn = ConnectionDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setLong(1, desafioId);
        ResultSet rs = stmt.executeQuery();

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
            Long grupo_id = rs.getLong("grupo_id");

            tarefas.add(new Tarefa(id, titulo, descricao, data_criacao, prazo, concluida, null, grupo_id, prioridade));
        }
        return tarefas;
    }
}
