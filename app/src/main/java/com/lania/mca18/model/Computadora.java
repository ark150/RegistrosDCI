package com.lania.mca18.model;

public class Computadora extends Item {
    private String color;
    private String modelo;
    private Persona propietario;

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

    public Computadora()
    {
        super();
        initialize();
    }

    public Computadora(long id) {
        super();
        this.id = id;
        initialize();
    }

    protected void initialize()
    {
        this.color = "";
        this.modelo = "";
        //this.propietario = ;
    }
}
