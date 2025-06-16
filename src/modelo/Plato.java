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
    private String descripcion;
    private double precio;
    private String urlImagen;            // NUEVO campo
    private List<Ingrediente> ingredientes;

    public Plato() {}

    public Plato(int idMenu, String nombre, String descripcion, double precio, String urlImagen) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.ingredientes = new LinkedList<>();
    }

    // Getters
    public int getIdMenu() { return idMenu; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public String getUrlImagen() { return urlImagen; }
    public List<Ingrediente> getIngredientes() { return ingredientes; }

    // Setters
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }
    public void setIngredientes(List<Ingrediente> ingredientes) { this.ingredientes = ingredientes; }

    @Override
    public String toString() {
        return "Menu{" +
               "idMenu=" + idMenu +
               ", nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", precio=" + precio +
               ", urlImagen='" + urlImagen + '\'' +
               '}';
    }
}
