/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.BDLocal;
import modelo.Plato;
import util.FontLoader;
import util.IconUtils;
import javax.swing.SwingConstants;
import util.UserUtil;



/**
 *
 * @author OMAR
 */
public class VistaAdmin extends javax.swing.JFrame {
    private BDLocal bdLocal = new BDLocal();
            
    
    public VistaAdmin() {
        initComponents();
        
        // jMenuInicio.setDebugGraphicsOptions(0);
         
        // Estilo igual al resto de menús visibles
        jMenuInicioX.setOpaque(true);
        jMenuInicioX.setBackground(new Color(239, 171, 106));
        jMenuInicioX.setForeground(new Color(89, 63, 40));
        jMenuInicioX.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jMenuInicioX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-cucharaInicio-25.png")));
        jMenuInicioX.setHorizontalAlignment(SwingConstants.CENTER);
        jMenuInicioX.setIconTextGap(12);
        jMenuInicioX.setPreferredSize(new Dimension(150, 55));
        jMenuInicioX.add(jMenuItem3);
        jMenuInicioX.add(jSeparator2);
        jMenuInicioX.add(jMenuItem4);
        
        Color colorMenu = new Color(89,63,40);
        Font botones = FontLoader.load("Poppins-SemiBold.ttf", 16);
        jMenuMesas.setFont(botones);
        jMenuDia.setFont(botones);


        jMenuMesas.setIcon(IconUtils.tintIcon(jMenuMesas.getIcon(), colorMenu));
        jMenuMesas.setForeground(colorMenu);
        jMenuDia.setIcon(IconUtils.tintIcon(jMenuDia.getIcon(), colorMenu));
        jMenu.setIcon(IconUtils.tintIcon(jMenu.getIcon(), colorMenu));
        jMenuDia.setForeground(colorMenu);
        this.setSize(new Dimension(1200,700));
        this.setLocationRelativeTo(null);
        this.setTitle("Restaurante La Buena Sartén");
        //establecer el color de fondo
        Color colorFondo = new java.awt.Color(210,210,0);
        this.getContentPane().setBackground(colorFondo);
        //this.jPanel1.setBackground(new Color(241,233,233));
        //jPanel1.setVisible(false);
        // Colores personalizados para el JMenuBar y los JMenu
        Color colorMenu2 = new Color(240, 240, 240); // color naranja claro
        jMenuBar3.setOpaque(true);
        jMenuBar3.setBackground(colorMenu2);
//jMenu.setPreferredSize(new Dimension(60, 55));

        //jMenuInicio.setBackground(colorMenu2);
        //jMenuInicio.setOpaque(true);
        jMenuDia.setBackground(colorMenu2);
        jMenuDia.setOpaque(true);
        jMenuMesas.setBackground(colorMenu2);
        jMenuMesas.setOpaque(true);
        //para inabilitar algunos jMenu
        
        // Configurar el JDesktopPane

        // Asegurar que el JDesktopPane ocupe todo el espacio disponible
        //this.setLayout(new BorderLayout());
        //jDesktopPane_menu.setPreferredSize(new Dimension(1200, 650));
        //this.add(jDesktopPane_menu, BorderLayout.CENTER);
        
            // 🔧 Corrección del color del texto
    //jMenuInicio.setForeground(Color.BLACK);

    // 🔧 Verificación: quitar temporalmente el ícono si causa el error
  //  jMenuInicio.setIcon(null);

    // 🔧 Cambiar fuente por defecto
  //  jMenuInicio.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        // Forzar el tamaño del JDesktopPane
        //jDesktopPane_menu.setSize(1200, 650);
        jMenuDia.setPreferredSize(new Dimension(150, 55));
        jMenuMesas.setPreferredSize(new Dimension(150, 55));


        
        // Asegurar que todos los contenedores tengan el mismo color de fondo
        this.getRootPane().setBackground(colorFondo);
        this.getLayeredPane().setBackground(colorFondo);
        this.getContentPane().setBackground(colorFondo);
        try {
            InicioAdmin entradas = new InicioAdmin();
            
            
            
            // Agregar y mostrar el JInternalFrame
            jDesktopPane_menu.add(entradas);
            entradas.setLocation(0,0);
            entradas.setVisible(true);
            entradas.setMaximum(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al abrir Entradas: " + e.getMessage());
        }
        
        System.out.println("Total menús: " + jMenuBar3.getMenuCount());
        for (int i = 0; i < jMenuBar3.getMenuCount(); i++) {
            System.out.println("- " + jMenuBar3.getMenu(i).getText());
        }

       if (jMenuInicio.getParent() == null) {
    jMenuBar3.add(jMenuInicio);
    jMenuBar3.revalidate();
    jMenuBar3.repaint();
}

    }
    
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane_menu = new javax.swing.JDesktopPane();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuInicioX = new javax.swing.JMenu();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuDia = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuMesas = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuInicio = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout jDesktopPane_menuLayout = new javax.swing.GroupLayout(jDesktopPane_menu);
        jDesktopPane_menu.setLayout(jDesktopPane_menuLayout);
        jDesktopPane_menuLayout.setHorizontalGroup(
            jDesktopPane_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        jDesktopPane_menuLayout.setVerticalGroup(
            jDesktopPane_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane_menu);

        jMenuBar3.setBackground(new java.awt.Color(239, 171, 106));
        jMenuBar3.setBorderPainted(false);
        jMenuBar3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar3.setPreferredSize(new java.awt.Dimension(70, 55));

        jMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-menú-30.png"))); // NOI18N
        jMenu.setBorderPainted(false);
        jMenu.setContentAreaFilled(false);
        jMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jMenu.setIconTextGap(12);
        jMenu.setMargin(null);
        jMenu.setMinimumSize(new java.awt.Dimension(20, 20));

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-sign-30.png"))); // NOI18N
        jMenuItem1.setText("Cerrar Sesion");
        jMenuItem1.setPreferredSize(new java.awt.Dimension(150, 40));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItem1);
        jMenu.add(jSeparator1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-booking-30.png"))); // NOI18N
        jMenuItem2.setText("Reservaciones");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(150, 40));
        jMenu.add(jMenuItem2);

        jMenuBar3.add(jMenu);

        jMenuInicioX.setBackground(new java.awt.Color(226, 139, 110));
        jMenuInicioX.setBorder(null);
        jMenuInicioX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-cucharaInicio-25.png"))); // NOI18N
        jMenuInicioX.setText("Inicio");
        jMenuInicioX.setBorderPainted(false);
        jMenuInicioX.setContentAreaFilled(false);
        jMenuInicioX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuInicioX.setIconTextGap(12);
        jMenuInicioX.setMargin(null);
        jMenuInicioX.setPreferredSize(new java.awt.Dimension(120, 55));
        jMenuInicioX.add(jSeparator9);
        jMenuInicioX.add(jSeparator10);

        jMenuBar3.add(jMenuInicioX);

        jMenuDia.setBackground(new java.awt.Color(239, 171, 106));
        jMenuDia.setBorder(null);
        jMenuDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-libro-de-cocina-24.png"))); // NOI18N
        jMenuDia.setText("Menú del día");
        jMenuDia.setBorderPainted(false);
        jMenuDia.setContentAreaFilled(false);
        jMenuDia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuDia.setIconTextGap(12);
        jMenuDia.setMargin(null);
        jMenuDia.setPreferredSize(new java.awt.Dimension(180, 55));

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-dining-room-16.png"))); // NOI18N
        jMenuItem5.setText("Entradas");
        jMenuItem5.setPreferredSize(new java.awt.Dimension(90, 30));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuDia.add(jMenuItem5);
        jMenuDia.add(jSeparator3);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-cutlery-16.png"))); // NOI18N
        jMenuItem6.setText("Fondos");
        jMenuItem6.setPreferredSize(new java.awt.Dimension(90, 30));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuDia.add(jMenuItem6);
        jMenuDia.add(jSeparator4);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-pie-16.png"))); // NOI18N
        jMenuItem7.setText("Postres");
        jMenuItem7.setPreferredSize(new java.awt.Dimension(90, 30));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuDia.add(jMenuItem7);
        jMenuDia.add(jSeparator5);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-cóctel-16.png"))); // NOI18N
        jMenuItem8.setText("Bebidas");
        jMenuItem8.setPreferredSize(new java.awt.Dimension(90, 30));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenuDia.add(jMenuItem8);

        jMenuBar3.add(jMenuDia);

        jMenuMesas.setBackground(new java.awt.Color(226, 139, 110));
        jMenuMesas.setBorder(null);
        jMenuMesas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-mesa-de-restaurante-24.png"))); // NOI18N
        jMenuMesas.setText("Mesas disponibles");
        jMenuMesas.setBorderPainted(false);
        jMenuMesas.setContentAreaFilled(false);
        jMenuMesas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuMesas.setIconTextGap(12);
        jMenuMesas.setMargin(null);
        jMenuMesas.setPreferredSize(new java.awt.Dimension(215, 55));

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-mesa-24.png"))); // NOI18N
        jMenuItem9.setText("Primer Piso");
        jMenuItem9.setPreferredSize(new java.awt.Dimension(130, 30));
        jMenuMesas.add(jMenuItem9);
        jMenuMesas.add(jSeparator6);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-vista-de-mesa-24.png"))); // NOI18N
        jMenuItem10.setText("Segundo Piso");
        jMenuItem10.setPreferredSize(new java.awt.Dimension(130, 30));
        jMenuMesas.add(jMenuItem10);
        jMenuMesas.add(jSeparator7);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-mesa-de-restaurante-24 (1).png"))); // NOI18N
        jMenuItem11.setText("Azotea");
        jMenuItem11.setPreferredSize(new java.awt.Dimension(130, 30));
        jMenuMesas.add(jMenuItem11);

        jMenuBar3.add(jMenuMesas);

        jMenuInicio.setBackground(new java.awt.Color(239, 171, 106));
        jMenuInicio.setBorder(null);
        jMenuInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-cucharaInicio-25.png"))); // NOI18N
        jMenuInicio.setText("Inicio");
        jMenuInicio.setBorderPainted(false);
        jMenuInicio.setContentAreaFilled(false);
        jMenuInicio.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jMenuInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuInicio.setIconTextGap(12);
        jMenuInicio.setMargin(null);
        jMenuInicio.setPreferredSize(new java.awt.Dimension(120, 55));

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-add-bookmark-30.png"))); // NOI18N
        jMenuItem3.setText("Novedades");
        jMenuItem3.setPreferredSize(new java.awt.Dimension(130, 30));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuInicio.add(jMenuItem3);
        jMenuInicio.add(jSeparator2);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-últimas-24-horas-30.png"))); // NOI18N
        jMenuItem4.setText("Promociones");
        jMenuItem4.setPreferredSize(new java.awt.Dimension(130, 30));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuInicio.add(jMenuItem4);
        jMenuInicio.add(jSeparator8);

        jMenuBar3.add(jMenuInicio);

        setJMenuBar(jMenuBar3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        crearCarta("- ENTRADAS -", bdLocal.getEntradas());
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        crearCarta("- FONDOS -", bdLocal.getFondos());
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        crearCarta("- POSTRES -", bdLocal.getPostres());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        crearCarta("- BEBIDAS -", bdLocal.getBebidas());
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        UserUtil.borrarUsuarioLocal();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            Promociones promociones = new Promociones();

            // Asegurar que el JInternalFrame tenga el tamaño correcto
            promociones.setSize(1200, 630);

            // Calcular la posición X para centrar horizontalmente
            int x = (jDesktopPane_menu.getWidth() - promociones.getWidth()) / 2;

            // Colocar el JInternalFrame pegado a la parte superior con un pequeño margen
            int y = 0;

            // Agregar y mostrar el JInternalFrame
            jDesktopPane_menu.add(promociones);
            promociones.setLocation(x, y);
            promociones.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al abrir Promociones: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            Novedades novedades = new Novedades();

            // Asegurar que el JInternalFrame tenga el tamaño correcto
            novedades.setSize(1200, 630);

            // Calcular la posición X para centrar horizontalmente
            int x = (jDesktopPane_menu.getWidth() - novedades.getWidth()) / 2;

            // Colocar el JInternalFrame pegado a la parte superior con un pequeño margen
            int y = 0;

            // Agregar y mostrar el JInternalFrame
            jDesktopPane_menu.add(novedades);
            novedades.setLocation(x, y);
            novedades.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al abrir Novedades: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void crearCarta(String titulo, List<Plato> carta) {
        try {
            CartaVista entradas = new CartaVista(titulo, carta);
            
            // Asegurar que el JInternalFrame tenga el tamaño correcto
            entradas.setSize(600, jDesktopPane_menu.getHeight());
            
            // Calcular la posición X para centrar horizontalmente
            int x = (jDesktopPane_menu.getWidth() - entradas.getWidth()) / 2;
            
            // Colocar el JInternalFrame pegado a la parte superior con un pequeño margen
            int y = 0;
            
            // Agregar y mostrar el JInternalFrame
            jDesktopPane_menu.add(entradas);
            entradas.setLocation(x, y);
            entradas.setVisible(true);            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al abrir Entradas: " + e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane_menu;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenu jMenuDia;
    private javax.swing.JMenu jMenuInicio;
    private javax.swing.JMenu jMenuInicioX;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu jMenuMesas;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    // End of variables declaration//GEN-END:variables

    // Eliminar este método ya que no es necesario y causa conflicto
    /*private void setExtendendedState(int MAXIMIZED_BOTH) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/
}
