package com.commands;

import com.organizer.Catalog;

public abstract class CatalogCommand implements Command {
    protected Catalog catalog;

    public CatalogCommand(Catalog catalog) {
        this.catalog = catalog;
    }
}
