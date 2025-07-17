/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gian Marrufo
 */
public class Pedido {
    private int idPedido;
    private int idReserva;
    private Date fechaPedido;

    public Pedido() {}

    public Pedido(int idPedido, int idReserva, Date fechaPedido) {
        this.idPedido = idPedido;
        this.idReserva = idReserva;
        this.fechaPedido = fechaPedido;
    }

    private List<DetallePedido> detalles = new ArrayList<>();


    public int getIdPedido() {
        return idPedido;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    public List<DetallePedido> getDetalles() {
    return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    
}
