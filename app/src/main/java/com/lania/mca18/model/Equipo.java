package com.lania.mca18.model;

import org.json.JSONException;
import org.json.JSONObject;

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

    public Equipo(long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Equipo(Item item)
    {
        super(item.getType());
        this.id = item.getId();
    }

    @Override
    public String toJSON()
    {
        JSONObject obj = new JSONObject();

        try {
            obj.put("id", this.id);
            obj.put("nombre", this.nombre);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj.toString();
    }
}
