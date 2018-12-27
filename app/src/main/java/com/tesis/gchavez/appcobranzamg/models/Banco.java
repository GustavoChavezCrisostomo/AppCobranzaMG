package com.tesis.gchavez.appcobranzamg.models;

public class Banco {
    private Integer id;
    private String nombre;
    private String estado;
    private String obsecacion;

    public Banco(){

    }

    public Banco(Integer id, String nombre, String estado, String obsecacion) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.obsecacion = obsecacion;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObsecacion() { return obsecacion; }
    public void setObsecacion(String obsecacion) { this.obsecacion = obsecacion; }

    @Override
    public String toString() {
        return "Banco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", obsecacion='" + obsecacion + '\'' +
                '}';
    }
}
