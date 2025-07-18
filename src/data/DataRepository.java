/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.beans.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.DetallePedido;
import modelo.Mesa;
import modelo.Novedad;
import modelo.Pedido;
import modelo.Plato;
import modelo.Promocion;
import modelo.Reserva;
import modelo.Resultado;
import modelo.Usuario;
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

    public static Resultado<Boolean> insertarReserva(Reserva reserva) {
        String sql = "INSERT INTO Reservas (IdCliente, FechaReserva, HoraReserva, CantPersonas, Estado, IdMesa) "
                + "VALUES (?, ?, ?, ?, ?, ?);" +
"UPDATE Mesas " +
"     SET [Disponible] = 0" +
" WHERE IdMesa = ?;";

        Connection connection;
        try {
            connection = ConexionSQL.obtenerConexion();
        } catch (SQLException e) {
            return new Resultado<>(false, false, "Error al conectar a la base de datos.");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Asignar parámetros a la consulta
            stmt.setInt(1, reserva.getUsuario().getIdUsuario());  // Asumiendo que Usuario tiene getIdUsuario()
            stmt.setDate(2, new Date(reserva.getFechaReserva().getTime()));          // java.sql.Date
            stmt.setTime(3, reserva.getHoraReserva());           // java.sql.Time
            stmt.setInt(4, (int)reserva.getCantPersonas());
            stmt.setString(5, reserva.getEstado());
            stmt.setInt(6, reserva.getMesa().getIdMesa());       // Asumiendo que Mesa tiene getIdMesa()
            stmt.setInt(7, reserva.getMesa().getIdMesa());
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                return new Resultado<>(true, true, null);
            } else {
                return new Resultado<>(false, false, "No se pudo insertar la reserva.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new Resultado<>(false, false, "Error al insertar la reserva: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Resultado<List<Novedad>> obtenerNovedades() {
        String sql = "SELECT Id, Titulo, Descripcion, Imagen, FechaInicio, FechaFin "
                + "FROM Novedades "
                + "WHERE GETDATE() BETWEEN FechaInicio AND FechaFin";

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
                        rs.getInt("Id"), // ← ID importante
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

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "UPDATE Promociones SET Titulo = ?, Descripcion = ?, FechaInicio = ?, FechaFin = ?, DescuentoPorcentaje = ?, Imagen = ? WHERE Id = ?";

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "DELETE FROM Promociones WHERE Id = ?";

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Resultado<List<Mesa>> obtenerMesas() {
        List<Mesa> lista = new ArrayList<>();
        String sql = "SELECT IdMesa, NumeroMesa, Capacidad, Disponible FROM Mesas";

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mesa m = new Mesa(
                        rs.getInt("IdMesa"),
                        rs.getInt("NumeroMesa"),
                        rs.getInt("Capacidad"),
                        rs.getBoolean("Disponible")
                );
                lista.add(m);
            }
            return new Resultado<>(lista, true, null);
        } catch (SQLException e) {
            return new Resultado<>(null, false, "Error al obtener mesas: " + e.getMessage());
        }
    }

    public static boolean agregarMesa(Mesa mesa) {
        String sql = "INSERT INTO Mesas (NumeroMesa, Capacidad, Disponible) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumeroMesa());
            stmt.setInt(2, mesa.getCapacidad());
            stmt.setBoolean(3, mesa.isDisponible());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean editarMesa(int id, Mesa mesa) {
        String sql = "UPDATE Mesas SET NumeroMesa = ?, Capacidad = ?, Disponible = ? WHERE IdMesa = ?";

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumeroMesa());
            stmt.setInt(2, mesa.getCapacidad());
            stmt.setBoolean(3, mesa.isDisponible());
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean eliminarMesa(int id) {
        String sql = "DELETE FROM Mesas WHERE IdMesa = ?";

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
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

    // Obtener lista de platos
    public static List<Plato> obtenerPlatos() {
        List<Plato> platos = new ArrayList<>();
        String sql = "SELECT IdMenu, Nombre, Descripcion, Precio, Imagen, Tipo FROM Menus";

        Connection connection = null;

        try {
            connection = ConexionSQL.obtenerConexion();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Plato p = new Plato();
                p.setIdMenu(rs.getInt("IdMenu"));
                p.setNombre(rs.getString("Nombre"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setPrecio(rs.getDouble("Precio"));
                p.setUrlImagen(rs.getString("Imagen"));
                p.setTipo(rs.getString("Tipo"));
                platos.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return platos;
    }

    // Agregar plato
    public static void agregarPlato(Plato p) {
        String sql = "INSERT INTO Menus (Nombre, Descripcion, Precio, Imagen, Tipo) VALUES (?, ?, ?, ?, ?)";

        //Connection connection = null;
        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getDescripcion());
            stmt.setDouble(3, p.getPrecio());
            stmt.setString(4, p.getUrlImagen());
            stmt.setString(5, p.getTipo());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Editar plato
    public static void editarPlato(int id, Plato p) {
        String sql = "UPDATE Menus SET Nombre = ?, Descripcion = ?, Precio = ?, Imagen = ?, Tipo = ? WHERE IdMenu = ?";

        // Connection connection = null;
        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getDescripcion());
            stmt.setDouble(3, p.getPrecio());
            stmt.setString(4, p.getUrlImagen());
            stmt.setString(5, p.getTipo());
            stmt.setInt(6, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar plato
    public static void eliminarPlato(int id) {
        String sql = "DELETE FROM Menus WHERE IdMenu = ?";

        try (Connection conn = ConexionSQL.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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

    public static List<Pedido> obtenerPedidos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedidos";

        try (Connection con = ConexionSQL.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("IdPedido"));
                p.setIdReserva(rs.getInt("IdReserva"));
                p.setFechaPedido(rs.getTimestamp("FechaPedido"));
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static List<DetallePedido> obtenerDetallePedido(int idPedido) {
        List<DetallePedido> lista = new ArrayList<>();
        String sql = """
            SELECT pm.IdPedido, pm.IdMenu, pm.Cantidad, m.Nombre, m.Precio
            FROM Pedido_Menu pm
            JOIN Menus m ON pm.IdMenu = m.IdMenu
            WHERE pm.IdPedido = ?
        """;

        try (Connection con = ConexionSQL.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetallePedido dp = new DetallePedido();
                    dp.setIdPedido(rs.getInt("IdPedido"));
                    dp.setIdMenu(rs.getInt("IdMenu"));
                    dp.setCantidad(rs.getInt("Cantidad"));

                    Plato plato = new Plato();
                    plato.setIdMenu(rs.getInt("IdMenu"));
                    plato.setNombre(rs.getString("Nombre"));
                    plato.setPrecio(rs.getDouble("Precio"));

                    dp.setPlato(plato);
                    lista.add(dp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static int agregarPedidoConDetalle(Pedido pedido) {
        String sqlPedido = "INSERT INTO Pedidos (IdReserva) VALUES (?)";
        String sqlDetalle = "INSERT INTO Pedido_Menu (IdPedido, IdMenu, Cantidad) VALUES (?, ?, ?)";

        int idPedidoGenerado = -1;

        try (Connection con = ConexionSQL.obtenerConexion()) {
            con.setAutoCommit(false); // Iniciar transacción manual

            // 1. Insertar el pedido y obtener ID generado
            try (PreparedStatement ps = con.prepareStatement(sqlPedido, 1)) {
                ps.setInt(1, pedido.getIdReserva());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPedidoGenerado = rs.getInt(1); // Obtener ID generado
                    }
                }
            }

            // 2. Insertar los detalles del pedido (varios platos)
            try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                for (DetallePedido d : pedido.getDetalles()) {
                    psDetalle.setInt(1, idPedidoGenerado);
                    psDetalle.setInt(2, d.getIdMenu());
                    psDetalle.setInt(3, d.getCantidad());
                    psDetalle.addBatch();
                }
                psDetalle.executeBatch(); // Ejecutar todo en lote
            }

            con.commit(); // Confirmar transacción

        } catch (Exception e) {
            e.printStackTrace(); // Imprimir error si ocurre
        }

        return idPedidoGenerado;
    }

    public static List<Reserva> obtenerReservasActivas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT R.IdReserva, R.FechaReserva, R.HoraReserva, R.CantPersonas, R.Estado, "
                + "R.IdCliente, U.NombreCompleto, R.IdMesa "
                + "FROM Reservas R "
                + "JOIN Usuario U ON R.IdCliente = U.IdUsuario "
                + "WHERE R.Estado = 'Pendiente'";

        try (Connection connection = ConexionSQL.obtenerConexion(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setIdReserva(rs.getInt("IdReserva"));
                r.setFechaReserva(rs.getDate("FechaReserva"));
                r.setHoraReserva(rs.getTime("HoraReserva"));
                r.setCantPersonas(rs.getInt("CantPersonas"));
                r.setEstado(rs.getString("Estado"));

                // Cargar cliente como Usuario
                Usuario cliente = new Usuario();
                cliente.setIdUsuario(rs.getInt("IdCliente"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                r.setUsuario(cliente);

                // Cargar mesa
                Mesa m = new Mesa();
                m.setIdMesa(rs.getInt("IdMesa"));
                r.setMesa(m);

                reservas.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservas;
    }

    public static Resultado<List<Reserva>> obtenerReservasDeUsuario(int idCliente) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT R.IdReserva, R.FechaReserva, R.HoraReserva, R.CantPersonas, R.Estado, "
                + "R.IdCliente, U.NombreCompleto, R.IdMesa "
                + "FROM Reservas R "
                + "JOIN Usuario U ON R.IdCliente = U.IdUsuario "
                + "WHERE R.IdCliente = ? ORDER BY R.FechaReserva DESC;";

        try {
            Connection connection = ConexionSQL.obtenerConexion();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setIdReserva(rs.getInt("IdReserva"));
                r.setFechaReserva(rs.getDate("FechaReserva"));
                r.setHoraReserva(rs.getTime("HoraReserva"));
                r.setCantPersonas(rs.getInt("CantPersonas"));
                r.setEstado(rs.getString("Estado"));

                // Cargar cliente como Usuario
                Usuario cliente = new Usuario();
                cliente.setIdUsuario(rs.getInt("IdCliente"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                r.setUsuario(cliente);

                // Cargar mesa
                Mesa m = new Mesa();
                m.setIdMesa(rs.getInt("IdMesa"));
                r.setMesa(m);

                reservas.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Resultado<>(null, false, e.getMessage());
        }

        return new Resultado<>(reservas, true, null);
    }

}
