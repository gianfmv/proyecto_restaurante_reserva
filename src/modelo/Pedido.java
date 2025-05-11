/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gian Marrufo
 */
public class Pedido {
    private int idPedido;
    private int idReserva;
    private double total;

    public Pedido() {}

    public Pedido(int idPedido, int idReserva, double total) {
        this.idPedido = idPedido;
        this.idReserva = idReserva;
        this.total = total;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public double getTotal() {
        return total;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
}
