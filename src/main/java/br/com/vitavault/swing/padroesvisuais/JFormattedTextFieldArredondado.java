package br.com.vitavault.swing.padroesvisuais;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.Format;

public class JFormattedTextFieldArredondado extends JFormattedTextField {

    private int arcWidth = 15;
    private int arcHeight = 15;

    public JFormattedTextFieldArredondado() {
        super();
        setOpaque(false);
        Dimension preferredSize = getPreferredSize();
        preferredSize.height = 30; // Ajuste a altura desejada
        setPreferredSize(preferredSize);

    }

    public JFormattedTextFieldArredondado(Format format) {
        super(format);
        setOpaque(false);
    }

    public JFormattedTextFieldArredondado(int arcWidth, int arcHeight) {
        super();
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color background = getBackground();
        g2d.setColor(background);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight));

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color borderColor = getBackground().darker();
        g2d.setColor(borderColor);
        g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight));
    }

    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight).contains(x, y);
    }
}
