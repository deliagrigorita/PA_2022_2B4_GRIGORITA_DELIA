package com.organizer;

import com.items.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class Catalog {
    private Set<Item> catalogItems;

    public Catalog() {
        catalogItems = new LinkedHashSet<Item>();
    }

    public void addItem(Item item)
    {
        catalogItems.add(item);
    }

    public Set<Item> getCatalogItems() {
        return catalogItems;
    }

    public Item getItemById(int id)
    {
        for (var item : catalogItems)
        {
            if (item.getId() == id)
            {
                return item;
            }
        }

        System.out.println("Error getting item by id");
        return new Book();
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogItems=" + catalogItems +
                '}';
    }
}
