package dh.clinica.model;

import java.util.Date;

public class Paciente {
    private Integer id;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String dni;
    private Date fechaAlta;

    // Constructor
    public Paciente(String nombre, String apellido, String domicilio, String dni, Date fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
