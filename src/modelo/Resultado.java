/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author salaz
 * @param <T>
 */
public class Resultado<T> {
    private final T data;
    private final boolean ok;
    private final String message;

    public Resultado(T data, boolean ok, String message) {
        this.data = data;
        this.ok = ok;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }
    
    
}
