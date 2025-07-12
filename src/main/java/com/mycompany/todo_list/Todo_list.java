package com.mycompany.todo_list;

import com.mycompany.todo_list.dao.ConnectionDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cliente
 */
public class Todo_list {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ConnectionDAO cdao = new ConnectionDAO();
        try {
            cdao.createDB();
        } catch (SQLException ex) {
            Logger.getLogger(Todo_list.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Todo_list.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
