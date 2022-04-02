package com.items;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public abstract class Item {
    protected int id;
    protected String title;
    protected String location;

    public Item() {}

    public Item(int id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Objects.equals(title, item.title) && Objects.equals(location, item.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, location);
    }

    @Override
    public String toString() {
        return findType() + "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public abstract JsonNode serialize();
    public abstract String findTextRepresentation();
    public abstract String findType();

    public String textRepresentation()
    {
        String representation = "";
        representation += ("id : " + id + "\n");
        representation += ("title : " + title + "\n");
        representation += ("location : " + location + "\n");
        return representation;
    }
}
