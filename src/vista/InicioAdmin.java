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
import data.UserRepository;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Mesa;
import modelo.Plato;
import modelo.Reserva;
import modelo.TipoUsuario;
import modelo.Usuario;


/**
 *
 * @author Gian Marrufo
 */
public class InicioAdmin extends javax.swing.JInternalFrame {

    // === COMPONENTES NOVEDADES ===
    JDateChooser dateInicioNovedad = new JDateChooser();
    JDateChooser dateFinNovedad = new JDateChooser();
    private javax.swing.JTable tablaNovedades;
    // Campos NOVEDAD
    //private JTextField txtTituloNovedadX;
    private JTextField txtTitNov;
    private JTextArea txtDescNovedad;
    private JTextField txtImgNovedad;
    

    // === COMPONENTES PROMOCIONES ===
    JDateChooser dateInicioPromo = new JDateChooser();
    JDateChooser dateFinPromo = new JDateChooser();
    private javax.swing.JTable tablaPromociones;    
    private JTextField txtTituloPromo;
    private JTextArea txtDescPromo;
    private JTextField txtImgPromo;
    private JTextField txtDescuento;
    
    
    // === COMPONENTES MESAS ===
    private javax.swing.JTable tablaMesas;
    private JTextField txtCapacidad;
    private JTextField txtNroMesa;
    private JCheckBox chkDisponible;
    
    private JPanel mesasPanel;
    private Mesa mesaSeleccionada;
    
    // === COMPONENTES USUARIOS ===
    private JTable tablaUsuarios;
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtDNI;
    private JTextField txtDireccion;
    private JComboBox<TipoUsuario> cboTipoUsuario;
    private JPasswordField txtContrasena;
      
    
    // === COMPONENTES PLATOS ===
    private JTextField txtNombrePlato, txtPrecioPlato, txtImagenPlato;
    private JTextArea txtDescripcionPlato;
    private JComboBox<String> cmbTipoPlato;
    private JLabel lblVistaPreviaImagenPlato;
    private JTable tablaPlatos;
    private DefaultTableModel modeloTablaPlatos;
    private JButton btnAgregarPlato, btnEditarPlato, btnEliminarPlato, btnLimpiarPlato;

