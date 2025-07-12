package com.mycompany.todo_list.dao;

import com.mycompany.todo_list.exceptions.EntityNotFoundException;
import com.mycompany.todo_list.model.Usuario;
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
public class UserDAO {

    public void insert(Usuario u) throws SQLException {
        String sql = """
                     INSERT INTO usuario(
                     \tnome, 
                         email, 
                         senha_hash, 
                         data_criacao, 
                         usuario_admin
                     )
                     values(?, ?, ?, ?, ?);""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, u.getNome());
        ps.setString(2, u.getEmail());
        ps.setString(3, u.getSenhaHash());
        ps.setDate(4, java.sql.Date.valueOf(u.getDataCriacao()));
        ps.setBoolean(5, u.isAdmin());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            long uId = rs.getLong(1);
            u.setId(uId);
        } else {
            throw new SQLException("Erro na inserção do usuário no BD.");
        }
    }

    public Usuario findByID(long id) throws SQLException, EntityNotFoundException {
        String sql = """
                     SELECT 
                     \tnome, 
                         email,
                         data_criacao,
                         usuario_admin
                     FROM usuario
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setLong(1, id);
        

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nome = rs.getString("nome");
            String email = rs.getString("email");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp != null ? timestamp.toLocalDateTime().toLocalDate() : null;

            Boolean usuario_admin = rs.getBoolean("usuario_admin");
            return new Usuario(id, nome, email, data_criacao, usuario_admin);
        }
        throw new EntityNotFoundException("usuário com id " + id);
    }    
    
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> ans = new ArrayList<>();
        
        String sql = """
                     SELECT 
                     \tid,
                     \tnome, 
                         email,
                         data_criacao,
                         usuario_admin
                     FROM usuario;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");

            Timestamp timestamp = rs.getTimestamp("data_criacao");
            LocalDate data_criacao = timestamp != null ? timestamp.toLocalDateTime().toLocalDate() : null;

            Boolean usuario_admin = rs.getBoolean("usuario_admin");
            ans.add(new Usuario(id, nome, email, data_criacao, usuario_admin));
        }
        return ans;
    }
    
    public boolean update(Usuario u) throws SQLException, EntityNotFoundException {
        String sql = """
                     UPDATE usuario
                     SET nome = ?,
                     \temail = ?,
                         senha_hash = ?,
                         usuario_admin = ?
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, u.getNome());
        ps.setString(2, u.getEmail());
        ps.setString(3, u.getSenhaHash());
        ps.setBoolean(4, u.isAdmin());
        ps.setLong(5, u.getId());

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }
    
    public boolean delete(long id) throws SQLException, EntityNotFoundException {
        String sql = """
                     DELETE from usuario
                     WHERE id = ?;""";

        Connection con = ConnectionDAO.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setLong(1, id);

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }
}
