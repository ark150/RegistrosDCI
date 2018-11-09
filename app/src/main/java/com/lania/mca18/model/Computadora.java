package com.lania.mca18.model;

class Computadora {
    private long id;
    private String color;
    private String modelo;
    private Persona propietario;

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    public Computadora() {}

    public Computadora(long id) {
        this.id = id;
    }
}
