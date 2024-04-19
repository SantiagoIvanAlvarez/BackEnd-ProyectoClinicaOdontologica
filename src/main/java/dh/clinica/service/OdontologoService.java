package dh.clinica.service;

import dh.clinica.dao.Idao;
import dh.clinica.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private Idao<Odontologo> odontologoIdao;

    public OdontologoService(Idao<Odontologo> odontologoIdao) {
        this.odontologoIdao = odontologoIdao;
    }

    public Odontologo registrar(Odontologo odontologo){
        return odontologoIdao.guardar(odontologo);

    }

    public Odontologo buscar(int id){
        return odontologoIdao.buscar(id);
    }
    public List<Odontologo> listar(){
        return odontologoIdao.listar();
    }

    public void eliminar(int id){
        odontologoIdao.eliminar(id);
    }
}
