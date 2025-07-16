/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gian Marrufo
 */
public class Mesa {
    private int idMesa;
    private int numeroMesa;
    private int capacidad;
    private boolean disponible;

    public Mesa() {}

    public Mesa(int idMesa, int numeroMesa, int capacidad, boolean disponible) {
        this.idMesa = idMesa;
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
}
