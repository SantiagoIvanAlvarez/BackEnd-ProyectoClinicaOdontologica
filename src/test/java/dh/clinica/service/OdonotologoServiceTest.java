package dh.clinica.service;

import dh.clinica.dao.impl.OdontologoDaoH2;
import dh.clinica.model.Odontologo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

class OdontologoServiceTest {

    @Test
    void guardarOdontologoTest() {
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo("matricula122", "Carlitos", "Bala");
        Odontologo guardado = odontologoService.registrar(odontologo);
        Assertions.assertNotNull(guardado.getId());
    }

    @Test
    void listarOdontologosTest() {
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        List<Odontologo> odontologos = odontologoService.listar();
        Assertions.assertFalse(odontologos.isEmpty());
    }

    @Test
    void buscarOdontologoExistenteTest() {
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        int idOdontologoABuscar = 17;
        Odontologo encontrado = odontologoService.buscar(idOdontologoABuscar);
        Assertions.assertNotNull(encontrado, "El odontólogo debe existir.");
        Assertions.assertEquals(idOdontologoABuscar, encontrado.getId(), "El ID del odontólogo encontrado debe coincidir con el buscado.");
        if (encontrado != null) {
            System.out.println("Odontólogo encontrado:");
            System.out.println("ID: " + encontrado.getId());
            System.out.println("Matrícula: " + encontrado.getMatricula());
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Apellido: " + encontrado.getApellido());
        } else {
            System.out.println("No se encontró el odontólogo con ID: " + idOdontologoABuscar);
        }
    }


    @Test
    void eliminarOdontologoExistenteTest() {
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        int idOdontologoAEliminar = 17;
        Odontologo odontologoExistente = odontologoService.buscar(idOdontologoAEliminar);
        Assertions.assertNotNull(odontologoExistente, "El odontólogo debe existir antes de ser eliminado.");

        odontologoService.eliminar(idOdontologoAEliminar);

        Assertions.assertNull(odontologoService.buscar(idOdontologoAEliminar), "El odontólogo debe ser null después de ser eliminado.");
    }

}
