package com.mycompany.todo_list.dao;

import com.mycompany.todo_list.exceptions.EntityNotFoundException;
import com.mycompany.todo_list.model.Desafio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

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
            long uId = rs.getLong(1);
            d.setId(uId);
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
}
