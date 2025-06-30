/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

/**
 *
 * @author salaz
 */
public class ViewUtil {
    public static void setComponentsEnabled(Container container, boolean enabled) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enabled);

            if (component instanceof Container container1) {
                setComponentsEnabled(container1, enabled);
            }
        }
    }
    
    public static void cambiarFuente(Container container, Class<?> clase, String nombre, int tamano, Color color) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (clase.isInstance(component)) {
                JComponent component1 = (JComponent) component;
                component1.setFont(FontLoader.load(nombre, Font.PLAIN, tamano ));
                component1.setForeground(color);
            }
        }
    }
    
    public static void vaciarInputs(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if(component instanceof JTextField jTextField) {
                jTextField.setText("");
            }
        }       
    }
    
    public static void agregarErrorEnInput(JTextComponent component, String error) {
        if(error == null) {
            if(component.getBorder() instanceof TitledBorder) {
                component.setBorder(null);
            }
        } else {
            LineBorder lineBorder = new LineBorder(Color.red);
            TitledBorder titledBorder = new TitledBorder(lineBorder, error
                    , TitledBorder.LEADING, TitledBorder.BELOW_BOTTOM, FontLoader.load("Poppins-Regular.ttf", 12), Color.RED);
            component.setBorder(titledBorder);
        }
    }
    
    public static String verificarMinimo(JTextComponent component, int length, String message) {
        String contenido = component.getText();
        if(contenido.isBlank() || contenido.isEmpty() || contenido.trim().length() < length) {
            agregarErrorEnInput(component, message);
            return null;
        } else {
            agregarErrorEnInput(component, null);
            return contenido;
        }
    }

}
