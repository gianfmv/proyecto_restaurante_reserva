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
    private int capacidad;
    private boolean disponible;

    public Mesa() {}

    public Mesa(int idMesa, int capacidad, boolean disponible) {
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    public int getIdMesa() {
        return idMesa;
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

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
}
