package com.commands;

import com.items.Item;
import com.organizer.Catalog;

public class ListCommand extends CatalogCommand {
    public ListCommand(Catalog catalog) {
        super(catalog);
    }

    public void runCommand()
    {
        for (var item : catalog.getCatalogItems())
        {
            System.out.println(item.textRepresentation());
        }
    }

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "List";
    }
}
