/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Gian Marrufo
 */
public class Promocion {
    private int idPromocion;
    private String titulo;
    private String descripcion;
    private String imageUrl;
    private Date fechaInicio;
    private Date fechaFin;
    private double descuentoPorcentaje;

    public Promocion() {}

    public Promocion(int idPromocion, String titulo, String descripcion,
                     Date fechaInicio, Date fechaFin, double descuentoPorcentaje, String imageUrl) {
        this.idPromocion = idPromocion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuentoPorcentaje = descuentoPorcentaje;
        this.imageUrl = imageUrl;
    }
    
    public Promocion(String titulo, String descripcion,
                     Date fechaInicio, Date fechaFin, double descuentoPorcentaje, String imageUrl) {        
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuentoPorcentaje = descuentoPorcentaje;
        this.imageUrl = imageUrl;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDescuentoPorcentaje(double descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
