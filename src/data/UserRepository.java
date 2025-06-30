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
}
