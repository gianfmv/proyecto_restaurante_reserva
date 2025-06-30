/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.prefs.Preferences;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 *
 * @author salaz
 */
public class UserUtil {
    private static final Preferences prefs = Preferences.userRoot().node("miApp");
    public static void guardarUsuario(Usuario usuario) {
        prefs.putInt("id", usuario.getIdUsuario());
        prefs.put("nombre", usuario.getNombreCompleto());
        prefs.put("email", usuario.getEmail());
        prefs.put("telefono", usuario.getTelefono());
        prefs.put("dni", usuario.getDni());
        prefs.put("direccion", usuario.getDireccion());
        prefs.putInt("idTipo", usuario.getTipoUsuario().getIdTipo());
        prefs.put("descTipo", usuario.getTipoUsuario().getDescripcion());
        
    }
    
    public static Usuario getUsuario() {
        int id = prefs.getInt("id", -1);
        if(id == -1) return null;
        String nombre = prefs.get("nombre","");
        String email = prefs.get("email", "");
        String telefono = prefs.get("telefono", "");
        String dni = prefs.get("dni", "");
        String direccion = prefs.get("direccion", "");
        int idTipo = prefs.getInt("idTipo", -1);
        String descTipo = prefs.get("descTipo", "");
        TipoUsuario tipo = new TipoUsuario(idTipo, descTipo);
        
        Usuario usuario = new Usuario(id, nombre, email, telefono, dni, direccion, tipo);
        return usuario;
    }
    
    public static void borrarUsuarioLocal() {
        prefs.remove("id");
        prefs.remove("email");
        prefs.remove("idTipo");
    }
}
