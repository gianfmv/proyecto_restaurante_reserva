/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import modelo.Plato;
import util.FontLoader;
import view.ImagePanel;

/**
 *
 * @author salaz
 */
public class PlatoVista extends javax.swing.JPanel {

    private Plato menu;

    /**
     * Creates new form MenuItem
     */
    public PlatoVista(Plato menu) {
        initComponents();
        this.menu = menu;
        txt_entrada1.setText(menu.getNombre());
        txt_entrada1.setFont(FontLoader.load("Poppins-Black.ttf", Font.PLAIN, 20));
        txtDescripcion.setFont(FontLoader.load("Poppins-Regular.ttf", Font.PLAIN, 14));

        txtDescripcion.setText(menu.getDescripcion());
        panelImagen.removeAll();
        ImagePanel imagen = new ImagePanel(menu.getUrlImagen());
        imagen.setAlignmentX(0.5f);
        imagen.setAlignmentY(0.5f);
        panelImagen.add(imagen, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_entrada1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtDescripcion = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(32767, 0));
        panelImagen = new javax.swing.JPanel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        txt_entrada1.setFont(new java.awt.Font("Mongolian Baiti", 0, 17)); // NOI18N
        txt_entrada1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_entrada1.setText("Sopa de Moron");
        txt_entrada1.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        add(txt_entrada1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 32, 16, 32));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        txtDescripcion.setText("jLabel1");
        txtDescripcion.setMaximumSize(new java.awt.Dimension(200, 999999));
        jPanel1.add(txtDescripcion);
        jPanel1.add(filler1);

        panelImagen.setBackground(new java.awt.Color(255, 255, 255));
        panelImagen.setMinimumSize(new java.awt.Dimension(100, 100));
        panelImagen.setPreferredSize(new java.awt.Dimension(100, 150));
        panelImagen.setLayout(new javax.swing.BoxLayout(panelImagen, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(panelImagen);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelImagen;
    private javax.swing.JLabel txtDescripcion;
    private javax.swing.JTextField txt_entrada1;
    // End of variables declaration//GEN-END:variables
}
