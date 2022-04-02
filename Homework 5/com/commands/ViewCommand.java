package com.commands;

import com.exceptions.CommandException;
import com.items.Item;
import com.organizer.Catalog;

import java.awt.*;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Paths;

public class ViewCommand extends CatalogCommand {

    private String itemPath;

    public ViewCommand(Catalog catalog, String itemPath) {
        super(catalog);
        this.itemPath = itemPath;
    }

    public void runCommand(int id) throws CommandException
    {
        try {
            dumpToFile(catalog.getItemById(id));
        }
        catch (Exception exception) {
            System.out.println(exception);
            return;
        }

        try {
            Desktop desktop = Desktop.getDesktop();

            desktop.open(Paths.get(this.itemPath).toFile());
        }
        catch (Exception exception) {
            System.out.println(exception);
            throw new CommandException("Exception opening viewer command problem", exception);
        }
    }

    private void dumpToFile(Item item) throws Exception
    {
        Writer fileWriter = new FileWriter(itemPath);
        fileWriter.write(item.textRepresentation());
        fileWriter.close();
    }

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "View";
    }
}
