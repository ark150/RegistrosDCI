package com.lania.mca18.model;

public class Equipo extends Item {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo()
    {
        super();
        this.nombre = "";
    }

    public Equipo(long id) {
        super();
        this.id = id;
        this.nombre = "";
    }
}
