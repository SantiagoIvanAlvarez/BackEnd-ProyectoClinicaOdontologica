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
        Odontologo odontologo = new Odontologo("12345", "Juanse", "Peres");
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
    void buscarOdontologoTest() {
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo("5678", "Laura", "Gomez");
        Odontologo guardado = odontologoService.registrar(odontologo);
        Odontologo encontrado = odontologoService.buscar(guardado.getId());
        Assertions.assertEquals(guardado.getId(), encontrado.getId());
    }

    @Test
    void eliminarOdontologoTest() {
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo("12345", "Juanse", "Peres");
        Odontologo guardado = odontologoService.registrar(odontologo);
        odontologoService.eliminar(guardado.getId());
        Assertions.assertNull(odontologoService.buscar(guardado.getId()));
    }
}
