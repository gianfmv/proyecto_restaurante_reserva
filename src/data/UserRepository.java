/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import util.ConexionSQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Resultado;
import modelo.TipoUsuario;
import modelo.Usuario;


/**
 *
 * @author salaz
 */
public class UserRepository {
    public static Resultado<Boolean> registrar(String nombre, String email, String contrasena, String telefono,
            String dni, String direccion, String tipo) {
        Connection connection;
        try {
            connection =  ConexionSQL.obtenerConexion();
        } catch (SQLException ex) {
            return new Resultado(false, false, "Ocurrió un error al conectarse a la base de datos.");
        }
        int tipoUsuario = tipo.equals("Administrador") ? 1 : 2;
        try {
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO [dbo].[Usuario]\n" +
"           ([NombreCompleto]\n" +
"           ,[Email]\n" +
"           ,[Contraseña]\n" +
"           ,[IdTipo]\n" +
"           ,[Telefono]\n" +
"           ,[DNI]\n" +
"           ,[Direccion])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" + "," + tipoUsuario +
"           \n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nombre);
            statement.setString(2, email);
            statement.setString(3, contrasena);
            statement.setString(4, telefono);
            statement.setString(5, dni);
            statement.setString(6, direccion);
            statement.executeQuery();
            connection.commit();
            connection.close();
            return new Resultado(true, true, null);
        } catch (SQLException exception) {
            return new Resultado(false, false, "Ocurrió un error al crear el usuario.");
        }
    }
    
    public static Resultado<Usuario> login(String email, String password) throws SQLException {
      
        String sql = "SELECT u.IdUsuario, u.NombreCompleto, u.Email, u.Telefono, u.DNI, u.Direccion, " +
                     "u.IdTipo, t.Descripcion " +
                     "FROM Usuario u JOIN TipoUsuario t ON t.IdTipo = u.IdTipo " +
                     "WHERE u.Email = ? AND u.Contraseña = ?";
        
        Connection connection;
        try {
            connection =  ConexionSQL.obtenerConexion();
        } catch (SQLException ex) {
            return new Resultado(false, false, "Ocurrió un error al conectarse a la base de datos.");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TipoUsuario tipo = new TipoUsuario(
                    rs.getInt("IdTipo"),
                    rs.getString("Descripcion")
                );

                Usuario usuario = new Usuario(
                    rs.getInt("IdUsuario"),
                    rs.getString("NombreCompleto"),
                    rs.getString("Email"),
                    rs.getString("Telefono"),
                    rs.getString("DNI"),
                    rs.getString("Direccion"),
                    tipo
                );

                return new Resultado(usuario, true, null);
            } else {
                return new Resultado(null, false, "Usuario o contraseña no son válidos.");
            }
        } catch (SQLException e) {
            return new Resultado(null, false, "Ocurrió un error al iniciar sesisón.");
        } finally {
            connection.close();
        }
    }
    
    
    public static Resultado<List<Usuario>> obtenerUsuarios() {
        String sql = "SELECT u.IdUsuario, u.NombreCompleto, u.Email, u.Telefono, u.DNI, u.Direccion, " +
                     "u.IdTipo, t.Descripcion, u.Contraseña FROM Usuario u " +
                     "JOIN TipoUsuario t ON u.IdTipo = t.IdTipo";

        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoUsuario tipo = new TipoUsuario(
                        rs.getInt("IdTipo"),
                        rs.getString("Descripcion")
                );

                Usuario u = new Usuario(
                        rs.getInt("IdUsuario"),
                        rs.getString("NombreCompleto"),
                        rs.getString("Email"),
                        rs.getString("Telefono"),
                        rs.getString("DNI"),
                        rs.getString("Direccion"),
                        tipo,
                        rs.getString("Contraseña")
                );
                lista.add(u);
            }

            return new Resultado<>(lista, true, null);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Resultado<>(null, false, "Error al obtener la lista de usuarios.");
        }
    }

    public static boolean agregarUsuario(Usuario u, String contrasena) {
        String sql = "INSERT INTO Usuario (NombreCompleto, Email, Contraseña, IdTipo, Telefono, DNI, Direccion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombreCompleto());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, contrasena); // Contraseña
            stmt.setInt(4, u.getTipoUsuario().getIdTipo());
            stmt.setString(5, u.getTelefono());
            stmt.setString(6, u.getDni());
            stmt.setString(7, u.getDireccion());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editarUsuario(int id, Usuario u) {
        String sql = "UPDATE Usuario SET NombreCompleto = ?, Email = ?, IdTipo = ?, Telefono = ?, DNI = ?, Direccion = ? " +
                     "WHERE IdUsuario = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombreCompleto());
            stmt.setString(2, u.getEmail());
            stmt.setInt(3, u.getTipoUsuario().getIdTipo());
            stmt.setString(4, u.getTelefono());
            stmt.setString(5, u.getDni());
            stmt.setString(6, u.getDireccion());
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE IdUsuario = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean editarUsuarioConContrasena(int id, Usuario u, String nuevaContrasena) {
    String sql = "UPDATE Usuario SET " +
                 "NombreCompleto = ?, " +
                 "Email = ?, " +
                 "Contraseña = ?, " +
                 "IdTipo = ?, " +
                 "Telefono = ?, " +
                 "DNI = ?, " +
                 "Direccion = ? " +
                 "WHERE IdUsuario = ?";

    try (Connection conn = ConexionSQL.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, u.getNombreCompleto());
        stmt.setString(2, u.getEmail());
        stmt.setString(3, nuevaContrasena);
        stmt.setInt(4, u.getTipoUsuario().getIdTipo());
        stmt.setString(5, u.getTelefono());
        stmt.setString(6, u.getDni());
        stmt.setString(7, u.getDireccion());
        stmt.setInt(8, id);

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al editar usuario con contraseña: " + e.getMessage());
        return false;
    }
}
    
    public static List<TipoUsuario> obtenerTiposUsuario() {
    List<TipoUsuario> tipos = new ArrayList<>();
    String sql = "SELECT IdTipo, Descripcion FROM TipoUsuario";

    try (Connection conn = ConexionSQL.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            TipoUsuario tipo = new TipoUsuario(
                rs.getInt("IdTipo"),
                rs.getString("Descripcion")
            );
            tipos.add(tipo);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error al obtener tipos de usuario: " + e.getMessage());
    }

    return tipos;
}


}
