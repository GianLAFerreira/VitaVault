package br.com.vitavault.swing.padroesvisuais;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class JButtonArredondado extends JButton {

    private int arcWidth = 15;
    private int arcHeight = 15;

    public JButtonArredondado() {
        super();
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public JButtonArredondado(int arcWidth, int arcHeight) {
        super();
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color background = getBackground();

        background = background.brighter();

        g2d.setColor(background);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight));

        super.paintComponent(g);
    }
}

