package com.commands;

import com.items.Item;
import com.organizer.Catalog;

public class AddCommand extends CatalogCommand {

    public AddCommand(Catalog catalog) {
        super(catalog);
    }

    public void runCommand(Item item)
    {
        this.catalog.addItem(item);
    }

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "Load";
    }
}
