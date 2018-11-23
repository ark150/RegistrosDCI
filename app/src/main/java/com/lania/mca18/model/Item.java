package com.lania.mca18.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringWriter;

public class Item {
    protected long id;
    private String action;
    private String type;

    public static String CREATE = "create";
    public static String LIST = "list";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() { return this.action; }

    public void setAction(String action) { this.action = action; }

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    public Item()
    {
        action = Item.CREATE;
    }

    public Item(String type)
    {
        this.type = type;
        action = Item.LIST;
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
