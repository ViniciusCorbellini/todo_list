package com.mycompany.todo_list.dao;

import com.mycompany.todo_list.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        } else{
            throw new SQLException("Erro na inserção do usuário no BD.");
        }
    }
}
