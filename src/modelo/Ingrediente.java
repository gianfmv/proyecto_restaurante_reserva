/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gian Marrufo
 */
public class Ingrediente {
    private int idIngrediente;
    private String nombre;
    private boolean esAlergeno;

    public Ingrediente() {}

    public Ingrediente(int idIngrediente, String nombre, boolean esAlergeno) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.esAlergeno = esAlergeno;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEsAlergeno() {
        return esAlergeno;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEsAlergeno(boolean esAlergeno) {
        this.esAlergeno = esAlergeno;
    }
    
    
    
}
