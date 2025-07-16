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
import modelo.Mesa;
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
        String sql = "SELECT Id, Titulo, Descripcion, Imagen, FechaInicio, FechaFin, DescuentoPorcentaje "
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
                        rs.getInt("Id"),
                        rs.getString("Titulo"),
                        rs.getString("Descripcion"),
                        rs.getDate("FechaInicio"),
                        rs.getDate("FechaFin"),
                        rs.getDouble("DescuentoPorcentaje"),
                        rs.getString("Imagen") 
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
        String sql = "SELECT Id, Titulo, Descripcion, Imagen, FechaInicio, FechaFin " +
                     "FROM Novedades " +
                     "WHERE GETDATE() BETWEEN FechaInicio AND FechaFin";

        Connection connection;
        try {
            connection = ConexionSQL.obtenerConexion();
        } catch (SQLException e) {
            return new Resultado<>(null, false, "Error al conectar a la base de datos.");
        }

        List<Novedad> novedades = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Novedad novedad = new Novedad(
                    rs.getInt("Id"),                         // ← ID importante
                    rs.getString("Titulo"),
                    rs.getString("Descripcion"),
                    rs.getString("Imagen"),
                    rs.getDate("FechaInicio"),
                    rs.getDate("FechaFin")
                    
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

   
    
    public static boolean agregarNovedad(Novedad novedad) {
        String sql = "INSERT INTO Novedades (titulo, descripcion, Imagen, fechainicio, fechafin) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novedad.getTitulo());
            stmt.setString(2, novedad.getDescripcion());
            stmt.setString(3, novedad.getImageUrl());
            stmt.setDate(4, new java.sql.Date(novedad.getFechaInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(novedad.getFechaFin().getTime()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editarNovedad(int id, Novedad novedad) {
        String sql = "UPDATE Novedades SET titulo = ?, descripcion = ?, Imagen = ?, fechainicio = ?, fechafin = ? WHERE id = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novedad.getTitulo());
            stmt.setString(2, novedad.getDescripcion());
            stmt.setString(3, novedad.getImageUrl());
            stmt.setDate(4, new java.sql.Date(novedad.getFechaInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(novedad.getFechaFin().getTime()));
            stmt.setInt(6, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean eliminarNovedad(int id) {
        String sql = "DELETE FROM Novedades WHERE id = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }   

    ///////////////////////////////////////////////////////////////////////////////////
    
    public static boolean agregarPromocion(Promocion promo) {
        String sql = "INSERT INTO Promociones (Titulo, Descripcion, FechaInicio, FechaFin, DescuentoPorcentaje, Imagen) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promo.getTitulo());
            stmt.setString(2, promo.getDescripcion());
            stmt.setDate(3, new java.sql.Date(promo.getFechaInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(promo.getFechaFin().getTime()));
            stmt.setDouble(5, promo.getDescuentoPorcentaje());
            stmt.setString(6, promo.getImageUrl());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static boolean editarPromocion(int id, Promocion promo) {
        String sql = "UPDATE Promociones SET Titulo = ?, Descripcion = ?, FechaInicio = ?, FechaFin = ?, DescuentoPorcentaje = ?, Imagen = ? WHERE IdPromocion = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promo.getTitulo());
            stmt.setString(2, promo.getDescripcion());
            stmt.setDate(3, new java.sql.Date(promo.getFechaInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(promo.getFechaFin().getTime()));
            stmt.setDouble(5, promo.getDescuentoPorcentaje());
            stmt.setString(6, promo.getImageUrl());
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean eliminarPromocion(int id) {
        String sql = "DELETE FROM Promociones WHERE IdPromocion = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
        public static Resultado<List<Mesa>> obtenerMesas() {
        String sql = "SELECT IdMesa, Capacidad, Disponible FROM Mesas";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<Mesa> mesas = new ArrayList<>();

            while (rs.next()) {
                Mesa mesa = new Mesa(
                    rs.getInt("IdMesa"),
                    rs.getInt("Capacidad"),
                    rs.getBoolean("Disponible")
                );
                mesas.add(mesa);
            }

            return new Resultado<>(mesas, true, null);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Resultado<>(null, false, "Error al obtener las mesas.");
        }
    }

    public static boolean agregarMesa(Mesa mesa) {
        String sql = "INSERT INTO Mesas (Capacidad, Disponible) VALUES (?, ?)";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mesa.getCapacidad());
            stmt.setBoolean(2, mesa.isDisponible());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editarMesa(int id, Mesa mesa) {
        String sql = "UPDATE Mesas SET Capacidad = ?, Disponible = ? WHERE IdMesa = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mesa.getCapacidad());
            stmt.setBoolean(2, mesa.isDisponible());
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarMesa(int id) {
        String sql = "DELETE FROM Mesas WHERE IdMesa = ?";

        try (Connection conn = ConexionSQL.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
