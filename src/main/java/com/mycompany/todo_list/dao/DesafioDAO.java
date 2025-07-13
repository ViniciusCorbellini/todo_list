package com.mycompany.todo_list.dao;

import com.mycompany.todo_list.exceptions.EntityNotFoundException;
import com.mycompany.todo_list.model.Desafio;
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
public class DesafioDAO {
    public void insert(Desafio d) throws SQLException{
        String sql = """
                     INSERT INTO desafio(
                     \tnome,
                         descricao,
                         data_inicio,
                         data_fim,
                         completo,
                         dono_id
                     )
                     VALUES(?,?,?,?,?,?);""";
        
        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, d.getNome());
        ps.setString(2, d.getDescricao());
        ps.setDate(3, java.sql.Date.valueOf(d.getDataInicio()));
        ps.setDate(4, java.sql.Date.valueOf(d.getDataFim()));
        ps.setDouble(5, d.getCompleto());
        ps.setLong(6, d.getDono().getId());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            long dId = rs.getLong(1);
            d.setId(dId);
        } else {
            throw new SQLException("Erro na inserção do desafio no BD.");
        }
    }
    
    public Desafio findByID(long id) throws SQLException, EntityNotFoundException {
        String sql = """
                     SELECT 
                     \tnome,
                         descricao,
                         data_inicio,
                         data_fim,
                         completo,
                         dono_id
                     FROM desafio 
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, id);
        

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_inicio");
            LocalDate data_inicio = timestamp.toLocalDateTime().toLocalDate();
            Timestamp timestamp_data_fim = rs.getTimestamp("data_fim");
            LocalDate data_fim = timestamp_data_fim.toLocalDateTime().toLocalDate();

            Double completo = rs.getDouble("completo");
            Long dono_id = rs.getLong("dono_id");
            return new Desafio(id, nome, descricao, data_inicio, data_fim, null, null, dono_id, completo);
        }
        throw new EntityNotFoundException("Desafio com id " + id);
    }    
    
    public List<Desafio> findAll() throws SQLException, EntityNotFoundException {
        List<Desafio> ans = new ArrayList<>();
        
        String sql = """
                     SELECT *
                     FROM desafio ;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");

            Timestamp timestamp = rs.getTimestamp("data_inicio");
            LocalDate data_inicio = timestamp.toLocalDateTime().toLocalDate();
            Timestamp timestamp_data_fim = rs.getTimestamp("data_fim");
            LocalDate data_fim = timestamp_data_fim.toLocalDateTime().toLocalDate();

            Double completo = rs.getDouble("completo");
            Long dono_id = rs.getLong("dono_id");
            ans.add(new Desafio(id, nome, descricao, data_inicio, data_fim, null, null, dono_id, completo));
        }
        return ans;
    }  
    
    public boolean update(Desafio d) throws SQLException, EntityNotFoundException {
        String sql = """
                     UPDATE desafio 
                     SET 
                     \tnome = ?,
                     \tdescricao = ?,
                         data_inicio = ?,
                         data_fim = ?,
                         completo = ?,
                         dono_id = ?
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, d.getNome());
        ps.setString(2, d.getDescricao());
        ps.setDate(3, Date.valueOf(d.getDataInicio()));
        ps.setDate(4, Date.valueOf(d.getDataFim()));
        ps.setDouble(5, d.getCompleto());
        ps.setLong(6, d.getDono_id());
        ps.setLong(7, d.getId());

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }
    
    public boolean delete(long id) throws SQLException, EntityNotFoundException {
        String sql = """
                     DELETE FROM desafio
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, id);

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }
}
