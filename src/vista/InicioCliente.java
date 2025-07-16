/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import data.DataRepository;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import modelo.Novedad;
import modelo.Plato;
import modelo.Promocion;
import modelo.Resultado;
import util.FontLoader;
import view.ComicPromoLabel;
import view.ImagePanel;

/**
 *
 * @author salaz
 */
public final class InicioCliente extends javax.swing.JInternalFrame {

    private List<Promocion> promociones;
    private List<Novedad> novedades;
    private List<Plato> fondos;
    final AtomicInteger indicePromociones = new AtomicInteger(0);
    final AtomicInteger indiceNovedades = new AtomicInteger(0);
    final AtomicInteger indiceFondos = new AtomicInteger(0);

    /**
     * Creates new form InicioCliente
     */
    public InicioCliente() {
        initComponents();
        labelPromo.setFont(FontLoader.load("Poppins-Black.ttf", Font.PLAIN, 20));
        textPromoTitle.setFont(FontLoader.load("Poppins-SemiBold.ttf", Font.PLAIN, 18));
        textPromoDesc.setFont(FontLoader.load("Poppins-Regular.ttf", Font.PLAIN, 14));

        labelNovedades.setFont(FontLoader.load("Poppins-Black.ttf", Font.PLAIN, 20));
        textNovTitle.setFont(FontLoader.load("Poppins-SemiBold.ttf", Font.PLAIN, 18));
        textNovDesc.setFont(FontLoader.load("Poppins-Regular.ttf", Font.PLAIN, 14));

        labelCarta.setFont(FontLoader.load("Poppins-Black.ttf", Font.PLAIN, 20));
        textCartaTitle.setFont(FontLoader.load("Poppins-SemiBold.ttf", Font.PLAIN, 18));
        obtenerNovedades();
    }

