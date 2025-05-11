/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gian Marrufo
 */
public class Menu {
    private int idMenu;
    private String nombre;
    private String descripcion;
    private double precio;

    public Menu() {}

    public Menu(int idMenu, String nombre, String descripcion, double precio) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
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
    
    
    
    
}
