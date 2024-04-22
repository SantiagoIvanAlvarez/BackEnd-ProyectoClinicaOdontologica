package dh.clinica.dao.impl;

import dh.clinica.dao.Idao;
import dh.clinica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements Idao<Paciente> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/clinica_dh;INIT=RUNSCRIPT FROM 'classpath:create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "sa";
    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO pacientes (nombre, apellido, domicilio, dni, fecha_alta) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDomicilio());
            preparedStatement.setString(4, paciente.getDni());
            preparedStatement.setDate(5, java.sql.Date.valueOf(paciente.getFechaAlta()));

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
            LOGGER.info("Paciente guardado con DNI: " + paciente.getDni());
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Error al guardar paciente", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error al cerrar la conexión", e);
            }
        }
        return paciente;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM pacientes");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Paciente paciente = new Paciente(
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("domicilio"),
                        resultSet.getString("dni"),
                        resultSet.getDate("fecha_alta").toLocalDate()
                );
                paciente.setId(resultSet.getInt("id"));
                pacientes.add(paciente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Error al listar pacientes", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error al cerrar la conexión", e);
            }
        }
        return pacientes;
    }

    @Override
    public Paciente buscar(int id) {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Paciente(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("domicilio"),
                        resultSet.getString("dni"),
                        resultSet.getDate("fecha_alta").toLocalDate()
                );
            }
        } catch (SQLException e) {
            LOGGER.error("Error al buscar paciente", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Paciente eliminado con ID: " + id);
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar paciente", e);
            throw new RuntimeException(e);
        }
    }
}
