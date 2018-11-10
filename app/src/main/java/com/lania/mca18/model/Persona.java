package com.lania.mca18.model;

public class Persona extends Item {
    private String nombre;
    private String institucionDeOrigen;
    private String facebook;
    private String correo;
    private Equipo equipo;
    private Computadora computadora;
    private String uuid;
    private String hash;

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

    public Persona()
    {
        super();
        initialize();
    }

    public Persona(long id)
    {
        super();
        this.id = id;
        initialize();
    }

    protected void initialize()
    {
        this.nombre = "";
        this.institucionDeOrigen = "";
        this.facebook = "";
        this.correo = "";
        // Equipo this.equipo = "";
        // Computadora this.computadora = "";
        this.uuid = "";
        this.hash = "";
    }
}
