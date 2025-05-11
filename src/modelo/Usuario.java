/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gian Marrufo
 */
public class Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private String email;
    private String contraseña;
    private int idTipo;
    private String telefono;
    private String dni;
    private String direccion;

    public Usuario() {}

    public Usuario(int idUsuario, String nombreCompleto, String email, String contraseña,
                   int idTipo, String telefono, String dni, String direccion) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contraseña = contraseña;
        this.idTipo = idTipo;
        this.telefono = telefono;
        this.dni = dni;
        this.direccion = direccion;
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

    public String getContraseña() {
        return contraseña;
    }

    public int getIdTipo() {
        return idTipo;
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

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
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

 
    
    
}
