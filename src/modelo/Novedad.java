/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author salaz
 */
public class Novedad {
    private int id;
    private String titulo;
    private String descripcion;
    private String imageUrl;
    private Date fechaInicio;
    private Date fechaFin;

    public Novedad(int ID, String titulo, String descripcion, String imageUrl, Date fechaInicio, Date fechaFin) {
        this.id=ID;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imageUrl = imageUrl;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    
    public Novedad(String titulo, String descripcion, String imageUrl, Date fechaInicio, Date fechaFin) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imageUrl = imageUrl;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }    
}
