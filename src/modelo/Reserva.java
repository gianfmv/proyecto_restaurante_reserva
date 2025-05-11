/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import java.sql.Time;  

/**
 *
 * @author Gian Marrufo
 */
public class Reserva {
    private int idReserva;
    private int idCliente;
    private Date fechaReserva;
    private Time horaReserva;
    private int cantPersonas;
    private String estado;
    private int idMesa;

    public Reserva() {}

    public Reserva(int idReserva, int idCliente, Date fechaReserva, Time horaReserva,
                   int cantPersonas, String estado, int idMesa) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.cantPersonas = cantPersonas;
        this.estado = estado;
        this.idMesa = idMesa;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getIdCliente() {
        return idCliente;
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

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
    
    
    
}
