package com.organizer;

import com.items.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Catalog {
    private Set<Item> catalogItems;//o multime de item-uri

    public Catalog() {
        catalogItems = new LinkedHashSet<Item>();
    }//instantiere

    public void addItem(Item item)
    {
        catalogItems.add(item);
    }//adaug itemi in catalog

    public Set<Item> getCatalogItems() {
        return catalogItems;
    }//iau toate elementele din catalog (multimea de elememte)

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogItems=" + catalogItems +
                '}';
    }
}
