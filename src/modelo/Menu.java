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
public class Menu {
    private int idMenu;
    private String nombre;
    private String descripcion;
    private double precio;
    private List<Ingrediente> ingredientes; //Relación de comp con ingredientes
    
    public Menu() {}

    public Menu(int idMenu, String nombre, String descripcion, double precio) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ingredientes=new LinkedList<>();
    }

    public int getIdMenu() {
        return idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }
    
    

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    
    
    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
    
    
    
    
}
