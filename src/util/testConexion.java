/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gian Marrufo
 */
public class testConexion {
        public static void main(String[] args) {
        Connection conn = null;
            try {
                conn = ConexionSQL.obtenerConexion();
            } catch (SQLException ex) {
                Logger.getLogger(testConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (conn != null) {
            System.out.println("Conexión establecida correctamente.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}
