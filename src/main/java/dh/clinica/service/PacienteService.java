package dh.clinica.service;

import dh.clinica.dao.Idao;
import dh.clinica.model.Paciente;

import java.util.List;

public class PacienteService {
    private Idao<Paciente> pacienteDao;

    public PacienteService(Idao<Paciente> pacienteDao) {
        this.pacienteDao = pacienteDao;
    }

    public Paciente registrar(Paciente paciente) {
        return pacienteDao.guardar(paciente);
    }

    public List<Paciente> listar() {
        return pacienteDao.listar();
    }

    public void eliminar(int id) {
        pacienteDao.eliminar(id);
    }

    public Paciente buscar(int id) {
        return pacienteDao.buscar(id);
    }


}
