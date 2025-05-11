/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

//import java.awt.List;
import java.util.Date;
import java.sql.Time;  
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gian Marrufo
 */
public class Reserva {
    private int idReserva;
    private Usuario usuario; //Relación asoc con usuario
    private Date fechaReserva;
    private Time horaReserva;
    private int cantPersonas;
    private String estado;
    private Mesa mesa; //Relación asoc con mesa
    private List<Pedido> pedidos; // Relación de comp con pedido
    
    public Reserva() {}

    public Reserva(int idReserva, Usuario usuario, Date fechaReserva, Time horaReserva,
                   int cantPersonas, String estado, Mesa mesa) {
        this.idReserva = idReserva;
        this.usuario = usuario;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.cantPersonas = cantPersonas;
        this.estado = estado;
        this.mesa = mesa;
        this.pedidos= new LinkedList<>();
    }

    public int getIdReserva() {
        return idReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }



    public Date getFechaReserva() {
        return fechaReserva;
    }

    public Time getHoraReserva() {
        return horaReserva;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public String getEstado() {
        return estado;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
       

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setHoraReserva(Time horaReserva) {
        this.horaReserva = horaReserva;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
    
}
