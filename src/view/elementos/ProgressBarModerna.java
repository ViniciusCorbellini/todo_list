package view.elementos;

import java.awt.*;
import javax.swing.*;

public class ProgressBarModerna extends JPanel {

    private int progresso; // Valor entre 0 e 100
    private Color corProgresso = new Color(63, 125, 62); // Azul padrão

    public ProgressBarModerna(int progresso) {
        this.progresso = progresso;
        setPreferredSize(new Dimension(200, 25));
        setBackground(new Color(255, 255, 255)); // Cor do fundo da barra
        setOpaque(false);
    }

    // Define o valor do progresso de 0 a 100
    public void setValorProgresso(int valor) {
        progresso = Math.max(0, Math.min(100, valor));
        repaint(); // Redesenha o componente
    }

    // Define a cor do preenchimento
    public void setCorProgresso(Color cor) {
        this.corProgresso = cor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Antialiasing para suavizar os cantos
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = height; // para ficar arredondado

        // Fundo
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        // Progresso
        int progressoWidth = (int) (width * (progresso / 100.0));
        g2.setColor(corProgresso);
        g2.fillRoundRect(0, 0, progressoWidth, height, arc, arc);

        g2.dispose();
    }
}
