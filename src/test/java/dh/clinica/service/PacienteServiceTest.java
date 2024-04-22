package dh.clinica.service;
import dh.clinica.dao.impl.PacienteDaoH2;
import dh.clinica.model.Paciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

class PacienteServiceTest {

    @Test
    void guardarPacienteTest() {
        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        Paciente paciente = new Paciente("Lucas", "Velazquez", "Calle unica 502", "67899432", LocalDate.now());
        Paciente guardado = pacienteService.registrar(paciente);
        Assertions.assertNotNull(guardado.getId());
    }

    @Test
    void listarPacientesTest() {
        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        List<Paciente> pacientes = pacienteService.listar();
        Assertions.assertFalse(pacientes.isEmpty());
    }

    @Test
    void buscarPacienteExistenteTest() {
        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        int idPacienteABuscar = 21;

        Paciente encontrado = pacienteService.buscar(idPacienteABuscar);
        Assertions.assertNotNull(encontrado, "El paciente debe existir.");
        Assertions.assertEquals(idPacienteABuscar, encontrado.getId(), "El ID del paciente encontrado debe coincidir con el buscado.");
        if (encontrado != null) {
            System.out.println("Paciente encontrado:");
            System.out.println("ID: " + encontrado.getId());
            System.out.println("Fecha_alta: " + encontrado.getFechaAlta());
            System.out.println("Domicilio: " + encontrado.getDomicilio());
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Apellido: " + encontrado.getApellido());
        } else {
            System.out.println("No se encontró el paciente con ID: " + idPacienteABuscar);
        }
    }

    @Test
    void eliminarPacienteExistenteTest() {
        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        int idPacienteAEliminar = 21;
        Paciente pacienteExistente = pacienteService.buscar(idPacienteAEliminar);
        Assertions.assertNotNull(pacienteExistente, "El paciente debe existir antes de ser eliminado.");

        pacienteService.eliminar(idPacienteAEliminar);

        Assertions.assertNull(pacienteService.buscar(idPacienteAEliminar), "El paciente debe ser null después de ser eliminado.");
    }
}
