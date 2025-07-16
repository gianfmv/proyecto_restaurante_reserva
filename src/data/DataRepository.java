/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Novedad;
import modelo.Plato;
import modelo.Promocion;
import modelo.Resultado;
import util.ConexionSQL;

/**
 *
 * @author salaz
 */
public class DataRepository {

    public static Resultado<List<Promocion>> obtenerPromociones() {
        String sql = "SELECT IdPromocion, Titulo, Descripcion, Imagen, FechaInicio, FechaFin, DescuentoPorcentaje "
                + "FROM Promociones "
                + "WHERE FechaInicio <= GETDATE() AND FechaFin >= GETDATE()";

        Connection connection;
        try {
            connection = ConexionSQL.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();

            return new Resultado(null, false, "Error al conectar a la base de datos.");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            List<Promocion> promociones = new ArrayList<>();

            while (rs.next()) {
                Promocion promocion = new Promocion(
                        rs.getInt("IdPromocion"),
                        rs.getString("Titulo"),
                        rs.getString("Descripcion"),
                        rs.getDate("FechaInicio"),
                        rs.getDate("FechaFin"),
                        rs.getDouble("DescuentoPorcentaje"),
                        rs.getString("Imagen") // Esto va a imageUrl
                );

                promociones.add(promocion);
            }

            return new Resultado<>(promociones, true, null);

        } catch (SQLException e) {
            e.printStackTrace();

            return new Resultado<>(null, false, "Ocurrió un error al cargar promociones.");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Resultado<List<Novedad>> obtenerNovedades() {
        String sql = "SELECT Titulo, Descripcion, Imagen "
                + "FROM Novedades "
                + "WHERE GETDATE() BETWEEN FechaInicio AND FechaFin";

        Connection connection;
        try {
            connection = ConexionSQL.obtenerConexion();
        } catch (SQLException e) {
            return new Resultado(null, false, "Error al conectar a la base de datos.");
        }

        List<Novedad> novedades = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Novedad novedad = new Novedad(
                        rs.getString("Titulo"),
                        rs.getString("Descripcion"),
                        rs.getString("Imagen")
                );
                novedades.add(novedad);
            }

            return new Resultado<>(novedades, true, null);

        } catch (SQLException e) {
            return new Resultado<>(null, false, "Error al obtener las novedades.");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Resultado<List<Plato>> obtenerPlatosPorTipo(String tipo) {
        String sql
                = "SELECT "
                + "    M.IdMenu, "
                + "    M.Nombre AS NombreMenu, "
                + "    M.Tipo, "
                + "    M.Descripcion, "
                + "    M.Precio, "
                + "    M.Imagen, "
                + "    STRING_AGG(I.Nombre, ', ') AS TodosLosIngredientes, "
                + "    STRING_AGG(CASE WHEN I.EsAlergeno = 1 THEN I.Nombre ELSE NULL END, ', ') AS IngredientesAlergenos "
                + "FROM Menus M "
                + "LEFT JOIN Menu_Ingredientes MI ON M.IdMenu = MI.IdMenu "
                + "LEFT JOIN Ingredientes I ON MI.IdIngrediente = I.IdIngrediente "
                + "WHERE M.Tipo = ? "
                + "GROUP BY M.IdMenu, M.Nombre, M.Tipo, M.Descripcion, M.Precio, M.Imagen "
                + "ORDER BY M.Nombre";
        Connection connection = null;
        try {
            connection = ConexionSQL.obtenerConexion();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            List<Plato> platos = new ArrayList<>();

            while (rs.next()) {
                Plato plato = new Plato(
                        rs.getInt("IdMenu"),
                        rs.getString("NombreMenu"),
                        rs.getString("Tipo"),
                        rs.getString("Descripcion"),
                        rs.getDouble("Precio"),
                        rs.getString("Imagen"),
                        rs.getString("TodosLosIngredientes"),
                        rs.getString("IngredientesAlergenos")
                );
                platos.add(plato);
            }

            return new Resultado<>(platos, true, null);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Resultado<>(null, false, "Error al obtener platos por tipo.");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static Resultado<List<Plato>> getEntradas() {
        return obtenerPlatosPorTipo("entrada");
    }

    public static Resultado<List<Plato>> getFondos() {
        return obtenerPlatosPorTipo("fondo");
    }
    
    public static Resultado<List<Plato>> getPostres() {
        return obtenerPlatosPorTipo("postre");
    }
    
    public static Resultado<List<Plato>> getBebidas() {
        return obtenerPlatosPorTipo("bebida");
    }
}
