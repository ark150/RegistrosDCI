package com.lania.mca18.model;

public class Item {
    protected long id;
    private String action;
    private String type;

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
        action = "create";
    }

    public Item(String type)
    {
        this.type = type;
        action = "list";
    }

}
