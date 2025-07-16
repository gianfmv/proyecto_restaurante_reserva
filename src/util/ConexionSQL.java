/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Gian Marrufo
 */
public class ConexionSQL {
 //   private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Reserva;";
   private static final String URL = "jdbc:sqlserver://161.132.53.247:1433;databaseName=Reserva;encrypt=true;trustServerCertificate=true";
//private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Reserva;encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
//private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Reserva;encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
//private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Reserva;encrypt=false";




    private static final String USUARIO = "sa";
    private static final String CLAVE = "Grupo2@12345678";

    public static Connection obtenerConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        return conexion;
    }
}
