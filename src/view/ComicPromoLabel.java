/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ComicPromoLabel extends JComponent {
    private String text = "-50%";
    private Color backgroundColor = new Color(255, 0, 0, 220);
    private Color textColor = Color.WHITE;
    private int radius = 40;
    private int spikes = 16;
    private Font font = new Font("Arial", Font.BOLD, 16);

    public ComicPromoLabel(String text) {
        this.text = text;
        setPreferredSize(new Dimension(2 * radius, 2 * radius));
        setOpaque(false); // Necesario para permitir fondo personalizado
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Dibujar explosión
        double angleStep = 2 * Math.PI / spikes;
        int[] xPoints = new int[spikes];
        int[] yPoints = new int[spikes];
        for (int i = 0; i < spikes; i++) {
            double angle = i * angleStep;
            int r = (i % 2 == 0) ? radius : (int) (radius * 0.6);
            xPoints[i] = (int) (centerX + r * Math.cos(angle));
            yPoints[i] = (int) (centerY + r * Math.sin(angle));
        }

        Polygon burst = new Polygon(xPoints, yPoints, spikes);
        g2.setColor(backgroundColor);
        g2.fill(burst);

        // Dibujar texto
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        int textW = fm.stringWidth(text);
        int textH = fm.getAscent();
        g2.setColor(textColor);
        g2.drawString(text, centerX - textW / 2, centerY + textH / 4);

        g2.dispose();
    }
}