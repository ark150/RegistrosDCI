package com.lania.mca18.model;

import org.json.JSONException;
import org.json.JSONObject;

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

    public Computadora(Item item)
    {
        super(item.getType());
        this.id = item.getId();
    }

    protected void initialize()
    {
        this.color = "";
        this.modelo = "";
        //this.propietario = ;
    }

    @Override
    public String toJSON()
    {
        JSONObject obj = new JSONObject();

        try {
            obj.put("id", this.id);
            obj.put("modelo", this.modelo);
            obj.put("color", this.color);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj.toString();
    }
}
