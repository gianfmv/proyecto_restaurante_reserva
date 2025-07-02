/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;
import data.DataRepository;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.Novedad;
import modelo.Promocion;
import modelo.Resultado;

/**
 *
 * @author Gian Marrufo
 */
public class InicioAdmin extends javax.swing.JInternalFrame {

    /**
     * Creates new form InicioAdmin
     */
    
    private javax.swing.JTable tablaNovedades;
    private javax.swing.JTable tablaPromociones;

    public InicioAdmin() {
        initComponents();
        
        // 1. Inicializar las tablas
    tablaNovedades = new javax.swing.JTable();
    tablaPromociones = new javax.swing.JTable();
          // === COMPONENTES PARA NOVEDADES (jPanel1) ===
    javax.swing.JLabel lblTituloNovedad = new javax.swing.JLabel("Título:");
    javax.swing.JTextField txtTituloNovedad = new javax.swing.JTextField(30);

    javax.swing.JLabel lblDescNovedad = new javax.swing.JLabel("Descripción:");
    javax.swing.JTextArea txtDescNovedad = new javax.swing.JTextArea(4, 30);
    txtDescNovedad.setLineWrap(true);
    txtDescNovedad.setWrapStyleWord(true);
    javax.swing.JScrollPane scrollDescNovedad = new javax.swing.JScrollPane(txtDescNovedad);

    javax.swing.JLabel lblImgNovedad = new javax.swing.JLabel("Ruta imagen:");
    javax.swing.JTextField txtImgNovedad = new javax.swing.JTextField(30);

    javax.swing.JButton btnAgregarNovedad = new javax.swing.JButton("Agregar");
    javax.swing.JButton btnEditarNovedad = new javax.swing.JButton("Editar");
    javax.swing.JButton btnEliminarNovedad = new javax.swing.JButton("Eliminar");

    javax.swing.JTable tablaNovedades = new javax.swing.JTable();
    javax.swing.JScrollPane scrollTablaNovedad = new javax.swing.JScrollPane(tablaNovedades);

    javax.swing.JPanel formNovedad = new javax.swing.JPanel();
    formNovedad.setLayout(new java.awt.GridLayout(4, 2, 5, 5));
    formNovedad.add(lblTituloNovedad);
    formNovedad.add(txtTituloNovedad);
    formNovedad.add(lblDescNovedad);
    formNovedad.add(scrollDescNovedad);
    formNovedad.add(lblImgNovedad);
    formNovedad.add(txtImgNovedad);

    javax.swing.JPanel botonesNovedad = new javax.swing.JPanel();
    botonesNovedad.add(btnAgregarNovedad);
    botonesNovedad.add(btnEditarNovedad);
    botonesNovedad.add(btnEliminarNovedad);

    javax.swing.JPanel panelContenidoNovedad = new javax.swing.JPanel();
    panelContenidoNovedad.setLayout(new java.awt.BorderLayout(10, 10));
    panelContenidoNovedad.add(formNovedad, java.awt.BorderLayout.NORTH);
    panelContenidoNovedad.add(botonesNovedad, java.awt.BorderLayout.CENTER);
    panelContenidoNovedad.add(scrollTablaNovedad, java.awt.BorderLayout.SOUTH);

    jPanel1.setLayout(new java.awt.BorderLayout());
    jPanel1.add(panelContenidoNovedad, java.awt.BorderLayout.CENTER);

    // === COMPONENTES PARA PROMOCIONES (jPanel2) ===
    javax.swing.JLabel lblTituloPromo = new javax.swing.JLabel("Título:");
    javax.swing.JTextField txtTituloPromo = new javax.swing.JTextField(30);

    javax.swing.JLabel lblDescPromo = new javax.swing.JLabel("Descripción:");
    javax.swing.JTextArea txtDescPromo = new javax.swing.JTextArea(4, 30);
    txtDescPromo.setLineWrap(true);
    txtDescPromo.setWrapStyleWord(true);
    javax.swing.JScrollPane scrollDescPromo = new javax.swing.JScrollPane(txtDescPromo);

    javax.swing.JLabel lblImgPromo = new javax.swing.JLabel("Ruta imagen:");
    javax.swing.JTextField txtImgPromo = new javax.swing.JTextField(30);

    javax.swing.JLabel lblInicio = new javax.swing.JLabel("Fecha inicio:");
    javax.swing.JTextField txtInicio = new javax.swing.JTextField(10);

    javax.swing.JLabel lblFin = new javax.swing.JLabel("Fecha fin:");
    javax.swing.JTextField txtFin = new javax.swing.JTextField(10);

    javax.swing.JLabel lblDescuento = new javax.swing.JLabel("Descuento (%):");
    javax.swing.JTextField txtDescuento = new javax.swing.JTextField(5);

    javax.swing.JButton btnAgregarPromo = new javax.swing.JButton("Agregar");
    javax.swing.JButton btnEditarPromo = new javax.swing.JButton("Editar");
    javax.swing.JButton btnEliminarPromo = new javax.swing.JButton("Eliminar");

    javax.swing.JTable tablaPromo = new javax.swing.JTable();
    javax.swing.JScrollPane scrollTablaPromo = new javax.swing.JScrollPane(tablaPromo);

    javax.swing.JPanel formPromo = new javax.swing.JPanel();
    formPromo.setLayout(new java.awt.GridLayout(6, 2, 5, 5));
    formPromo.add(lblTituloPromo);
    formPromo.add(txtTituloPromo);
    formPromo.add(lblDescPromo);
    formPromo.add(scrollDescPromo);
    formPromo.add(lblImgPromo);
    formPromo.add(txtImgPromo);
    formPromo.add(lblInicio);
    formPromo.add(txtInicio);
    formPromo.add(lblFin);
    formPromo.add(txtFin);
    formPromo.add(lblDescuento);
    formPromo.add(txtDescuento);

    javax.swing.JPanel botonesPromo = new javax.swing.JPanel();
    botonesPromo.add(btnAgregarPromo);
    botonesPromo.add(btnEditarPromo);
    botonesPromo.add(btnEliminarPromo);

    javax.swing.JPanel panelContenidoPromo = new javax.swing.JPanel();
    panelContenidoPromo.setLayout(new java.awt.BorderLayout(10, 10));
    panelContenidoPromo.add(formPromo, java.awt.BorderLayout.NORTH);
    panelContenidoPromo.add(botonesPromo, java.awt.BorderLayout.CENTER);
    panelContenidoPromo.add(scrollTablaPromo, java.awt.BorderLayout.SOUTH);

    jPanel2.setLayout(new java.awt.BorderLayout());
    jPanel2.add(panelContenidoPromo, java.awt.BorderLayout.CENTER);
    
    
    cargarNovedades();
    cargarPromociones();
    
    }
    
    private void cargarNovedades() {
    String[] columnas = {"titulo", "descripcion", "imagen"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    Resultado<List<Novedad>> resultado = DataRepository.obtenerNovedades();
    if (resultado.isOk()) {
        for (Novedad nov : resultado.getData()) {
            Object[] fila = {
                nov.getTitulo(),
                nov.getDescripcion(),
                nov.getImageUrl()
            };
            modelo.addRow(fila);
        }
    }

    tablaNovedades.setModel(modelo);
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
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PANEL DE ADMINISTRACIÓN");

        jButton5.setText("Registrar");

        jButton6.setText("Editar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(585, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(401, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(107, 107, 107))
        );

        jTabbedPane1.addTab("NOVEDADES", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 973, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("PROMOCIONES", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 973, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("MENUS", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 973, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
