package com.tesis.gchavez.appcobranzamg.models;

public class Documento {

    private Integer id;
    private Integer cliente_id;
    private Cliente cliente_c;

    public Documento(){

    }

    public Documento(Integer id, Integer cliente_id, Cliente cliente_c) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.cliente_c = cliente_c;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getCliente_id() { return cliente_id; }
    public void setCliente_id(Integer cliente_id) { this.cliente_id = cliente_id; }

    public Cliente getCliente_c() {return cliente_c;}
    public void setCliente_c(Cliente cliente_c) { this.cliente_c = cliente_c; }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", cliente_id=" + cliente_id +
                ", cliente_c=" + cliente_c +
                '}';
    }
}
