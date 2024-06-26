package dh.clinica.dao;

import java.util.List;

public interface Idao<T> {
    public T guardar(T t);
    public List<T> listar();
    public T buscar(int id);
    public void eliminar(int id);
}
