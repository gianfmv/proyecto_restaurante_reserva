package view;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel {
    public enum FitMode { COVER, CONTAIN }

    private BufferedImage image;
    private FitMode fitMode = FitMode.COVER;

    private int cornerRadius = 32;
    private int borderThickness = 1;
    private Color borderColor = new Color(25, 25, 25); // Azul por defecto

    public ImagePanel(String url) {
        setOpaque(false);
        loadImageAsync(url);
    }

    public void setFitMode(FitMode mode) {
        this.fitMode = mode;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    private void loadImageAsync(String urlOrName) {
        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {
            @Override
            protected BufferedImage doInBackground() throws Exception {
                try {
                    URI uri = new URI(urlOrName);
                    if(uri.isAbsolute()) return ImageIO.read(uri.toURL());
                    return ImageIO.read(ImagePanel.class.getResourceAsStream("/resources/" + urlOrName));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                
            }

            @Override
            protected void done() {
                try {
                    BufferedImage img = get();
                    if(img != null) image = get();
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape clip = new RoundRectangle2D.Float(
            borderThickness / 2f,
            borderThickness / 2f,
            width - borderThickness,
            height - borderThickness,
            cornerRadius,
            cornerRadius
        );
        g2.setClip(clip);

        // Fondo por si la imagen no llena
        g2.setColor(getBackground());
        g2.fill(clip);

        // Dibujo de imagen según modo
        if (image != null) {
            int imgW = image.getWidth();
            int imgH = image.getHeight();

            float panelAspect = (float) width / height;
            float imgAspect = (float) imgW / imgH;

            int drawW, drawH, drawX, drawY;

            if (fitMode == FitMode.CONTAIN) {
                if (imgAspect > panelAspect) {
                    drawW = width;
                    drawH = (int) (drawW / imgAspect);
                } else {
                    drawH = height;
                    drawW = (int) (drawH * imgAspect);
                }
                drawX = (width - drawW) / 2;
                drawY = (height - drawH) / 2;
            } else { // COVER
                if (imgAspect < panelAspect) {
                    drawW = width;
                    drawH = (int) (drawW / imgAspect);
                } else {
                    drawH = height;
                    drawW = (int) (drawH * imgAspect);
                }
                drawX = (width - drawW) / 2;
                drawY = (height - drawH) / 2;
            }

            g2.drawImage(image, drawX, drawY, drawW, drawH, this);
        }

        // Dibujar borde redondeado
        g2.setClip(null);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Float(
            borderThickness / 2f,
            borderThickness / 2f,
            width - borderThickness,
            height - borderThickness,
            cornerRadius,
            cornerRadius
        ));

        g2.dispose();
        super.paintComponent(g);
    }
}
