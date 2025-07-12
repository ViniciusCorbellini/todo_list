package com.mycompany.todo_list.dao;

import com.mycompany.todo_list.model.Usuario;

/**
 *
 * @author Vinicius Corbellini
 */
public class UserDAO {

    public void insert(Usuario u) {
        String sql = """
                     INSERT INTO usuario(
                     \tnome, 
                         email, 
                         senha_hash, 
                         data_criacao, 
                         usuario_admin
                     )
                     values(?, ?, ?, ?, ?);""";
    }
}
