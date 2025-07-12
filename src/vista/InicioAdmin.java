/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;
import data.DataRepository;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Novedad;
import modelo.Promocion;
import modelo.Resultado;
import java.io.File;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
 *
 * @author Gian Marrufo
 */
public class InicioAdmin extends javax.swing.JInternalFrame {

    /**
     * Creates new form InicioAdmin
     */
    // Novedades
    JDateChooser dateInicioNovedad = new JDateChooser();
    JDateChooser dateFinNovedad = new JDateChooser();

    // Promociones
    JDateChooser dateInicioPromo = new JDateChooser();
    JDateChooser dateFinPromo = new JDateChooser();

    
    private javax.swing.JTable tablaNovedades;
    private javax.swing.JTable tablaPromociones;
    
    // Campos NOVEDAD
    private JTextField txtTituloNovedad;
    private JTextArea txtDescNovedad;
    private JTextField txtImgNovedad;


    // Campos PROMOCION
    private JTextField txtTituloPromo;
    private JTextArea txtDescPromo;
    private JTextField txtImgPromo;
    private JTextField txtDescuento;


    public InicioAdmin() {
        initComponents();
        
    
    // 1. Inicializar tablas con modelo por defecto
    tablaNovedades = new JTable(new DefaultTableModel(
        new Object[]{"Título", "Descripción", "Imagen", "Inicio", "Fin"}, 0));
    tablaPromociones = new JTable(new DefaultTableModel(
        new Object[]{"Título", "Descripción", "Descuento", "Inicio", "Fin", "Imagen"}, 0));

    tablaNovedades.setPreferredScrollableViewportSize(new java.awt.Dimension(600, 250));
    tablaPromociones.setPreferredScrollableViewportSize(new java.awt.Dimension(600, 250));

    tablaNovedades.setFillsViewportHeight(true);
    tablaPromociones.setFillsViewportHeight(true);

    // === NOVEDADES ===
    JLabel lblTituloNovedad = new JLabel("Título:");
    txtTituloNovedad = new JTextField(25);

    JLabel lblDescNovedad = new JLabel("Descripción:");
    txtDescNovedad = new JTextArea(3, 25);
    txtDescNovedad.setLineWrap(true);
    txtDescNovedad.setWrapStyleWord(true);
    JScrollPane scrollDescNovedad = new JScrollPane(txtDescNovedad);

    JLabel lblImgNovedad = new JLabel("Ruta imagen:");
    txtImgNovedad = new JTextField(25);
    JButton btnSeleccionarImgNovedad = new JButton("Buscar");

    btnSeleccionarImgNovedad.addActionListener(evt -> {
        JFileChooser fileChooser = new JFileChooser(new File("src/resources"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            txtImgNovedad.setText(selectedFile.getPath());
        }
    });

    JLabel lblInicioNovedad = new JLabel("Fecha inicio:");
    JLabel lblFinNovedad = new JLabel("Fecha fin:");

    JButton btnAgregarNovedad = new JButton("Agregar");
    JButton btnEditarNovedad = new JButton("Editar");
    JButton btnEliminarNovedad = new JButton("Eliminar");

    btnAgregarNovedad.setPreferredSize(new Dimension(100, 30));
    btnEditarNovedad.setPreferredSize(new Dimension(100, 30));
    btnEliminarNovedad.setPreferredSize(new Dimension(100, 30));

    JPanel formNovedad = new JPanel();
    formNovedad.setLayout(new BoxLayout(formNovedad, BoxLayout.Y_AXIS));
    formNovedad.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    formNovedad.add(lblTituloNovedad);
    formNovedad.add(txtTituloNovedad);
    formNovedad.add(lblDescNovedad);
    formNovedad.add(scrollDescNovedad);
    formNovedad.add(lblImgNovedad);
    formNovedad.add(txtImgNovedad);
    formNovedad.add(btnSeleccionarImgNovedad); // Buscar imagen
    formNovedad.add(lblInicioNovedad);
    formNovedad.add(dateInicioNovedad);
    formNovedad.add(lblFinNovedad);
    formNovedad.add(dateFinNovedad);

    JPanel botonesNovedad = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    botonesNovedad.add(btnAgregarNovedad);
    botonesNovedad.add(btnEditarNovedad);
    botonesNovedad.add(btnEliminarNovedad);

    JPanel panelIzquierdoNovedad = new JPanel(new BorderLayout());
    panelIzquierdoNovedad.add(formNovedad, BorderLayout.CENTER);
    panelIzquierdoNovedad.add(botonesNovedad, BorderLayout.SOUTH);

    JScrollPane scrollTablaNovedad = new JScrollPane(tablaNovedades);
    scrollTablaNovedad.setBorder(BorderFactory.createTitledBorder("Lista de Novedades"));

    JSplitPane splitNovedad = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoNovedad, scrollTablaNovedad);
    splitNovedad.setResizeWeight(0.4);

    jPanel1.removeAll();
    jPanel1.setLayout(new BorderLayout());
    jPanel1.add(splitNovedad, BorderLayout.CENTER);

    // === PROMOCIONES ===
    JLabel lblTituloPromo = new JLabel("Título:");
    txtTituloPromo = new JTextField(25);

    JLabel lblDescPromo = new JLabel("Descripción:");
    txtDescPromo = new JTextArea(3, 25);
    txtDescPromo.setLineWrap(true);
    txtDescPromo.setWrapStyleWord(true);
    JScrollPane scrollDescPromo = new JScrollPane(txtDescPromo);

    JLabel lblImgPromo = new JLabel("Ruta imagen:");
    txtImgPromo = new JTextField(25);
    JButton btnSeleccionarImgPromo = new JButton("Buscar");

    btnSeleccionarImgPromo.addActionListener(evt -> {
        JFileChooser fileChooser = new JFileChooser(new File("src/resources"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            txtImgPromo.setText(selectedFile.getPath());
        }
    });

    JLabel lblInicio = new JLabel("Fecha inicio:");
    JLabel lblFin = new JLabel("Fecha fin:");
    JLabel lblDescuento = new JLabel("Descuento (%):");
    txtDescuento = new JTextField(5);

    JButton btnAgregarPromo = new JButton("Agregar");
    JButton btnEditarPromo = new JButton("Editar");
    JButton btnEliminarPromo = new JButton("Eliminar");

    btnAgregarPromo.setPreferredSize(new Dimension(100, 30));
    btnEditarPromo.setPreferredSize(new Dimension(100, 30));
    btnEliminarPromo.setPreferredSize(new Dimension(100, 30));

    JPanel formPromo = new JPanel();
    formPromo.setLayout(new BoxLayout(formPromo, BoxLayout.Y_AXIS));
    formPromo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    formPromo.add(lblTituloPromo);
    formPromo.add(txtTituloPromo);
    formPromo.add(lblDescPromo);
    formPromo.add(scrollDescPromo);
    formPromo.add(lblImgPromo);
    formPromo.add(txtImgPromo);
    formPromo.add(btnSeleccionarImgPromo); // Buscar imagen
    formPromo.add(lblInicio);
    formPromo.add(dateInicioPromo);
    formPromo.add(lblFin);
    formPromo.add(dateFinPromo);
    formPromo.add(lblDescuento);
    formPromo.add(txtDescuento);

    JPanel botonesPromo = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    botonesPromo.add(btnAgregarPromo);
    botonesPromo.add(btnEditarPromo);
    botonesPromo.add(btnEliminarPromo);

    JPanel panelIzquierdoPromo = new JPanel(new BorderLayout());
    panelIzquierdoPromo.add(formPromo, BorderLayout.CENTER);
    panelIzquierdoPromo.add(botonesPromo, BorderLayout.SOUTH);

    JScrollPane scrollTablaPromo = new JScrollPane(tablaPromociones);
    scrollTablaPromo.setBorder(BorderFactory.createTitledBorder("Lista de Promociones"));

    JSplitPane splitPromo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoPromo, scrollTablaPromo);
    splitPromo.setResizeWeight(0.4);

    jPanel2.removeAll();
    jPanel2.setLayout(new BorderLayout());
    jPanel2.add(splitPromo, BorderLayout.CENTER);

     // Agrega listeners a las tablas
    tablaNovedades.getSelectionModel().addListSelectionListener(e -> cargarNovedadSeleccionada());
    tablaPromociones.getSelectionModel().addListSelectionListener(e -> cargarPromocionSeleccionada());

     // Agrega los eventos
    btnAgregarNovedad.addActionListener(e -> agregarNovedad());
    btnEditarNovedad.addActionListener(e -> editarNovedad());
    btnEliminarNovedad.addActionListener(e -> eliminarNovedad());

    btnAgregarPromo.addActionListener(e -> agregarPromocion());
    btnEditarPromo.addActionListener(e -> editarPromocion());
    btnEliminarPromo.addActionListener(e -> eliminarPromocion());

    // Cargar datos
    cargarNovedades();
    cargarPromociones();

    }
    
