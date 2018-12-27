package com.tesis.gchavez.appcobranzamg.models;

import java.util.Date;

public class Cliente {
    private Integer id;
    private String nombre;
    private String direccion;
    private String distrito;
    private String ruc;
    private String telefono;
    private Double deuda;
    private String fchVenc;
    private String fchCobra;
    private String observacion;
    private String longitud;
    private String latitud;

    public Cliente(){

    }

    public Cliente(Integer id, String nombre, String direccion, String distrito, String ruc, String telefono, Double deuda, String fchVenc, String fchCobra, String observacion, String longitud, String latitud) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.ruc = ruc;
        this.telefono = telefono;
        this.deuda = deuda;
        this.fchVenc = fchVenc;
        this.fchCobra = fchCobra;
        this.observacion = observacion;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Double getDeuda() { return deuda; }
    public void setDeuda(Double deuda) { this.deuda = deuda; }

    public String getFchVenc() { return fchVenc; }
    public void setFchVenc(String fchVenc) { this.fchVenc = fchVenc; }

    public String getFchCobra() { return fchCobra; }
    public void setFchCobra(String fchCobra) { this.fchCobra = fchCobra; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public String getLongitud() { return longitud; }
    public void setLongitud(String longitud) { this.longitud = longitud; }

    public String getLatitud() { return latitud; }
    public void setLatitud(String latitud) { this.latitud = latitud; }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", distrito='" + distrito + '\'' +
                ", ruc='" + ruc + '\'' +
                ", telefono='" + telefono + '\'' +
                ", deuda=" + deuda +
                ", fchVenc=" + fchVenc +
                ", fchCobra=" + fchCobra +
                ", observacion='" + observacion + '\'' +
                ", longitud='" + longitud + '\'' +
                ", latitud='" + latitud + '\'' +
                '}';
    }
}