    //NO VA PEDIDO AGREGAR RESERVA EN SU LUGAR


  
    public InicioAdmin() {
        initComponents();
        
    //Inicializar componentes y eventos
    inicializarPanelNovedadesEventos();   
    inicializarPanelPromocionesEventos();
    inicializarPanelMesasEventos();
    inicializarPanelUsuariosEventos();
    inicializarPanelPlatos();
    agregarListenerPlatos();
    

    // Cargar datos
    cargarNovedades();
    cargarPromociones();        
    //cargarMesas();
    cargarMesasGraficamente();
    cargarUsuarios();   
    cargarPlatos();

 
    }
    
    
    private void inicializarPanelNovedadesEventos() {
    // === Inicializar tabla de novedades ===
    tablaNovedades = new JTable(new DefaultTableModel(
        new Object[]{"Título", "Descripción", "Imagen", "Inicio", "Fin"}, 0));
    tablaNovedades.setPreferredScrollableViewportSize(new java.awt.Dimension(600, 250));
    tablaNovedades.setFillsViewportHeight(true);

    // === Crear campos del formulario ===
    JLabel lblTituloNovedad = new JLabel("Título:");
    txtTitNov = new JTextField(25);

    JLabel lblDescNovedad = new JLabel("Descripción:");
    txtDescNovedad = new JTextArea(3, 25);
    txtDescNovedad.setLineWrap(true);
    txtDescNovedad.setWrapStyleWord(true);
    JScrollPane scrollDescNovedad = new JScrollPane(txtDescNovedad);

    JLabel lblImgNovedad = new JLabel("Imagen:");
    txtImgNovedad = new JTextField(25);
    JButton btnSeleccionarImgNovedad = new JButton("Buscar"); 
        btnSeleccionarImgNovedad.addActionListener(evt -> {
        JFileChooser fileChooser = new JFileChooser(new File("src/resources"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            txtImgNovedad.setText(selectedFile.getName());
        }
    });

    JLabel lblInicioNovedad = new JLabel("Fecha inicio:");
    JLabel lblFinNovedad = new JLabel("Fecha fin:");

    // === Botones ===
    JButton btnAgregarNovedad = new JButton("Agregar");
    JButton btnEditarNovedad = new JButton("Editar");
    JButton btnEliminarNovedad = new JButton("Eliminar");
    JButton btnLimpiarNovedad = new JButton("Limpiar");

    btnAgregarNovedad.setPreferredSize(new Dimension(100, 30));
    btnEditarNovedad.setPreferredSize(new Dimension(100, 30));
    btnEliminarNovedad.setPreferredSize(new Dimension(100, 30));
    btnLimpiarNovedad.setPreferredSize(new Dimension(100, 30));

    // === Panel de formulario con diseño vertical ===
    JPanel formNovedad = new JPanel();
    formNovedad.setLayout(new BoxLayout(formNovedad, BoxLayout.Y_AXIS));
    formNovedad.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // === Paneles para campos con altura fija ===
    int alturaFija = 30;

    JPanel panelImgNovedad = new JPanel(new BorderLayout());
    panelImgNovedad.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
    panelImgNovedad.setPreferredSize(new Dimension(0, alturaFija));
    panelImgNovedad.add(txtImgNovedad, BorderLayout.CENTER);

    JPanel panelInicioNovedad = new JPanel(new BorderLayout());
    panelInicioNovedad.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
    panelInicioNovedad.setPreferredSize(new Dimension(0, alturaFija));
    panelInicioNovedad.add(dateInicioNovedad, BorderLayout.CENTER);

    JPanel panelFinNovedad = new JPanel(new BorderLayout());
    panelFinNovedad.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
    panelFinNovedad.setPreferredSize(new Dimension(0, alturaFija));
    panelFinNovedad.add(dateFinNovedad, BorderLayout.CENTER);

    // === Añadir campos al panel de formulario ===
    formNovedad.add(lblTituloNovedad);
    formNovedad.add(txtTitNov);
    formNovedad.add(lblDescNovedad);
    formNovedad.add(scrollDescNovedad);
    formNovedad.add(lblImgNovedad);
    formNovedad.add(panelImgNovedad);
    formNovedad.add(btnSeleccionarImgNovedad); // botón para buscar imagen
    formNovedad.add(lblInicioNovedad);
    formNovedad.add(panelInicioNovedad);
    formNovedad.add(lblFinNovedad);
    formNovedad.add(panelFinNovedad);

    // === Panel de botones CRUD ===
    JPanel botonesNovedad = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    botonesNovedad.add(btnAgregarNovedad);
    botonesNovedad.add(btnEditarNovedad);
    botonesNovedad.add(btnEliminarNovedad);
    botonesNovedad.add(btnLimpiarNovedad); 

    // === Panel izquierdo con formulario y botones ===
    JPanel panelIzquierdoNovedad = new JPanel(new BorderLayout());
    panelIzquierdoNovedad.add(formNovedad, BorderLayout.CENTER);
    panelIzquierdoNovedad.add(botonesNovedad, BorderLayout.SOUTH);

    // === Scroll de tabla con borde ===
    JScrollPane scrollTablaNovedad = new JScrollPane(tablaNovedades);
    scrollTablaNovedad.setBorder(BorderFactory.createTitledBorder("Lista de Novedades"));

    // === Dividir en dos columnas: formulario y tabla ===
    JSplitPane splitNovedad = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoNovedad, scrollTablaNovedad);
    splitNovedad.setResizeWeight(0.4); // 40% izquierda

    // === Cargar en el contenedor principal ===
    jPanel1.removeAll();
    jPanel1.setLayout(new BorderLayout());
    jPanel1.add(splitNovedad, BorderLayout.CENTER);

    // === Agregar listeners ===
    tablaNovedades.getSelectionModel().addListSelectionListener(e -> cargarNovedadSeleccionada());
    btnAgregarNovedad.addActionListener(e -> agregarNovedad());
    btnEditarNovedad.addActionListener(e -> editarNovedad());
    btnEliminarNovedad.addActionListener(e -> eliminarNovedad());
    btnLimpiarNovedad.addActionListener(e -> limpiarCamposNovedad());
}   
    private void inicializarPanelPromocionesEventos() {
        // === PROMOCIONES ===
        JLabel lblTituloPromo = new JLabel("Título:");
        txtTituloPromo = new JTextField(25);

        JLabel lblDescPromo = new JLabel("Descripción:");
        txtDescPromo = new JTextArea(3, 25);
        txtDescPromo.setLineWrap(true);
        txtDescPromo.setWrapStyleWord(true);
        JScrollPane scrollDescPromo = new JScrollPane(txtDescPromo);

        JLabel lblImgPromo = new JLabel("Imagen:");
        txtImgPromo = new JTextField(25);
        JButton btnSeleccionarImgPromo = new JButton("Buscar");

        btnSeleccionarImgPromo.addActionListener(evt -> {
            JFileChooser fileChooser = new JFileChooser(new File("src/resources"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                txtImgPromo.setText(selectedFile.getName());
            }
        });

        JLabel lblInicio = new JLabel("Fecha inicio:");
        dateInicioPromo = new JDateChooser();
        JLabel lblFin = new JLabel("Fecha fin:");
        dateFinPromo = new JDateChooser();
        JLabel lblDescuento = new JLabel("Descuento (%):");
        txtDescuento = new JTextField(5);

        JButton btnAgregarPromo = new JButton("Agregar");
        JButton btnEditarPromo = new JButton("Editar");
        JButton btnEliminarPromo = new JButton("Eliminar");
        JButton btnLimpiarPromo = new JButton("Limpiar");

        btnAgregarPromo.setPreferredSize(new Dimension(100, 30));
        btnEditarPromo.setPreferredSize(new Dimension(100, 30));
        btnEliminarPromo.setPreferredSize(new Dimension(100, 30));
        btnLimpiarPromo.setPreferredSize(new Dimension(100, 30));

        // === FORMULARIO IZQUIERDA ===
        JPanel formPromo = new JPanel();
        formPromo.setLayout(new BoxLayout(formPromo, BoxLayout.Y_AXIS));
        formPromo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        int alturaFija = 30;

        JPanel panelInicioPromo = new JPanel(new BorderLayout());
        panelInicioPromo.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelInicioPromo.add(dateInicioPromo, BorderLayout.CENTER);

        JPanel panelFinPromo = new JPanel(new BorderLayout());
        panelFinPromo.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelFinPromo.add(dateFinPromo, BorderLayout.CENTER);

        JPanel panelImgPromo = new JPanel(new BorderLayout());
        panelImgPromo.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelImgPromo.add(txtImgPromo, BorderLayout.CENTER);

        JPanel panelDescPromo = new JPanel(new BorderLayout());
        panelDescPromo.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelDescPromo.add(txtDescuento, BorderLayout.CENTER);

        formPromo.add(lblTituloPromo);
        formPromo.add(txtTituloPromo);
        formPromo.add(lblDescPromo);
        formPromo.add(scrollDescPromo);
        formPromo.add(lblImgPromo);
        formPromo.add(panelImgPromo);
        formPromo.add(btnSeleccionarImgPromo);
        formPromo.add(lblInicio);
        formPromo.add(panelInicioPromo);
        formPromo.add(lblFin);
        formPromo.add(panelFinPromo);
        formPromo.add(lblDescuento);
        formPromo.add(panelDescPromo);

        JPanel botonesPromo = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPromo.add(btnAgregarPromo);
        botonesPromo.add(btnEditarPromo);
        botonesPromo.add(btnEliminarPromo);
        botonesPromo.add(btnLimpiarPromo);

        JPanel panelIzquierdoPromo = new JPanel(new BorderLayout());
        panelIzquierdoPromo.add(formPromo, BorderLayout.CENTER);
        panelIzquierdoPromo.add(botonesPromo, BorderLayout.SOUTH);

        // === TABLA DERECHA ===
        tablaPromociones = new JTable(new DefaultTableModel(
            new Object[]{"Título", "Descripción", "Descuento", "Inicio", "Fin", "Imagen"}, 0));
        tablaPromociones.setPreferredScrollableViewportSize(new Dimension(600, 250));
        tablaPromociones.setFillsViewportHeight(true);

        JScrollPane scrollTablaPromo = new JScrollPane(tablaPromociones);
        scrollTablaPromo.setBorder(BorderFactory.createTitledBorder("Lista de Promociones"));

        // === SPLIT Y ENSAMBLE ===
        JSplitPane splitPromo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoPromo, scrollTablaPromo);
        splitPromo.setResizeWeight(0.4);

        jPanel2.removeAll();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(splitPromo, BorderLayout.CENTER);

        // === LISTENERS ===
        tablaPromociones.getSelectionModel().addListSelectionListener(e -> cargarPromocionSeleccionada());
        btnAgregarPromo.addActionListener(e -> agregarPromocion());
        btnEditarPromo.addActionListener(e -> editarPromocion());
        btnEliminarPromo.addActionListener(e -> eliminarPromocion());
        btnLimpiarPromo.addActionListener(e -> limpiarCamposPromocion());

        // === CARGA DE DATOS INICIAL ===
        cargarPromociones();
    }
    