    private void agregarNovedad() {
        DefaultTableModel model = (DefaultTableModel) tablaNovedades.getModel();
        model.addRow(new Object[]{
            txtTituloNovedad.getText(),
            txtDescNovedad.getText(),
            txtImgNovedad.getText(),
            ((JTextField) dateInicioNovedad.getDateEditor().getUiComponent()).getText(),
            ((JTextField) dateFinNovedad.getDateEditor().getUiComponent()).getText()
        });
        limpiarCamposNovedad();
    }

    private void editarNovedad() {
        int fila = tablaNovedades.getSelectedRow();
        if (fila != -1) {
            DefaultTableModel model = (DefaultTableModel) tablaNovedades.getModel();
            model.setValueAt(txtTituloNovedad.getText(), fila, 0);
            model.setValueAt(txtDescNovedad.getText(), fila, 1);
            model.setValueAt(txtImgNovedad.getText(), fila, 2);
            model.setValueAt(((JTextField) dateInicioNovedad.getDateEditor().getUiComponent()).getText(), fila, 3);
            model.setValueAt(((JTextField) dateFinNovedad.getDateEditor().getUiComponent()).getText(), fila, 4);
            limpiarCamposNovedad();
        }
    }

    private void eliminarNovedad() {
        int fila = tablaNovedades.getSelectedRow();
        if (fila != -1) {
            ((DefaultTableModel) tablaNovedades.getModel()).removeRow(fila);
            limpiarCamposNovedad();
        }
    }

