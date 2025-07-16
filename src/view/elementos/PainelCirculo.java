package view.elementos;

import java.awt.*;
import javax.swing.*;

public class PainelCirculo extends JPanel {

    private Color cor = new Color(255, 255, 255); // Cor padrão (azul)

    public PainelCirculo() {
        setOpaque(false); // deixa o fundo transparente
        setPreferredSize(new Dimension(100, 100)); // tamanho padrão
    }

    public Color getCor() {
        return cor;
    }

    // Permite mudar a cor pelo código
    public void setCor(Color cor) {
        this.cor = cor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha o círculo
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(cor);
        g2.fillOval(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