    void obtenerPromociones() {
        new SwingWorker<Resultado<List<Promocion>>, Void>() {
            @Override
            protected Resultado<List<Promocion>> doInBackground() throws Exception {
                return DataRepository.obtenerPromociones();
            }

            @Override
            protected void done() {
                super.done();
                System.err.println("obtenido");
                Resultado<List<Promocion>> resultado = null;
                try {
                    resultado = get();
                    promociones = resultado.getData();
                    if (promociones.isEmpty()) {
                        System.err.println("vacio");
                    } else {
                        System.err.println("mostrando");
                        mostrarPromociones();
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();

                    Logger.getLogger(InicioCliente.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    obtenerFondos();
                }

            }

        }.execute();
    }

    void obtenerNovedades() {
        new SwingWorker<Resultado<List<Novedad>>, Void>() {
            @Override
            protected Resultado<List<Novedad>> doInBackground() throws Exception {
                return DataRepository.obtenerNovedades();
            }

            @Override
            protected void done() {
                super.done();
                Resultado<List<Novedad>> resultado = null;
                try {
                    resultado = get();
                    novedades = resultado.getData();
                    if (novedades.isEmpty()) {
                    } else {
                        mostrarNovedades();
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(InicioCliente.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    obtenerPromociones();
                }

            }

        }.execute();
    }

    void obtenerFondos() {
        new SwingWorker<Resultado<List<Plato>>, Void>() {
            @Override
            protected Resultado<List<Plato>> doInBackground() throws Exception {
                return DataRepository.getFondos();
            }

            @Override
            protected void done() {
                super.done();
                System.err.println("obtenido");
                Resultado<List<Plato>> resultado = null;
                try {
                    resultado = get();
                    fondos = resultado.getData();
                    if (fondos.isEmpty()) {
                        System.err.println("vacio");
                    } else {
                        System.err.println("mostrando");
                        mostrarFondos();
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();

                    Logger.getLogger(InicioCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }.execute();
    }

    void mostrarPromociones() {
        int delay = 5000;

        mostrarPromocion(promociones.get(indicePromociones.get()));
        Timer timer = new Timer(delay, (ActionEvent e) -> {
            if (indicePromociones.get() < promociones.size() - 1) {
                indicePromociones.set(indicePromociones.get() + 1);
            } else {
                indicePromociones.set(0);
            }
            mostrarPromocion(promociones.get(indicePromociones.get()));
        });

        timer.start();
    }

    void mostrarPromocion(Promocion promocion) {
        textPromoTitle.setText(promocion.getTitulo());
        textPromoDesc.setText(promocion.getDescripcion());
        panelPromoCard.removeAll();
        ImagePanel imagen = new ImagePanel(promocion.getImageUrl(), ImagePanel.FitMode.CONTAIN);
        imagen.setAlignmentX(0.5f);
        imagen.setAlignmentY(0.5f);

        if (promocion.getDescuentoPorcentaje() > 0) {
            ComicPromoLabel badge = new ComicPromoLabel(promocion.getDescuentoPorcentaje() + "%");
            badge.setAlignmentX(0f);
            badge.setAlignmentY(0f);
            panelPromoCard.add(badge);
        }
        panelPromoCard.add(imagen);

    }

    void mostrarNovedades() {
        int delay = 5000;

        mostrarNovedad(novedades.get(indiceNovedades.get()));
        Timer timer = new Timer(delay, (ActionEvent e) -> {
            if (indiceNovedades.get() < novedades.size() - 1) {
                indiceNovedades.set(indiceNovedades.get() + 1);
            } else {
                indiceNovedades.set(0);
            }
            mostrarNovedad(novedades.get(indiceNovedades.get()));
        });

        timer.start();
    }

    void mostrarNovedad(Novedad novedad) {
        textNovTitle.setText(novedad.getTitulo());
        textNovDesc.setText(novedad.getDescripcion());
        panelNovedCard.removeAll();
        ImagePanel imagen = new ImagePanel(novedad.getImageUrl(), ImagePanel.FitMode.CONTAIN);
        imagen.setAlignmentX(0.5f);
        imagen.setAlignmentY(0.5f);
        panelNovedCard.add(imagen);

    }

    void mostrarFondos() {
        int delay = 5000;

        mostrarFondo(fondos.get(indiceFondos.get()));
        Timer timer = new Timer(delay, (ActionEvent e) -> {
            if (indiceFondos.get() < fondos.size() - 1) {
                indiceFondos.set(indiceFondos.get() + 1);
            } else {
                indiceFondos.set(0);
            }
            mostrarFondo(fondos.get(indiceFondos.get()));
        });

        timer.start();
    }

    void mostrarFondo(Plato plato) {
        textCartaTitle.setText(plato.getNombre());
        panelCartaCard.removeAll();
        ImagePanel imagen = new ImagePanel(plato.getUrlImagen());
        imagen.setAlignmentX(0.5f);
        imagen.setAlignmentY(0.5f);

        panelCartaCard.add(imagen);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        labelNovedades = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 8));
        panelNovedCard = new javax.swing.JPanel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 16), new java.awt.Dimension(0, 16), new java.awt.Dimension(32767, 16));
        textNovTitle = new javax.swing.JLabel();
        textNovDesc = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel1 = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        labelPromo = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 8));
        panelPromoCard = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 16), new java.awt.Dimension(0, 16), new java.awt.Dimension(32767, 16));
        textPromoTitle = new javax.swing.JLabel();
        textPromoDesc = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel3 = new javax.swing.JPanel();
        labelCarta = new javax.swing.JLabel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 8));
        panelCartaCard = new javax.swing.JPanel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 16), new java.awt.Dimension(0, 16), new java.awt.Dimension(32767, 16));
        textCartaTitle = new javax.swing.JLabel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 16), new java.awt.Dimension(0, 16), new java.awt.Dimension(32767, 16));
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 16), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 16));
        jLabel1 = new javax.swing.JLabel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 16), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 16));
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setBorder(null);
        getContentPane().setLayout(new java.awt.GridLayout(1, 3));

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 32, 16, 32));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));
        jPanel4.add(filler6);

        labelNovedades.setForeground(new java.awt.Color(51, 51, 51));
        labelNovedades.setText("Novedades");
        jPanel4.add(labelNovedades);
        jPanel4.add(filler2);

        panelNovedCard.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelNovedCard.setLayout(new javax.swing.OverlayLayout(panelNovedCard));
        jPanel4.add(panelNovedCard);
        jPanel4.add(filler7);

        textNovTitle.setForeground(new java.awt.Color(102, 102, 102));
        textNovTitle.setText("Titulo");
        jPanel4.add(textNovTitle);

        textNovDesc.setForeground(new java.awt.Color(102, 102, 102));
        textNovDesc.setText("Descripcion");
        jPanel4.add(textNovDesc);
        jPanel4.add(filler8);

        getContentPane().add(jPanel4);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 32, 16, 32));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jPanel1.add(filler4);

        labelPromo.setForeground(new java.awt.Color(51, 51, 51));
        labelPromo.setText("Promociones");
        jPanel1.add(labelPromo);
        jPanel1.add(filler1);

        panelPromoCard.setAlignmentX(0.0F);
        panelPromoCard.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelPromoCard.setLayout(new javax.swing.OverlayLayout(panelPromoCard));
        jPanel1.add(panelPromoCard);
        jPanel1.add(filler3);

        textPromoTitle.setForeground(new java.awt.Color(102, 102, 102));
        textPromoTitle.setText("Titulo");
        jPanel1.add(textPromoTitle);

        textPromoDesc.setForeground(new java.awt.Color(102, 102, 102));
        textPromoDesc.setText("Descripcion");
        jPanel1.add(textPromoDesc);
        jPanel1.add(filler5);

        getContentPane().add(jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 32, 16, 32));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        labelCarta.setForeground(new java.awt.Color(51, 51, 51));
        labelCarta.setText("En la carta...");
        labelCarta.setAlignmentX(0.5F);
        jPanel3.add(labelCarta);
        jPanel3.add(filler9);

        panelCartaCard.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelCartaCard.setLayout(new javax.swing.OverlayLayout(panelCartaCard));
        jPanel3.add(panelCartaCard);
        jPanel3.add(filler10);

        textCartaTitle.setForeground(new java.awt.Color(102, 102, 102));
        textCartaTitle.setText("Titulo");
        textCartaTitle.setAlignmentX(0.5F);
        jPanel3.add(textCartaTitle);
        jPanel3.add(filler11);

        jPanel2.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jButton1.setText("Reservar Ahora !!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel2);
        jPanel3.add(filler13);

        jLabel1.setText("o");
        jLabel1.setAlignmentX(0.5F);
        jPanel3.add(jLabel1);
        jPanel3.add(filler12);

        jPanel5.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jButton2.setText("Ver mis reservaciones");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5);

        getContentPane().add(jPanel3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Reservar().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new ReservasRestaurante().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelCarta;
    private javax.swing.JLabel labelNovedades;
    private javax.swing.JLabel labelPromo;
    private javax.swing.JPanel panelCartaCard;
    private javax.swing.JPanel panelNovedCard;
    private javax.swing.JPanel panelPromoCard;
    private javax.swing.JLabel textCartaTitle;
    private javax.swing.JLabel textNovDesc;
    private javax.swing.JLabel textNovTitle;
    private javax.swing.JLabel textPromoDesc;
    private javax.swing.JLabel textPromoTitle;
    // End of variables declaration//GEN-END:variables
}
