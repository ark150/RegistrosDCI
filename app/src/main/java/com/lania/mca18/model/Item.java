package com.lania.mca18.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {
    protected long id;
    protected boolean reg;
    private String action;
    private String type;

    public static String CREATE = "create";
    public static String LIST = "list";
    public static String REG_IO = "ioregistro";
    public static String GETDATAID = "";
    public static String REG_IOCreate = Item.REG_IO + "/" + Item.CREATE;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() { return this.action; }

    public void setAction(String action)
    {
        /*if(action == Item.REG_IO)
        {
            this.action = Item.REG_IOCreate;
        }
        else
            this.action = action;*/

        this.action = action;
    }

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    public Item()
    {
        action = Item.CREATE;
        reg = false;
    }

    public Item(String type)
    {
        this.type = type;
        action = Item.LIST;
        reg = false;
    }

    public String toJSON()
    {
        JSONObject obj = new JSONObject();

        try {
            obj.put("id", this.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj.toString();
    }
}
