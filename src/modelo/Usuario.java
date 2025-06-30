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
public class Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String dni;
    private String direccion;
    
     private TipoUsuario tipoUsuario;  // Relación de asociación
     private List<Ingrediente> ingredientesAlergicos; //asociación
     
    public Usuario() {}

    public Usuario(int idUsuario, String nombreCompleto, String email,
                   String telefono, String dni, String direccion, TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.dni = dni;
        this.direccion = direccion;
        this.tipoUsuario=tipoUsuario;
        this.ingredientesAlergicos= new LinkedList<>();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public List<Ingrediente> getIngredientesAlergicos() {
        return ingredientesAlergicos;
    }
    
    

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setIngredientesAlergicos(List<Ingrediente> ingredientesAlergicos) {
        this.ingredientesAlergicos = ingredientesAlergicos;
    }

 
    
    
}
