package view.elementos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotaoModerno extends JButton {

    private Color corFundo = new Color(255, 255, 255);
    private Color corHover = new Color(240, 224, 173);
    private Color corTexto = new Color(255, 255, 255);
    private int raio = 20; // arredondamento

    public BotaoModerno() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(corTexto);
        setBackground(corFundo);
        setFont(new Font("Segoe UI", Font.BOLD, 13));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(10, 20, 10, 20)); // padding interno

        // Hover
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(corHover);
            }

            public void mouseExited(MouseEvent e) {
                setBackground(corFundo);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fundo com cantos arredondados
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), raio, raio);

        super.paintComponent(g);
        g2.dispose();
    }

    // Setters para personalizar se quiser
    public void setCorFundo(Color cor) {
        this.corFundo = cor;
        setBackground(corFundo);
        repaint();
    }

    public void setCorHover(Color cor) {
        this.corHover = cor;
    }

    public void setRaio(int r) {
        this.raio = r;
    }
}
