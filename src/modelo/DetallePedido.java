/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gian Marrufo
 */


public class DetallePedido {
    private int idPedido;
    private int idMenu;
    private int cantidad;
    private double precio;
    private String descripcionPlato;

    private Plato plato; // opcional, para mostrar nombre/plato directamente

    public DetallePedido() {}

    public DetallePedido(int idPedido, int idMenu, int cantidad) {
        this.idPedido = idPedido;
        this.idMenu = idMenu;
        this.cantidad = cantidad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setDescripcionPlato(String descripcionPlato) {
        this.descripcionPlato = descripcionPlato;
    }

    public String getDescripcionPlato() {
        return descripcionPlato;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
}