    private void cargarNovedadSeleccionada() {
        int fila = tablaNovedades.getSelectedRow();
        if (fila != -1) {
            txtTituloNovedad.setText(tablaNovedades.getValueAt(fila, 0).toString());
            txtDescNovedad.setText(tablaNovedades.getValueAt(fila, 1).toString());
            txtImgNovedad.setText(tablaNovedades.getValueAt(fila, 2).toString());
            // Asumiendo fechas como texto, puedes parsearlas si es necesario
            ((JTextField) dateInicioNovedad.getDateEditor().getUiComponent()).setText(tablaNovedades.getValueAt(fila, 3).toString());
            ((JTextField) dateFinNovedad.getDateEditor().getUiComponent()).setText(tablaNovedades.getValueAt(fila, 4).toString());
        }
    }

    private void limpiarCamposNovedad() {
        txtTituloNovedad.setText("");
        txtDescNovedad.setText("");
        txtImgNovedad.setText("");
        dateInicioNovedad.setDate(null);
        dateFinNovedad.setDate(null);
    }

    private void agregarPromocion() {
        DefaultTableModel model = (DefaultTableModel) tablaPromociones.getModel();
        model.addRow(new Object[]{
            txtTituloPromo.getText(),
            txtDescPromo.getText(),
            txtDescuento.getText(),
            ((JTextField) dateInicioPromo.getDateEditor().getUiComponent()).getText(),
            ((JTextField) dateFinPromo.getDateEditor().getUiComponent()).getText(),
            txtImgPromo.getText()
        });
        limpiarCamposPromocion();
    }