    private void inicializarPanelMesasEventos() {
        // === FORMULARIO DE MESAS ===
        JLabel lblCapacidad = new JLabel("Capacidad:");
        JLabel lblnroMesa = new JLabel("Nro de Mesa:");

        txtCapacidad = new JTextField(10);
        txtNroMesa = new JTextField(10);
        chkDisponible = new JCheckBox("Disponible");

        JButton btnAgregarMesa = new JButton("Agregar");
        JButton btnEditarMesa = new JButton("Editar");
        JButton btnEliminarMesa = new JButton("Eliminar");
        JButton btnLimpiarMesa = new JButton("Limpiar");

        btnAgregarMesa.setPreferredSize(new Dimension(100, 30));
        btnEditarMesa.setPreferredSize(new Dimension(100, 30));
        btnEliminarMesa.setPreferredSize(new Dimension(100, 30));
        btnLimpiarMesa.setPreferredSize(new Dimension(100, 30));

        JPanel formMesa = new JPanel();
        formMesa.setLayout(new BoxLayout(formMesa, BoxLayout.Y_AXIS));
        formMesa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int alturaFija = 30;

        JPanel panelCapMesa = new JPanel(new BorderLayout());
        panelCapMesa.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelCapMesa.setPreferredSize(new Dimension(0, alturaFija));
        panelCapMesa.add(txtCapacidad, BorderLayout.CENTER);

        JPanel panelNroMesa = new JPanel(new BorderLayout());
        panelNroMesa.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelNroMesa.setPreferredSize(new Dimension(0, alturaFija));
        panelNroMesa.add(txtNroMesa, BorderLayout.CENTER);

        formMesa.add(lblCapacidad);
        formMesa.add(panelCapMesa);
        formMesa.add(lblnroMesa);
        formMesa.add(panelNroMesa);
        formMesa.add(chkDisponible);

        JPanel botonesMesa = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesMesa.add(btnAgregarMesa);
        botonesMesa.add(btnEditarMesa);
        botonesMesa.add(btnEliminarMesa);
        botonesMesa.add(btnLimpiarMesa);

        // PANEL IZQUIERDO: Formulario + Botones
        JPanel panelIzquierdoMesa = new JPanel(new BorderLayout());
        panelIzquierdoMesa.add(formMesa, BorderLayout.CENTER);
        panelIzquierdoMesa.add(botonesMesa, BorderLayout.SOUTH);

        // === PANEL DERECHO: Vista gráfica de mesas ===
        mesasPanel = new JPanel(); // definido como atributo
        mesasPanel.setLayout(new GridLayout(0, 4, 10, 10));
        JScrollPane scrollMesas = new JScrollPane(mesasPanel);
        scrollMesas.setBorder(BorderFactory.createTitledBorder("Vista de Mesas"));

        // SPLITPANE
        JSplitPane splitMesa = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoMesa, scrollMesas);
        splitMesa.setResizeWeight(0.3);

        // INSERTAR AL PANEL PRINCIPAL
        jPanel3.removeAll();
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(splitMesa, BorderLayout.CENTER);

        // === EVENTOS ===
        btnAgregarMesa.addActionListener(e -> agregarMesa());
        btnEditarMesa.addActionListener(e -> editarMesa());
        btnEliminarMesa.addActionListener(e -> eliminarMesa());
        btnLimpiarMesa.addActionListener(e -> limpiarCamposMesa());

