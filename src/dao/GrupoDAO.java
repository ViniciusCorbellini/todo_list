package dao;

import exceptions.EntityNotFoundException;
import model.GrupoTarefas;
import java.sql.Connection;
import java.sql.Date;
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
public class GrupoDAO {
    public void insert(GrupoTarefas g) throws SQLException{
        String sql = """
                     INSERT INTO grupo_de_tarefas(
                     \tnome,
                         descricao,
                         data_criacao,
                         dono_id
                     ) 
                     VALUES(?,?,?,?);""";
        
        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setString(1, g.getNome());
        ps.setString(2, g.getDescricao());
        ps.setDate(3, Date.valueOf(g.getDataCriacao()));
        ps.setLong(4, g.getDono_id());
        
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            long gId = rs.getLong(1);
            g.setId(gId);
        } else {
            throw new SQLException("Erro na inserção do grupo no BD.");
        }
    }
    
    public GrupoTarefas findById(long id) throws SQLException, EntityNotFoundException{
        String sql = """
                     SELECT  
                     \tnome,
                         descricao,
                         data_criacao,
                         dono_id
                     FROM grupo_de_tarefas
                     WHERE id = ?;""";
        
        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setLong(1, id);
        
        ps.executeQuery();

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp.toLocalDateTime().toLocalDate();
            
            Long dono_id = rs.getLong("dono_id");
            return new GrupoTarefas(id, nome, descricao, data_criacao, null, dono_id, null);
        }
        throw new EntityNotFoundException("Desafio com id " + id);
    }
    
    public GrupoTarefas findByName(String nome) throws SQLException, EntityNotFoundException{
        String sql = """
                     SELECT *
                     FROM grupo_de_tarefas
                     WHERE nome = ?;""";
        
        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, nome);
        
        ps.executeQuery();

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Long id = rs.getLong("id");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp.toLocalDateTime().toLocalDate();
            
            Long dono_id = rs.getLong("dono_id");
            return new GrupoTarefas(id, nome, descricao, data_criacao, null, dono_id, null);
        }
        throw new EntityNotFoundException("Desafio com nome " + nome);
    }
    
    public List<GrupoTarefas> findAll(long dono_id) throws SQLException{
        List<GrupoTarefas> ans = new ArrayList<>();
        
        String sql = """
                     SELECT * 
                     FROM grupo_de_tarefas 
                     WHERE dono_id = ?;""";
        
        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, dono_id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp.toLocalDateTime().toLocalDate();
            ans.add(new GrupoTarefas(id, nome, descricao, data_criacao, null, dono_id, null));
        }
        return ans;
    }
    
    public boolean update(GrupoTarefas g) throws SQLException{
        String sql = """
                     UPDATE grupo_de_tarefas
                     SET  
                     \tnome = ?,
                         descricao = ?,
                         data_criacao = ?,
                         dono_id = ?
                     WHERE id = ?;""";
        
        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, g.getNome());
        ps.setString(2, g.getDescricao());
        ps.setDate(3, Date.valueOf(g.getDataCriacao()));
        ps.setLong(4, g.getDono_id());
        ps.setLong(5, g.getId());

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }
    
    public boolean delete(long id) throws SQLException{
        String sql = """
                     DELETE FROM grupo_de_tarefas
                     WHERE id = ?;""";
        
        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, id);

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }
}
