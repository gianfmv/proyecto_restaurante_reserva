/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gian Marrufo
 */
public class Plato {
    private int idMenu;
    private String nombre;
    private String tipo;
    private String descripcion;
    private double precio;
    private String urlImagen;            // NUEVO campo
    private String ingredientes;
    private String alergenos;

    public Plato() {}

    public Plato(int idMenu, String nombre, String tipo, String descripcion, double precio, String urlImagen, String ingredientes,
            String alergenos) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.tipo = tipo;
        this.ingredientes = ingredientes;
        this.alergenos = alergenos;
    }
    
       public Plato(int idMenu, String nombre, String tipo, String descripcion, double precio, String urlImagen) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.tipo = tipo;
    }

    // Getters
    public int getIdMenu() { return idMenu; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public String getUrlImagen() { return urlImagen; }

    public String getAlergenos() {
        return alergenos;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getTipo() {
        return tipo;
    }
    

    // Setters

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
    }

    
    
    /*@Override
    public String toString() {
        return "Menu{" +
               "idMenu=" + idMenu +
               ", nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", precio=" + precio +
               ", urlImagen='" + urlImagen + '\'' +
               '}';
    }*/
    @Override
    public String toString() {
        return nombre; // así se mostrará solo el nombre en el combo
    }

}
