package com.lania.mca18.model;

public class Item {
    protected long id;
    private String action;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() { return this.action; }

    public void setAction(String action) { this.action = action; }

    public Item()
    {
        action = "create";
    }
}
