package com.lania.mca18.model;

import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * Establece si es una entrada o una salida.
     * @param reg Verdadero es entrada. Falso es salida.
     */
    public void setRegist(boolean reg) { this.reg = reg; }

    public boolean getRegist() { return this.reg; }

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

    public Persona(Item item)
    {
        super(item.getType());
        this.id = item.getId();
    }

    protected void initialize()
    {
        this.nombre = "";
        this.institucionDeOrigen = "";
        this.facebook = "";
        this.correo = "";
        // this.equipo = new Equipo();
        // this.computadora = new Computadora();
        this.uuid = "";
        this.hash = "";
    }

    @Override
    public String toJSON()
    {
        JSONObject obj = new JSONObject();

        try {
            obj.put("nombre", this.nombre);
            obj.put("institucionDeOrigen", this.institucionDeOrigen);
            obj.put("facebook", this.facebook);
            obj.put("correo", this.correo);
            // obj.put("equipo", this.equipo.getId());
            // obj.put("computadora", this.computadora.getId());
            // obj.put("uuid", this.uuid);
            // obj.put("hash", this.hash);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj.toString();
    }

    /**
     * Verdadero: Entrada. Falso: Salida.
     * @return JSON String de registro.
     */
    public String getRegisterItemJSON()
    {
        String str = "{\"persona\":{\"id\": " + this.getId() + "}, \"entrada\": ";
        if(this.getRegist())
            str += "true, \"salida\":false}";
        else
            str += "false, \"salida\":true}";

        return str;
    }
}
