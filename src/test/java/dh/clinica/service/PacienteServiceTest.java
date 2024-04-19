package dh.clinica.service;

import dh.clinica.dao.impl.PacienteDaoH2;
import dh.clinica.model.Paciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.List;

class PacienteServiceTest {

    @Test
    void guardarPacienteTest() {
        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        Paciente paciente = new Paciente("Ana", "Lopez", "Calle Verdadera 456", "98765432", new Date());
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
    void buscarPacienteTest() {
        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        Paciente paciente = new Paciente("Ana", "Lopez", "Calle Verdadera 456", "98765432", new Date());
        Paciente guardado = pacienteService.registrar(paciente);
        Paciente encontrado = pacienteService.buscar(guardado.getId());
        Assertions.assertEquals(guardado.getId(), encontrado.getId());
    }

    @Test
    void eliminarPacienteTest() {
            PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

            // Crear y guardar un nuevo paciente
            String uniqueDNI = "DNI" + System.currentTimeMillis(); // DNI único
            Paciente paciente = new Paciente("Ana", "Lopez", "Calle Verdadera 456", uniqueDNI, new Date());
            Paciente guardado = pacienteService.registrar(paciente);
            Assertions.assertNotNull(guardado.getId(), "El paciente debe ser guardado correctamente");

            // Eliminar el paciente
            pacienteService.eliminar(guardado.getId());

            // Verificar que el paciente fue eliminado
            Paciente eliminado = pacienteService.buscar(guardado.getId());
            Assertions.assertNull(eliminado, "El paciente debe ser null después de ser eliminado.");
    }
}
