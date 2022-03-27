package com.commands;

import com.items.Item;
import com.organizer.Catalog;

public class AddCommand extends CatalogCommand {

    public AddCommand(Catalog catalog) {
        super(catalog);
    }//constructor

    public void runCommand(Item item)
    {
        this.catalog.addItem(item);
    }//adaug item uri in catalog

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "Load";
    }//pentru a descrie foiecare comanda (in viitor)
}
