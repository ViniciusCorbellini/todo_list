package view;

import com.formdev.flatlaf.FlatDarkLaf;
import controller.TodoController;
import dao.ConnectionDAO;
import exceptions.InvalidObjectException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author boli6
 */
public class Main {

    public static void main(String[] args) {
        try {
            ConnectionDAO.createDB();
            TodoController.insertAdmin();
        } catch (SQLException | IOException | InvalidObjectException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

        SwingUtilities.invokeLater(() -> {
            Login login = new Login(new TodoController());
            login.pack();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
        });
    }

}
