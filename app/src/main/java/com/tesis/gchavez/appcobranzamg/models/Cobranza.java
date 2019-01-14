package com.tesis.gchavez.appcobranzamg.models;

public class Cobranza {

    private Integer id;
    private Integer usuario_id;
    private String serie;
    private String fecha;
    private String tipo;
    private String numDoc;
    private Integer cliente_id;
    private String distritoCli;
    private String rucCli;
    private String tipoPago;
    private Double monto;
    private String numCheque;
    private Integer banco_id;
    private String numOpe;
    private String observaciones;

    public Cobranza() {

    }

    public Cobranza(Integer id, Integer usuario_id, String serie, String fecha, String tipo, String numDoc, Integer cliente_id, String distritoCli, String rucCli, String tipoPago, Double monto, String numCheque, Integer banco_id, String numOpe, String observaciones) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.serie = serie;
        this.fecha = fecha;
        this.tipo = tipo;
        this.numDoc = numDoc;
        this.cliente_id = cliente_id;
        this.distritoCli = distritoCli;
        this.rucCli = rucCli;
        this.tipoPago = tipoPago;
        this.monto = monto;
        this.numCheque = numCheque;
        this.banco_id = banco_id;
        this.numOpe = numOpe;
        this.observaciones = observaciones;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUsuario_id() { return usuario_id; }
    public void setUsuario_id(Integer usuario_id) { this.usuario_id = usuario_id; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getNumDoc() { return numDoc; }
    public void setNumDoc(String numDoc) { this.numDoc = numDoc; }

    public Integer getCliente_id() { return cliente_id; }
    public void setCliente_id(Integer cliente_id) { this.cliente_id = cliente_id; }

    public String getDistritoCli() { return distritoCli; }
    public void setDistritoCli(String distritoCli) { this.distritoCli = distritoCli; }

    public String getRucCli() { return rucCli; }
    public void setRucCli(String rucCli) { this.rucCli = rucCli; }

    public String getTipoPago() { return tipoPago; }
    public void setTipoPago(String tipoPago) { this.tipoPago = tipoPago; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getNumCheque() { return numCheque; }
    public void setNumCheque(String numCheque) { this.numCheque = numCheque; }

    public Integer getBanco_id() { return banco_id; }
    public void setBanco_id(Integer banco_id) { this.banco_id = banco_id; }

    public String getNumOpe() { return numOpe; }
    public void setNumOpe(String numOpe) { this.numOpe = numOpe; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public String toString() {
        return "Cobranza{" +
                "id=" + id +
                ", usuarioId=" + usuario_id +
                ", serie='" + serie + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tipo=" + tipo +
                ", numDoc='" + numDoc + '\'' +
                ", clienteId=" + cliente_id +
                ", distritoCli='" + distritoCli + '\'' +
                ", rucCli='" + rucCli + '\'' +
                ", tipoPago='" + tipoPago + '\'' +
                ", monto=" + monto +
                ", numCheque='" + numCheque + '\'' +
                ", bancoId=" + banco_id +
                ", numOpe='" + numOpe + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
