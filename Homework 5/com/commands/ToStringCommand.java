package com.commands;

import com.items.Item;
import com.organizer.Catalog;

public class ToStringCommand extends CatalogCommand {

    public ToStringCommand(Catalog catalog) {
        super(catalog);
    }

    public String runCommand()
    {
        return catalog.toString();
    }

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "Load";
    }
}
