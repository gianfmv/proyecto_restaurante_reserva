/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URI;

public class CustomLabel extends JLabel {
    private BufferedImage image;

    public CustomLabel(String imagePath) {
        try {
          image = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(300, 200));
    }

    public CustomLabel(String imageUrl, boolean fromUrl) {
        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {
                @Override
                protected BufferedImage doInBackground() throws Exception {
                    URI uri = new URI(imageUrl);
                    return ImageIO.read(uri.toURL());
                }

                @Override
                protected void done() {
                    try {
                        image = get();
                        
                    
                    } catch (Exception e) {
                        setText("Error inesperado");
                        e.printStackTrace();
                    }
                }
            };
            worker.execute();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int compWidth = getWidth();
            int compHeight = getHeight();
            float imgRatio = (float) image.getWidth() / image.getHeight();
            float compRatio = (float) compWidth / compHeight;

            int drawWidth, drawHeight;

            if (compRatio > imgRatio) {

                drawWidth = compWidth;
                drawHeight = (int)(drawWidth / imgRatio);
                
                System.out.println("a");
            } else {
                drawWidth = compWidth;
                drawHeight = (int) (compWidth / imgRatio);
                System.out.println("b");
            }

            int x = (compWidth - drawWidth) / 2;
            int y = (compHeight - drawHeight) / 2;

            g.drawImage(image, x, y, drawWidth, drawHeight, this);
        }
    }
    
    public void setImageFromFile(String imagePath) {
    try {
        this.image = ImageIO.read(new File(imagePath));
        repaint();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}