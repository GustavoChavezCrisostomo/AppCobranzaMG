package com.tesis.gchavez.appcobranzamg.models;

public class Documento {

    private Integer id;
    private Integer cliente_id;

    public Documento(){

    }

    public Documento(Integer id, Integer cliente_id) {
        this.id = id;
        this.cliente_id = cliente_id;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getCliente_id() { return cliente_id; }
    public void setCliente_id(Integer cliente_id) { this.cliente_id = cliente_id; }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", cliente_id=" + cliente_id +
                '}';
    }
}
