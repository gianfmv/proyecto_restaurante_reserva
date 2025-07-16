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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Mesa;


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
    private javax.swing.JTable tablaMesas;
    
    // Campos NOVEDAD
    private JTextField txtTituloNovedad;
    private JTextArea txtDescNovedad;
    private JTextField txtImgNovedad;


    // Campos PROMOCION
    private JTextField txtTituloPromo;
    private JTextArea txtDescPromo;
    private JTextField txtImgPromo;
    private JTextField txtDescuento;

    private JTextField txtCapacidad;
    private JCheckBox chkDisponible;
    
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
    JButton btnLimpiarNovedad = new JButton("Limpiar");

    btnAgregarNovedad.setPreferredSize(new Dimension(100, 30));
    btnEditarNovedad.setPreferredSize(new Dimension(100, 30));
    btnEliminarNovedad.setPreferredSize(new Dimension(100, 30));
    btnLimpiarNovedad.setPreferredSize(new Dimension(100, 30));

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
    botonesNovedad.add(btnLimpiarNovedad); 

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
    JButton btnLimpiarPromo = new JButton("Limpiar");

    btnAgregarPromo.setPreferredSize(new Dimension(100, 30));
    btnEditarPromo.setPreferredSize(new Dimension(100, 30));
    btnEliminarPromo.setPreferredSize(new Dimension(100, 30));
    btnLimpiarPromo.setPreferredSize(new Dimension(100, 30));

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
    botonesPromo.add(btnLimpiarPromo); 

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
    btnLimpiarNovedad.addActionListener(e -> limpiarCamposNovedad());

    btnAgregarPromo.addActionListener(e -> agregarPromocion());
    btnEditarPromo.addActionListener(e -> editarPromocion());
    btnEliminarPromo.addActionListener(e -> eliminarPromocion());
    btnLimpiarPromo.addActionListener(e -> limpiarCamposPromocion());

    // Cargar datos
    cargarNovedades();
    cargarPromociones();
    
        // === FORMULARIO DE MESAS ===
    JLabel lblCapacidad = new JLabel("Capacidad:");
    
    txtCapacidad = new JTextField(10);
    chkDisponible = new JCheckBox("Disponible");
    txtCapacidad.setMaximumSize(new Dimension(150, 25)); // ancho y alto máximos

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

    formMesa.add(lblCapacidad);
    formMesa.add(txtCapacidad);
    formMesa.add(chkDisponible);

    JPanel botonesMesa = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    botonesMesa.add(btnAgregarMesa);
    botonesMesa.add(btnEditarMesa);
    botonesMesa.add(btnEliminarMesa);
    botonesMesa.add(btnLimpiarMesa);

    tablaMesas = new JTable(new DefaultTableModel(new Object[]{"ID", "Capacidad", "Disponible"}, 0));
    JScrollPane scrollTablaMesas = new JScrollPane(tablaMesas);
    scrollTablaMesas.setBorder(BorderFactory.createTitledBorder("Lista de Mesas"));

   // tablaMesas.getColumnModel().getColumn(0).setMinWidth(0); // Ocultar columna ID
    //tablaMesas.getColumnModel().getColumn(0).setMaxWidth(0);

    JPanel panelIzquierdoMesa = new JPanel(new BorderLayout());
    panelIzquierdoMesa.add(formMesa, BorderLayout.CENTER);
    panelIzquierdoMesa.add(botonesMesa, BorderLayout.SOUTH);

    JSplitPane splitMesa = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdoMesa, scrollTablaMesas);
    splitMesa.setResizeWeight(0.4);

    jPanel3.removeAll();
    jPanel3.setLayout(new BorderLayout());
    jPanel3.add(splitMesa, BorderLayout.CENTER);

    // Eventos
    btnAgregarMesa.addActionListener(e -> agregarMesa());
    btnEditarMesa.addActionListener(e -> editarMesa());
    btnEliminarMesa.addActionListener(e -> eliminarMesa());
    btnLimpiarMesa.addActionListener(e -> limpiarCamposMesa());

    tablaMesas.getSelectionModel().addListSelectionListener(e -> cargarMesaSeleccionada());

    cargarMesas();
    

    }
    
    private void agregarNovedad() {
        String titulo = txtTituloNovedad.getText();
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
            String titulo = txtTituloNovedad.getText();
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
            txtTituloNovedad.setText(tablaNovedades.getValueAt(fila, 1).toString()); // Título
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

    private void limpiarCamposNovedad() {
        txtTituloNovedad.setText("");
        txtDescNovedad.setText("");
        txtImgNovedad.setText("");
        dateInicioNovedad.setDate(null);
        dateFinNovedad.setDate(null);
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

         List<Mesa> lista = resultado.getData();
         DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Capacidad", "Disponible"}, 0);

         for (Mesa m : lista) {
             model.addRow(new Object[]{
                 m.getIdMesa(),
                 m.getCapacidad(),
                 m.isDisponible() ? "Sí" : "No"
             });
         }

         tablaMesas.setModel(model);
        // tablaMesas.getColumnModel().getColumn(0).setMinWidth(0);
        // tablaMesas.getColumnModel().getColumn(0).setMaxWidth(0);
     }

     private void agregarMesa() {
         try {
             int capacidad = Integer.parseInt(txtCapacidad.getText());
             boolean disponible = chkDisponible.isSelected();

             Mesa nueva = new Mesa(0, capacidad, disponible);
             boolean ok = DataRepository.agregarMesa(nueva);

             if (ok) {
                 cargarMesas();
                 limpiarCamposMesa();
                 JOptionPane.showMessageDialog(this, "Mesa agregada correctamente.");
             } else {
                 JOptionPane.showMessageDialog(this, "Error al agregar la mesa.");
             }
         } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(this, "Capacidad debe ser un número válido.");
         }
     }

     private void editarMesa() {
         int fila = tablaMesas.getSelectedRow();
         if (fila != -1) {
             try {
                 int id = Integer.parseInt(tablaMesas.getValueAt(fila, 0).toString());
                 int capacidad = Integer.parseInt(txtCapacidad.getText());
                 boolean disponible = chkDisponible.isSelected();

                 Mesa mesaEditada = new Mesa(id, capacidad, disponible);
                 boolean ok = DataRepository.editarMesa(id, mesaEditada);

                 if (ok) {
                     cargarMesas();
                     limpiarCamposMesa();
                     JOptionPane.showMessageDialog(this, "Mesa actualizada.");
                 } else {
                     JOptionPane.showMessageDialog(this, "Error al editar la mesa.");
                 }
             } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(this, "Capacidad debe ser un número válido.");
             }
         }
     }

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
     }

     private void cargarMesaSeleccionada() {
         int fila = tablaMesas.getSelectedRow();
         if (fila != -1) {
             txtCapacidad.setText(tablaMesas.getValueAt(fila, 1).toString());
             String disponibleStr = tablaMesas.getValueAt(fila, 2).toString();
             chkDisponible.setSelected(disponibleStr.equalsIgnoreCase("Sí"));
         }
     }

     private void limpiarCamposMesa() {
         txtCapacidad.setText("");
         chkDisponible.setSelected(false);
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
