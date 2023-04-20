package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.modelos.Huesped;

public class HuespedDao {

    private Connection conn;

    public HuespedDao(Connection conexion) {
        this.conn = conexion;
    }

    public List<Huesped> listar() {

        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = conn
                    .prepareStatement("SELECT * FROM huespedes");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {

                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("id_reserva")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public List<Huesped> listarBusqueda(String cadena) {

        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = conn
                    .prepareStatement("SELECT * FROM huespedes WHERE apellido LIKE ?");

            try (statement) {
                statement.setString(1, cadena + "%");
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {

                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("id_reserva")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
        return resultado;
    }

    public void guardar(Huesped huesped) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String nacimiento = date.format(huesped.getFechaNacimiento());

        try {
            PreparedStatement statement;

            statement = conn.prepareStatement(
                    "INSERT INTO HUESPEDES "
                    + "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
                    + " VALUES (?, ?, ?, ?, ?, ?)"
            );

            try (statement) {

                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, Date.valueOf(nacimiento));
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getId_reserva());


                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int modificar(int idHuesped, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, int id_reserva) {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String nacimiento = date.format(fechaNacimiento);

        try {
            final PreparedStatement statement = conn.prepareStatement(
                    "UPDATE huespedes SET "
                    + " nombre = ?,"
                    + " apellido = ?,"
                    + " fecha_macimiento = ?,"
                    + " nacionalidad = ?,"
                    + " telefono = ? ,"
                    + " id_reserva = ? "
                    + " WHERE id = ?"
            );

            try (statement) {

                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, Date.valueOf(nacimiento));
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, id_reserva);
                statement.setInt(7, idHuesped);

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(int idHuesped) {

        try {

            PreparedStatement statement = conn.prepareStatement("DELETE FROM huespedes WHERE id = ?");

            try (statement) {
                statement.setInt(1, idHuesped);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
