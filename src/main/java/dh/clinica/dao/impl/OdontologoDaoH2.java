package dh.clinica.dao.impl;

import dh.clinica.dao.Idao;
import dh.clinica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements Idao<Odontologo> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/clinica_dh;INIT=RUNSCRIPT FROM 'classpath:create.sql'";

    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "sa";
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO odontologos (apellido, nombre, matricula) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getApellido());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getMatricula());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }
            LOGGER.info("Odontólogo guardado con matrícula: " + odontologo.getMatricula());
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Error al guardar odontólogo", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error al cerrar la conexión", e);
            }
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(
                        resultSet.getInt("id"),
                        resultSet.getString("matricula"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido")
                );
                odontologos.add(odontologo);
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Error al listar odontólogos", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error al cerrar la conexión", e);
            }
        }

        return odontologos;
    }

    @Override
    public Odontologo buscar(int id) {
        String sql = "SELECT * FROM odontologos WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Odontologo(
                        resultSet.getInt("id"),
                        resultSet.getString("matricula"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido")
                );
            }
        } catch (SQLException e) {
            LOGGER.error("Error al buscar odontólogo", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM odontologos WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Odontólogo eliminado con ID: " + id);
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar odontólogo", e);
            throw new RuntimeException(e);
        }
    }

//    public void actualizarMatricula(int id, String nuevaMatricula) {
//        String sql = "UPDATE odontologos SET matricula = ? WHERE id = ?";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setString(1, nuevaMatricula);
//            preparedStatement.setInt(2, id);
//            preparedStatement.executeUpdate();
//
//            LOGGER.info("Matrícula actualizada a: " + nuevaMatricula);
//        } catch (SQLException e) {
//            LOGGER.error("Error al actualizar la matrícula", e);
//            throw new RuntimeException(e);
//        }
//    }

}