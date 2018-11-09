package com.lania.mca18.model;

class Equipo {
    private long id;
    private String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo() { }

    public Equipo(long id) {
        this.id = id;
    }
}
