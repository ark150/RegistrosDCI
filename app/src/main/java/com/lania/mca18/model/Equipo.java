package com.lania.mca18.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Equipo extends Item {
    private String nombre;
    private boolean detallado;
    private List<Persona> integrantes;

    public static String INTEGRANTES = "integrantes";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getIntegrantes() {
        return this.integrantes;
    }

    public void setIntegrantes(List<Persona> integrantes) {
        this.integrantes = integrantes;
    }

    public void setDetallado(boolean d)
    {
        this.detallado = d;
    }

    public boolean getDetallado() { return this.detallado; }

    public Equipo()
    {
        super();
        this.nombre = "";
        this.detallado = false;
        integrantes = new ArrayList<>();
    }

    public Equipo(long id) {
        super();
        this.id = id;
        this.nombre = "";
        this.detallado = false;
        integrantes = new ArrayList<>();
    }

    public Equipo(long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.detallado = false;
        integrantes = new ArrayList<>();
    }

    public Equipo(Item item)
    {
        super(item.getType());
        this.id = item.getId();
        this.detallado = false;
        integrantes = new ArrayList<>();
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
