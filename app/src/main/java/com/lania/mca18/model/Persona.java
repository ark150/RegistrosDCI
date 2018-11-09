package com.lania.mca18.model;

public class Persona {
    private long id;
    private String nombre;
    private String institucionDeOrigen;
    private String facebook;
    private String correo;
    private Equipo equipo;
    private Computadora computadora;
    private String uuid;
    private String hash;

    public long getId() {
        return id;
    }

    // public void setId(long id) {
    //     this.id = id;
    // }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstitucionDeOrigen() {
        return institucionDeOrigen;
    }

    public void setInstitucionDeOrigen(String institucionDeOrigen) {
        this.institucionDeOrigen = institucionDeOrigen;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Persona() { }

    public Persona(long id) {
        this.id = id;
    }
}
