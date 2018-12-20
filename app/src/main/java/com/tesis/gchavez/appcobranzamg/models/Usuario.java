package com.tesis.gchavez.appcobranzamg.models;

public class Usuario {

    private Integer id;
    private String login;
    private String password;
    private String nombre;
    private String imagen;
    private String telefono;
    private String correo;
    private String zona;
    private Integer clienAsig;
    private String role;

    public Usuario(){
    }

    public Usuario(Integer id, String login, String password, String nombre, String imagen, String telefono, String correo, String zona, Integer clienAsig, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.imagen = imagen;
        this.telefono = telefono;
        this.correo = correo;
        this.zona = zona;
        this.clienAsig = clienAsig;
        this.role = role;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public Integer getClienAsig() { return clienAsig; }
    public void setClienAsig(Integer clienAsig) { this.clienAsig = clienAsig; }

    public String getRole() {  return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", zona='" + zona + '\'' +
                ", clienAsig=" + clienAsig +
                ", role='" + role + '\'' +
                '}';
    }
}