        cargarMesasGraficamente();
    }
    /*private void inicializarPanelMesasEventos() {
        // === FORMULARIO DE MESAS ===
        JLabel lblCapacidad = new JLabel("Capacidad:");
        JLabel lblnroMesa = new JLabel("Nro de Mesa:");

        txtCapacidad = new JTextField(10);
        txtNroMesa = new JTextField(10);
        chkDisponible = new JCheckBox("Disponible");

        JButton btnAgregarMesa = new JButton("Agregar");
        JButton btnEditarMesa = new JButton("Editar");
        JButton btnEliminarMesa = new JButton("Eliminar");
        JButton btnLimpiarMesa = new JButton("Limpiar");

        btnAgregarMesa.setPreferredSize(new Dimension(100, 30));
        btnEditarMesa.setPreferredSize(new Dimension(100, 30));
        btnEliminarMesa.setPreferredSize(new Dimension(100, 30));
        btnLimpiarMesa.setPreferredSize(new Dimension(100, 30));

        JPanel formMesa = new JPanel();
        formMesa.setLayout(new BoxLayout(formMesa, BoxLayout.Y_AXIS));
        formMesa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int alturaFija = 30;

        JPanel panelCapMesa = new JPanel(new BorderLayout());
        panelCapMesa.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelCapMesa.setPreferredSize(new Dimension(0, alturaFija));
        panelCapMesa.add(txtCapacidad, BorderLayout.CENTER);

        JPanel panelNroMesa = new JPanel(new BorderLayout());
        panelNroMesa.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaFija));
        panelNroMesa.setPreferredSize(new Dimension(0, alturaFija));
        panelNroMesa.add(txtNroMesa, BorderLayout.CENTER);

        formMesa.add(lblCapacidad);
        formMesa.add(panelCapMesa);
        formMesa.add(lblnroMesa);
        formMesa.add(panelNroMesa);
        formMesa.add(chkDisponible);

        JPanel botonesMesa = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesMesa.add(btnAgregarMesa);
        botonesMesa.add(btnEditarMesa);
        botonesMesa.add(btnEliminarMesa);
        botonesMesa.add(btnLimpiarMesa);

        tablaMesas = new JTable(new DefaultTableModel(new Object[]{"ID", "Capacidad", "Disponible"}, 0));
        JScrollPane scrollTablaMesas = new JScrollPane(tablaMesas);
        scrollTablaMesas.setBorder(BorderFactory.createTitledBorder("Lista de Mesas"));

        JPanel panelIzquierdoMesa = new JPanel(new BorderLayout());
        panelIzquierdoMesa.add(formMesa, BorderLayout.CENTER);
        panelIzquierdoMesa.add(botonesMesa, BorderLayout.SOUTH);

        JSplitPane splitMesa = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoMesa, scrollTablaMesas);
        splitMesa.setResizeWeight(0.4);

        jPanel3.removeAll();
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(splitMesa, BorderLayout.CENTER);

        // === EVENTOS ===
        btnAgregarMesa.addActionListener(e -> agregarMesa());
        btnEditarMesa.addActionListener(e -> editarMesa());
        btnEliminarMesa.addActionListener(e -> eliminarMesa());
        btnLimpiarMesa.addActionListener(e -> limpiarCamposMesa());

        tablaMesas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarMesaSeleccionada();
            }
        });
    }
    */
    private void inicializarPanelUsuariosEventos() {
        // === FORMULARIO DE USUARIOS ===
        JLabel lblNombre = new JLabel("Nombre completo:");
        txtNombre = new JTextField(25);

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(25);

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(15);

        JLabel lblDNI = new JLabel("DNI:");
        txtDNI = new JTextField(15);

        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField(25);

        JLabel lblTipoUsuario = new JLabel("Tipo de Usuario:");
        cboTipoUsuario = new JComboBox<>();

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField(20);

        // Llenar combo de tipo usuario desde BD
        List<TipoUsuario> tipos = UserRepository.obtenerTiposUsuario();
        for (TipoUsuario tipo : tipos) {
            cboTipoUsuario.addItem(tipo);
        }

        // Botones
        JButton btnAgregarUsuario = new JButton("Agregar");
        JButton btnEditarUsuario = new JButton("Editar");
        JButton btnEliminarUsuario = new JButton("Eliminar");
        JButton btnLimpiarUsuario = new JButton("Limpiar");

        btnAgregarUsuario.setPreferredSize(new Dimension(100, 30));
        btnEditarUsuario.setPreferredSize(new Dimension(100, 30));
        btnEliminarUsuario.setPreferredSize(new Dimension(100, 30));
        btnLimpiarUsuario.setPreferredSize(new Dimension(100, 30));

        // Panel formulario
        JPanel formUsuario = new JPanel();
        formUsuario.setLayout(new BoxLayout(formUsuario, BoxLayout.Y_AXIS));
        formUsuario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formUsuario.add(lblNombre);
        formUsuario.add(txtNombre);
        formUsuario.add(lblEmail);
        formUsuario.add(txtEmail);
        formUsuario.add(lblTelefono);
        formUsuario.add(txtTelefono);
        formUsuario.add(lblDNI);
        formUsuario.add(txtDNI);
        formUsuario.add(lblDireccion);
        formUsuario.add(txtDireccion);
        formUsuario.add(lblTipoUsuario);
        formUsuario.add(cboTipoUsuario);
        formUsuario.add(lblContrasena);
        formUsuario.add(txtContrasena);

        // Panel botones
        JPanel botonesUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesUsuario.add(btnAgregarUsuario);
        botonesUsuario.add(btnEditarUsuario);
        botonesUsuario.add(btnEliminarUsuario);
        botonesUsuario.add(btnLimpiarUsuario);

        // Tabla
        tablaUsuarios = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Email", "Teléfono", "DNI", "Dirección", "Tipo","Clave"}, 0));
        JScrollPane scrollTablaUsuarios = new JScrollPane(tablaUsuarios);

        scrollTablaUsuarios.setBorder(BorderFactory.createTitledBorder("Lista de Usuarios"));
        
     

        // Panel dividido
        JPanel panelIzquierdoUsuario = new JPanel(new BorderLayout());
        panelIzquierdoUsuario.add(formUsuario, BorderLayout.CENTER);
        panelIzquierdoUsuario.add(botonesUsuario, BorderLayout.SOUTH);

        JSplitPane splitUsuario = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoUsuario, scrollTablaUsuarios);
        splitUsuario.setResizeWeight(0.4);

        jPanel4.removeAll();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(splitUsuario, BorderLayout.CENTER);

        // === EVENTOS ===
        btnAgregarUsuario.addActionListener(e -> agregarUsuario());
        btnEditarUsuario.addActionListener(e -> editarUsuario());
        btnEliminarUsuario.addActionListener(e -> eliminarUsuario());
        btnLimpiarUsuario.addActionListener(e -> limpiarCamposUsuario());

        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> cargarUsuarioSeleccionado());
        

        // Cargar tabla al iniciar
        cargarUsuarios();
    }        
    private void inicializarPanelPlatos() {
        jPanel5.setLayout(null); // Posicionamiento absoluto

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        jPanel5.add(lblNombre);

        txtNombrePlato = new JTextField();
        txtNombrePlato.setBounds(120, 20, 200, 25);
        jPanel5.add(txtNombrePlato);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 60, 100, 25);
        jPanel5.add(lblDescripcion);

        txtDescripcionPlato = new JTextArea();
        txtDescripcionPlato.setLineWrap(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcionPlato);
        scrollDesc.setBounds(120, 60, 200, 60);
        jPanel5.add(scrollDesc);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 130, 100, 25);
        jPanel5.add(lblPrecio);

        txtPrecioPlato = new JTextField();
        txtPrecioPlato.setBounds(120, 130, 200, 25);
        jPanel5.add(txtPrecioPlato);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 170, 100, 25);
        jPanel5.add(lblTipo);

        cmbTipoPlato = new JComboBox<>(new String[]{"Entrada", "Fondo", "Bebida", "Postre"});
        cmbTipoPlato.setBounds(120, 170, 200, 25);
        jPanel5.add(cmbTipoPlato);

        JLabel lblImagen = new JLabel("Imagen:");
        lblImagen.setBounds(20, 210, 100, 25);
        jPanel5.add(lblImagen);

        txtImagenPlato = new JTextField();
        txtImagenPlato.setBounds(120, 210, 200, 25);
        jPanel5.add(txtImagenPlato);

        JButton btnSeleccionarImagen = new JButton("...");
        btnSeleccionarImagen.setBounds(330, 210, 30, 25);
        jPanel5.add(btnSeleccionarImagen);

        lblVistaPreviaImagenPlato = new JLabel();
        lblVistaPreviaImagenPlato.setBounds(370, 60, 180, 180);
        lblVistaPreviaImagenPlato.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jPanel5.add(lblVistaPreviaImagenPlato);

        // Tabla
        tablaPlatos = new JTable();
        modeloTablaPlatos = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción", "Precio", "Tipo", "Imagen"}, 0);
        tablaPlatos.setModel(modeloTablaPlatos);
        JScrollPane scrollTabla = new JScrollPane(tablaPlatos);
        scrollTabla.setBounds(20, 260, 600, 200);
        jPanel5.add(scrollTabla);

        // Botones
        btnAgregarPlato = new JButton("Agregar");
        btnAgregarPlato.setBounds(650, 60, 100, 25);
        jPanel5.add(btnAgregarPlato);

        btnEditarPlato = new JButton("Editar");
        btnEditarPlato.setBounds(650, 95, 100, 25);
        jPanel5.add(btnEditarPlato);

        btnEliminarPlato = new JButton("Eliminar");
        btnEliminarPlato.setBounds(650, 130, 100, 25);
        jPanel5.add(btnEliminarPlato);

        btnLimpiarPlato = new JButton("Limpiar");
        btnLimpiarPlato.setBounds(650, 165, 100, 25);
        jPanel5.add(btnLimpiarPlato);

        // Acción del botón de imagen
        btnSeleccionarImagen.addActionListener(e -> seleccionarImagenPlato(txtImagenPlato, lblVistaPreviaImagenPlato));
        
    }  
    private void agregarListenerPlatos(){
        btnAgregarPlato.addActionListener(e -> {
        String nombre = txtNombrePlato.getText();
        String descripcion = txtDescripcionPlato.getText();
        String tipo = (String) cmbTipoPlato.getSelectedItem();
        String imagen = txtImagenPlato.getText();
        double precio;

        try {
            precio = Double.parseDouble(txtPrecioPlato.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido");
            return;
        }

        Plato nuevoPlato = new Plato(0, nombre, tipo, descripcion , precio,imagen);
        DataRepository.agregarPlato(nuevoPlato);
        cargarPlatos();
        limpiarCamposPlato();
    });
        
        

    btnEditarPlato.addActionListener(e -> {
        int fila = tablaPlatos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un plato para editar");
            return;
        }

        int id = (int) modeloTablaPlatos.getValueAt(fila, 0);
        String nombre = txtNombrePlato.getText();
        String descripcion = txtDescripcionPlato.getText();
        String tipo = (String) cmbTipoPlato.getSelectedItem();
        String imagen = txtImagenPlato.getText();
        double precio;

        try {
            precio = Double.parseDouble(txtPrecioPlato.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido");
            return;
        }

        Plato editado = new Plato(id, nombre,tipo, descripcion, precio, imagen );
        DataRepository.editarPlato(id,editado);
        cargarPlatos();
        limpiarCamposPlato();
    });

    btnEliminarPlato.addActionListener(e -> {
        int fila = tablaPlatos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un plato para eliminar");
            return;
        }

        int id = (int) modeloTablaPlatos.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este plato?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DataRepository.eliminarPlato(id);
            cargarPlatos();
            limpiarCamposPlato();
        }
    });

    btnLimpiarPlato.addActionListener(e -> limpiarCamposPlato());

    tablaPlatos.getSelectionModel().addListSelectionListener(e -> {
        int fila = tablaPlatos.getSelectedRow();
        if (fila != -1) {
            txtNombrePlato.setText((String) modeloTablaPlatos.getValueAt(fila, 1));
            txtDescripcionPlato.setText((String) modeloTablaPlatos.getValueAt(fila, 2));
            txtPrecioPlato.setText(modeloTablaPlatos.getValueAt(fila, 3).toString());
            cmbTipoPlato.setSelectedItem((String) modeloTablaPlatos.getValueAt(fila, 4));
            txtImagenPlato.setText((String) modeloTablaPlatos.getValueAt(fila, 5));

            // Vista previa imagen
            String rutaImagen = txtImagenPlato.getText();
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                ImageIcon icono = new ImageIcon(rutaImagen);
                Image imagenEscalada = icono.getImage().getScaledInstance(
                    lblVistaPreviaImagenPlato.getWidth(), lblVistaPreviaImagenPlato.getHeight(), Image.SCALE_SMOOTH);
                lblVistaPreviaImagenPlato.setIcon(new ImageIcon(imagenEscalada));
            } else {
                lblVistaPreviaImagenPlato.setIcon(null);
            }
        }
    });

    }
    private void seleccionarImagenPlato(JTextField campoRuta, JLabel labelVistaPrevia) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            campoRuta.setText(archivoSeleccionado.getAbsolutePath());

            // Mostrar vista previa escalada
            ImageIcon icono = new ImageIcon(archivoSeleccionado.getAbsolutePath());
            Image imagenEscalada = icono.getImage().getScaledInstance(
                    labelVistaPrevia.getWidth(), labelVistaPrevia.getHeight(), Image.SCALE_SMOOTH);
            labelVistaPrevia.setIcon(new ImageIcon(imagenEscalada));
        }
    }

   
    private void agregarNovedad() {
        String titulo = txtTitNov.getText();
        String descripcion = txtDescNovedad.getText();
        String imagen = txtImgNovedad.getText();
        Date inicio = dateInicioNovedad.getDate();
        Date fin = dateFinNovedad.getDate();

        Novedad nueva = new Novedad(titulo, descripcion, imagen, inicio, fin);
        boolean ok = DataRepository.agregarNovedad(nueva);

        if (ok) {
            cargarNovedades(); // Recarga desde base de datos
            
            JOptionPane.showMessageDialog(this, "Novedad agregada correctamente.");
            limpiarCamposNovedad();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la novedad.");
        }
    }
    private void editarNovedad() {
        int fila = tablaNovedades.getSelectedRow();
        if (fila != -1) {
            String titulo = txtTitNov.getText();
            String descripcion = txtDescNovedad.getText();
            String imagen = txtImgNovedad.getText();
            Date inicio = dateInicioNovedad.getDate();
            Date fin = dateFinNovedad.getDate();

            // Obtener ID desde tabla si tienes columna oculta "ID"
            int id = obtenerIdDesdeTablaNovedad(fila);  // Asegúrate de tener esta columna
            Novedad novedad = new Novedad(titulo, descripcion, imagen, inicio, fin);

            boolean ok = DataRepository.editarNovedad(id, novedad);

            if (ok) {
                cargarNovedades();
                
                JOptionPane.showMessageDialog(this, "Novedad editada con éxito.");
                limpiarCamposNovedad();
            } else {
                JOptionPane.showMessageDialog(this, "Error al editar la novedad.");
            }
        }
    }
    private int obtenerIdDesdeTablaNovedad(int fila) {
        return Integer.parseInt(tablaNovedades.getValueAt(fila, 0).toString()); // Columna 0 es ID
    }
    private void eliminarNovedad() {
        int fila = tablaNovedades.getSelectedRow();
        if (fila != -1) {
            int id = obtenerIdDesdeTablaNovedad(fila); // Debes tener columna ID
            boolean exito = DataRepository.eliminarNovedad(id);

            if (exito) {
                cargarNovedades(); // Refrescar tabla desde base de datos
                
                JOptionPane.showMessageDialog(this, "Novedad eliminada.");
                limpiarCamposNovedad();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar novedad.");
            }
        }
    }
    private void cargarNovedadSeleccionada() {
        int fila = tablaNovedades.getSelectedRow();
        if (fila != -1) {
            txtTitNov.setText(tablaNovedades.getValueAt(fila, 1).toString()); // Título
            txtDescNovedad.setText(tablaNovedades.getValueAt(fila, 2).toString());  // Descripción
            txtImgNovedad.setText(tablaNovedades.getValueAt(fila, 3).toString());   // Imagen

            try {
                String fechaInicioStr = tablaNovedades.getValueAt(fila, 4).toString();
                String fechaFinStr = tablaNovedades.getValueAt(fila, 5).toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);

                dateInicioNovedad.setDate(fechaInicio);
                dateFinNovedad.setDate(fechaFin);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void agregarPromocion() {
        String titulo = txtTituloPromo.getText();
        String descripcion = txtDescPromo.getText();
        String img = txtImgPromo.getText();
        double descuento = Double.parseDouble(txtDescuento.getText());
        Date inicio = dateInicioPromo.getDate();
        Date fin = dateFinPromo.getDate();

        Promocion nueva = new Promocion(0, titulo, descripcion, inicio, fin, descuento, img);
        boolean ok = DataRepository.agregarPromocion(nueva);

        if (ok) {
            cargarPromociones(); // Actualiza la tabla desde BD
            
            JOptionPane.showMessageDialog(this, "Promoción agregada correctamente.");
            limpiarCamposPromocion();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar promoción.");
        }
    }
    private void editarPromocion() {
        int fila = tablaPromociones.getSelectedRow();
        if (fila != -1) {
            int id = Integer.parseInt(tablaPromociones.getValueAt(fila, 0).toString());

            String titulo = txtTituloPromo.getText();
            String descripcion = txtDescPromo.getText();
            String img = txtImgPromo.getText();
            double descuento = Double.parseDouble(txtDescuento.getText());
            Date inicio = dateInicioPromo.getDate();
            Date fin = dateFinPromo.getDate();

            Promocion actualizada = new Promocion(id, titulo, descripcion, inicio, fin, descuento, img);
            boolean ok = DataRepository.editarPromocion(id, actualizada);

            if (ok) {
                cargarPromociones();
                
                JOptionPane.showMessageDialog(this, "Promoción editada correctamente.");
                limpiarCamposPromocion();
            } else {
                JOptionPane.showMessageDialog(this, "Error al editar promoción.");
            }
        }
    }
    private void eliminarPromocion() {
        int fila = tablaPromociones.getSelectedRow();
        if (fila != -1) {
            int id = Integer.parseInt(tablaPromociones.getValueAt(fila, 0).toString());
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Eliminar esta promoción?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                boolean ok = DataRepository.eliminarPromocion(id);
                if (ok) {
                    cargarPromociones();
                    
                    JOptionPane.showMessageDialog(this, "Promoción eliminada.");
                    limpiarCamposPromocion();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar promoción.");
                }
            }
        }
    }    
    private void cargarPromocionSeleccionada() {
        int fila = tablaPromociones.getSelectedRow();
        if (fila != -1) {
            txtTituloPromo.setText(tablaPromociones.getValueAt(fila, 1).toString()); // Título
            txtDescPromo.setText(tablaPromociones.getValueAt(fila, 2).toString());  // Descripción
            txtDescuento.setText(tablaPromociones.getValueAt(fila, 3).toString());  // Descuento
            txtImgPromo.setText(tablaPromociones.getValueAt(fila, 6).toString());   // Imagen

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(tablaPromociones.getValueAt(fila, 4).toString());
                Date fechaFin = sdf.parse(tablaPromociones.getValueAt(fila, 5).toString());

                dateInicioPromo.setDate(fechaInicio);
                dateFinPromo.setDate(fechaFin);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void cargarMesasGraficamente() {
        mesasPanel.removeAll();
        List<Mesa> mesas = DataRepository.obtenerMesas().getData();
        if (mesas == null || mesas.isEmpty()) {
            mesasPanel.add(new JLabel("No hay mesas registradas"));
            mesasPanel.revalidate();
            mesasPanel.repaint();
            return;
        }

        int columnas = 4;
        int filas = (int) Math.ceil(mesas.size() / (double) columnas);
        mesasPanel.setLayout(new GridLayout(filas, columnas, 10, 10));

        for (Mesa mesa : mesas) {
            boolean esSeleccionada = mesaSeleccionada != null && mesa.getIdMesa() == mesaSeleccionada.getIdMesa();

            MesaVista vista = new MesaVista(mesa, m -> {
                mesaSeleccionada = m;
                txtNroMesa.setText(String.valueOf(m.getNumeroMesa()));
                txtCapacidad.setText(String.valueOf(m.getCapacidad()));
                chkDisponible.setSelected(m.isDisponible());
                cargarMesasGraficamente(); // refresca resaltado
            }, esSeleccionada);

            mesasPanel.add(vista);
        }
        System.out.println("Mesas cargadas: " + mesas.size());
        mesasPanel.setBackground(Color.YELLOW); // para ver si el panel aparece


        mesasPanel.revalidate();
        mesasPanel.repaint();
    }

    
    private void limpiarCamposPlato() {
        txtNombrePlato.setText("");
        txtDescripcionPlato.setText("");
        txtPrecioPlato.setText("");
        cmbTipoPlato.setSelectedIndex(0);
        txtImagenPlato.setText("");
        lblVistaPreviaImagenPlato.setIcon(null);
        tablaPlatos.clearSelection();
    }
    private void limpiarCamposNovedad() {
        txtTitNov.setText("");
        txtDescNovedad.setText("");
        txtImgNovedad.setText("");
        dateInicioNovedad.setDate(null);
        dateFinNovedad.setDate(null);
    }
    private void limpiarCamposPromocion() {
        txtTituloPromo.setText("");
        txtDescPromo.setText("");
        txtDescuento.setText("");
        txtImgPromo.setText("");
        dateInicioPromo.setDate(null);
        dateFinPromo.setDate(null);
    } 
    private void limpiarCamposUsuario() {
       txtNombre.setText("");
       txtEmail.setText("");
       txtTelefono.setText("");
       txtDNI.setText("");
       txtDireccion.setText("");
       cboTipoUsuario.setSelectedIndex(0);
       txtContrasena.setText("");
   }
    
    private void cargarNovedades() {
    Resultado<List<Novedad>> resultado = DataRepository.obtenerNovedades();

    if (!resultado.isOk()) {
        System.out.println("Error al cargar novedades: " + resultado.getMessage());
        return;
    }

    List<Novedad> lista = resultado.getData();

    System.out.println("Tamaño de la lista de novedades: " + lista.size());

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID"); // ← oculto
    model.addColumn("Título");
    model.addColumn("Descripción");
    model.addColumn("Imagen");
    model.addColumn("Inicio");
    model.addColumn("Fin");

    for (Novedad n : lista) {
        model.addRow(new Object[]{
            n.getId(), // ← ID
            n.getTitulo(),
            n.getDescripcion(),
            n.getImageUrl(),
            n.getFechaInicio(),
            n.getFechaFin()
        });
    }

    tablaNovedades.setModel(model);
    tablaNovedades.getColumnModel().getColumn(0).setMinWidth(0);   // Oculta visualmente el ID
    tablaNovedades.getColumnModel().getColumn(0).setMaxWidth(0);

}
    

public class MesaVista extends JPanel {
    public MesaVista(Mesa mesa, Consumer<Mesa> onSeleccionar, boolean seleccionada) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(seleccionada ? Color.BLUE : Color.GRAY, 2));
        setBackground(seleccionada ? new Color(220, 240, 255) : Color.WHITE);
        setPreferredSize(new Dimension(100, 80));

        JLabel lblId = new JLabel("Mesa " + mesa.getIdMesa(), SwingConstants.CENTER);
        JLabel lblCapacidad = new JLabel("Cap: " + mesa.getCapacidad(), SwingConstants.CENTER);
        JLabel lblEstado = new JLabel(mesa.isDisponible() ? "Disponible" : "Ocupada", SwingConstants.CENTER);

        lblEstado.setForeground(mesa.isDisponible() ? Color.GREEN.darker() : Color.RED);

        add(lblId, BorderLayout.NORTH);
        add(lblCapacidad, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);

        // Selección al hacer clic
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onSeleccionar.accept(mesa);
            }
        });
    }
}




    private void cargarPlatos() {
        modeloTablaPlatos.setRowCount(0); // Limpiar la tabla
        List<Plato> lista = DataRepository.obtenerPlatos();

        for (Plato plato : lista) {
            modeloTablaPlatos.addRow(new Object[]{
                plato.getIdMenu(),
                plato.getNombre(),
                plato.getDescripcion(),
                plato.getPrecio(),
                plato.getTipo(),
                plato.getUrlImagen()
            });
        }
    }   
    private void cargarPromociones() {
        String[] columnas = {"ID", "Título", "Descripción", "Descuento", "Inicio", "Fin", "Imagen"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        Resultado<List<Promocion>> resultado = DataRepository.obtenerPromociones();
        if (resultado.isOk()) {
            for (Promocion promo : resultado.getData()) {
                Object[] fila = {
                    promo.getIdPromocion(), // ID para gestión
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

        // Ocultar la columna ID
        tablaPromociones.getColumnModel().getColumn(0).setMinWidth(0);
        tablaPromociones.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPromociones.getColumnModel().getColumn(0).setWidth(0);
    }
    private void cargarMesas() {
    Resultado<List<Mesa>> resultado = DataRepository.obtenerMesas();
    if (!resultado.isOk()) {
        JOptionPane.showMessageDialog(this, "Error al cargar mesas: " + resultado.getMessage());
        return;
    }

    DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "N° Mesa", "Capacidad", "Disponible"}, 0);
    for (Mesa m : resultado.getData()) {
        model.addRow(new Object[]{
            m.getIdMesa(),
            m.getNumeroMesa(),
            m.getCapacidad(),
            m.isDisponible() ? "Sí" : "No"
        });
    }
    tablaMesas.setModel(model);
   // tablaMesas.getColumnModel().getColumn(0).setMinWidth(0); // Ocultar ID
   // tablaMesas.getColumnModel().getColumn(0).setMaxWidth(0);
}
    private void cargarUsuarios() {
       Resultado<List<Usuario>> resultado = UserRepository.obtenerUsuarios();
       if (!resultado.isOk()) {
           JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + resultado.getMessage());
           return;
       }

       DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Email", "Teléfono", "DNI", "Dirección", "Tipo","Clave"}, 0);
       for (Usuario u : resultado.getData()) {
           model.addRow(new Object[]{
               u.getIdUsuario(),
               u.getNombreCompleto(),
               u.getEmail(),
               u.getTelefono(),
               u.getDni(),
               u.getDireccion(),
               u.getTipoUsuario().getDescripcion(),
               u.getContrasena()
           });
       }
       tablaUsuarios.setModel(model);
       tablaUsuarios.getColumnModel().getColumn(0).setMinWidth(0);  // Ocultar ID
       tablaUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaUsuarios.getColumnModel().getColumn(7).setMinWidth(0);
        tablaUsuarios.getColumnModel().getColumn(7).setMaxWidth(0);
        tablaUsuarios.getColumnModel().getColumn(7).setPreferredWidth(0);
   }
/*
    private void agregarMesa() {
    try {
        int numeroMesa = Integer.parseInt(txtNroMesa.getText());
        int capacidad = Integer.parseInt(txtCapacidad.getText());
        boolean disponible = chkDisponible.isSelected();

        Mesa nueva = new Mesa(0, numeroMesa, capacidad, disponible);
        if (DataRepository.agregarMesa(nueva)) {
            cargarMesas();
            limpiarCamposMesa();
            JOptionPane.showMessageDialog(this, "Mesa agregada.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la mesa.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese valores válidos.");
    }
}*/
    
    private void agregarMesa() {
    try {
        int numeroMesa = Integer.parseInt(txtNroMesa.getText());
        int capacidad = Integer.parseInt(txtCapacidad.getText());
        boolean disponible = chkDisponible.isSelected();

        Mesa nueva = new Mesa(0, numeroMesa, capacidad, disponible);
        if (DataRepository.agregarMesa(nueva)) {
            cargarMesasGraficamente();
            limpiarCamposMesa();
            JOptionPane.showMessageDialog(this, "Mesa agregada.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la mesa.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese valores válidos.");
    }
}
/*
    private void editarMesa() {
        int fila = tablaMesas.getSelectedRow();
        if (fila != -1) {
            try {
                int id = Integer.parseInt(tablaMesas.getValueAt(fila, 0).toString());
                int numeroMesa = Integer.parseInt(txtNroMesa.getText());
                int capacidad = Integer.parseInt(txtCapacidad.getText());
                boolean disponible = chkDisponible.isSelected();

                Mesa editada = new Mesa(id, numeroMesa, capacidad, disponible);
                if (DataRepository.editarMesa(id, editada)) {
                    cargarMesas();
                    limpiarCamposMesa();
                    JOptionPane.showMessageDialog(this, "Mesa actualizada.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al editar.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese valores válidos.");
            }
        }
    }
    */
    private void editarMesa() {
    if (mesaSeleccionada != null) {
        try {
            int id = mesaSeleccionada.getIdMesa();
            int numeroMesa = Integer.parseInt(txtNroMesa.getText());
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            boolean disponible = chkDisponible.isSelected();

            Mesa editada = new Mesa(id, numeroMesa, capacidad, disponible);
            if (DataRepository.editarMesa(id, editada)) {
                cargarMesasGraficamente();
                limpiarCamposMesa();
                JOptionPane.showMessageDialog(this, "Mesa actualizada.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al editar.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione una mesa para editar.");
    }
}
/*
    private void eliminarMesa() {
         int fila = tablaMesas.getSelectedRow();
         if (fila != -1) {
             int id = Integer.parseInt(tablaMesas.getValueAt(fila, 0).toString());
             int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta mesa?", "Confirmar", JOptionPane.YES_NO_OPTION);

             if (confirm == JOptionPane.YES_OPTION) {
                 boolean ok = DataRepository.eliminarMesa(id);
                 if (ok) {
                     cargarMesas();
                     limpiarCamposMesa();
                     JOptionPane.showMessageDialog(this, "Mesa eliminada.");
                 } else {
                     JOptionPane.showMessageDialog(this, "Error al eliminar mesa.");
                 }
             }
         }
     }*/
    
    private void eliminarMesa() {
    if (mesaSeleccionada != null) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta mesa?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = DataRepository.eliminarMesa(mesaSeleccionada.getIdMesa());
            if (ok) {
                cargarMesasGraficamente();
                limpiarCamposMesa();
                JOptionPane.showMessageDialog(this, "Mesa eliminada.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar mesa.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione una mesa para eliminar.");
    }
}

    private void cargarMesaSeleccionada() {
        int fila = tablaMesas.getSelectedRow();
        if (fila != -1) {
            txtNroMesa.setText(tablaMesas.getValueAt(fila, 1).toString());
            txtCapacidad.setText(tablaMesas.getValueAt(fila, 2).toString());
            chkDisponible.setSelected(tablaMesas.getValueAt(fila, 3).equals("Sí"));
        }
    }


     private void limpiarCamposMesa() {
    txtCapacidad.setText("");
    txtNroMesa.setText("");
    chkDisponible.setSelected(false);
    mesaSeleccionada = null;
    cargarMesasGraficamente(); // para deseleccionar visualmente
}

     
       

    private void agregarUsuario() {
        String contrasena = new String(txtContrasena.getPassword());
        if (contrasena.isBlank()) {
            JOptionPane.showMessageDialog(this, "Ingrese una contraseña.");
            return;
        }

        Usuario u = obtenerUsuarioDesdeFormulario(0, contrasena);
        if (u == null) return;

        boolean ok = UserRepository.agregarUsuario(u, contrasena);
        if (ok) {
            cargarUsuarios();
            limpiarCamposUsuario();
            JOptionPane.showMessageDialog(this, "Usuario agregado.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar usuario.");
        }
    }
    private void editarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila != -1) {
            int id = Integer.parseInt(tablaUsuarios.getValueAt(fila, 0).toString());
            String contrasena = new String(txtContrasena.getPassword());

            Usuario u = obtenerUsuarioDesdeFormulario(id, contrasena);
            if (u == null) return;

            boolean ok;
            if (contrasena.isBlank()) {
                ok = UserRepository.editarUsuario(id, u); // sin cambiar contraseña
            } else {
                ok = UserRepository.editarUsuarioConContrasena(id, u, contrasena); // cambia contraseña
            }

            if (ok) {
                cargarUsuarios();
                limpiarCamposUsuario();
                JOptionPane.showMessageDialog(this, "Usuario actualizado.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al editar usuario.");
            }
        }
    }
    private void eliminarUsuario() {
       int fila = tablaUsuarios.getSelectedRow();
       if (fila != -1) {
           int id = Integer.parseInt(tablaUsuarios.getValueAt(fila, 0).toString());
           int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
           if (confirm == JOptionPane.YES_OPTION) {
               boolean ok = UserRepository.eliminarUsuario(id);
               if (ok) {
                   cargarUsuarios();
                   limpiarCamposUsuario();
                   JOptionPane.showMessageDialog(this, "Usuario eliminado.");
               } else {
                   JOptionPane.showMessageDialog(this, "Error al eliminar.");
               }
           }
       }
   }
    private void cargarUsuarioSeleccionado() {
       int fila = tablaUsuarios.getSelectedRow();
       if (fila != -1) {
           txtNombre.setText(tablaUsuarios.getValueAt(fila, 1).toString());
           txtEmail.setText(tablaUsuarios.getValueAt(fila, 2).toString());
           txtTelefono.setText(tablaUsuarios.getValueAt(fila, 3).toString());
           txtDNI.setText(tablaUsuarios.getValueAt(fila, 4).toString());
           txtDireccion.setText(tablaUsuarios.getValueAt(fila, 5).toString());
           cboTipoUsuario.setSelectedItem(tablaUsuarios.getValueAt(fila, 6).toString());
           txtContrasena.setText(tablaUsuarios.getValueAt(fila, 7).toString());
       }
   }

 

  /* private Usuario obtenerUsuarioDesdeFormulario(int id) {
       try {
           String nombre = txtNombre.getText();
           String email = txtEmail.getText();
           String telefono = txtTelefono.getText();
           String dni = txtDNI.getText();
           String direccion = txtDireccion.getText();
           String tipoStr = cboTipoUsuario.getSelectedItem().toString();
           int idTipo = tipoStr.equals("Administrador") ? 1 : 2;
           TipoUsuario tipo = new TipoUsuario(idTipo, tipoStr);

           return new Usuario(id, nombre, email, telefono, dni, direccion, tipo);
       } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Datos inválidos.");
           return null;
       }
   }
*/
   private Usuario obtenerUsuarioDesdeFormulario(int id, String contrasena) {
    try {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();
        String dni = txtDNI.getText();
        String direccion = txtDireccion.getText();
        String tipoStr = cboTipoUsuario.getSelectedItem().toString();
        int idTipo = tipoStr.equals("Administrador") ? 1 : 2;
        TipoUsuario tipo = new TipoUsuario(idTipo, tipoStr);

        Usuario u = new Usuario(id, nombre, email, telefono, dni, direccion, tipo);
        u.setEmail(email);
        u.setDireccion(direccion);
        // No se guarda la contraseña directamente en Usuario pero puedes pasarla por separado
        return u;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Datos inválidos.");
        return null;
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

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

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

        jTabbedPane1.addTab("MESAS", jPanel3);

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

        jTabbedPane1.addTab("USUARIOS", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("PLATO", jPanel5);

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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
