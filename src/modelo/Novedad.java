/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author salaz
 */
public class Novedad {
    private String titulo;
    private String descripcion;
    private String imageUrl;

    public Novedad(String titulo, String descripcion, String imageUrl) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imageUrl = imageUrl;
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