    private void editarPromocion() {
        int fila = tablaPromociones.getSelectedRow();
        if (fila != -1) {
            DefaultTableModel model = (DefaultTableModel) tablaPromociones.getModel();
            model.setValueAt(txtTituloPromo.getText(), fila, 0);
            model.setValueAt(txtDescPromo.getText(), fila, 1);
            model.setValueAt(txtDescuento.getText(), fila, 2);
            model.setValueAt(((JTextField) dateInicioPromo.getDateEditor().getUiComponent()).getText(), fila, 3);
            model.setValueAt(((JTextField) dateFinPromo.getDateEditor().getUiComponent()).getText(), fila, 4);
            model.setValueAt(txtImgPromo.getText(), fila, 5);
            limpiarCamposPromocion();
        }
    }

    private void eliminarPromocion() {
        int fila = tablaPromociones.getSelectedRow();
        if (fila != -1) {
            ((DefaultTableModel) tablaPromociones.getModel()).removeRow(fila);
            limpiarCamposPromocion();
        }
    }

    private void cargarPromocionSeleccionada() {
        int fila = tablaPromociones.getSelectedRow();
        if (fila != -1) {
            txtTituloPromo.setText(tablaPromociones.getValueAt(fila, 0).toString());
            txtDescPromo.setText(tablaPromociones.getValueAt(fila, 1).toString());
            txtDescuento.setText(tablaPromociones.getValueAt(fila, 2).toString());
            ((JTextField) dateInicioPromo.getDateEditor().getUiComponent()).setText(tablaPromociones.getValueAt(fila, 3).toString());
            ((JTextField) dateFinPromo.getDateEditor().getUiComponent()).setText(tablaPromociones.getValueAt(fila, 4).toString());
            txtImgPromo.setText(tablaPromociones.getValueAt(fila, 5).toString());
        }
    }

    private void limpiarCamposPromocion() {
        txtTituloPromo.setText("");
        txtDescPromo.setText("");
        txtDescuento.setText("");
        txtImgPromo.setText("");
        dateInicioPromo.setDate(null);
        dateFinPromo.setDate(null);
    }
    
    
    private void cargarNovedades() {
        Resultado<List<Novedad>> resultado = DataRepository.obtenerNovedades();

        if (!resultado.isOk()) {
            System.out.println("Error al cargar novedades: " + resultado.getMessage());
            return;
        }

        List<Novedad> lista = resultado.getData();

        System.out.println("Tamaño de la lista de novedades: " + lista.size());  // Agrega esta línea

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Título");
        model.addColumn("Descripción");
        model.addColumn("Imagen");

        for (Novedad n : lista) {
            model.addRow(new Object[]{n.getTitulo(), n.getDescripcion(), n.getImageUrl()});
        }

        tablaNovedades.setModel(model);
    }

    private void cargarPromociones() {
        String[] columnas = {"Título", "Descripción", "Descuento", "Inicio", "Fin", "Imagen"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        Resultado<List<Promocion>> resultado = DataRepository.obtenerPromociones();
        if (resultado.isOk()) {
            for (Promocion promo : resultado.getData()) {
                Object[] fila = {
                    promo.getTitulo(),
                    promo.getDescripcion(),
                    promo.getDescuentoPorcentaje(),
                    promo.getFechaInicio(),
                    promo.getFechaFin(),
                    promo.getImageUrl()
                };
                modelo.addRow(fila);
            }
        }

        tablaPromociones.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PANEL DE ADMINISTRACIÓN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("NOVEDADES", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("PROMOCIONES", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("MENUS", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("PEDIDOS", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        ImageIcon iconoPromo = new ImageIcon(getClass().getResource("/resources/icons8-últimas-24-horas-30.png"));
        jTabbedPane1.setIconAt(0, iconoPromo); // Primera pestaña: Promociones
        ImageIcon iconoNovedad = new ImageIcon(getClass().getResource("/resources/icons8-add-bookmark-30.png"));
        jTabbedPane1.setIconAt(1, iconoNovedad); // Primera pestaña: Promociones
        ImageIcon iconoMenu = new ImageIcon(getClass().getResource("/resources/icons8-cutlery-16.png"));
        jTabbedPane1.setIconAt(2, iconoMenu); // Primera pestaña: Promociones
        ImageIcon iconoPedido = new ImageIcon(getClass().getResource("/resources/menu_book_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24(1).png"));
        jTabbedPane1.setIconAt(3, iconoPedido); // Primera pestaña: Promociones

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
