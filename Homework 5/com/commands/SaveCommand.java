package com.commands;

import com.exceptions.CommandException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.organizer.Catalog;

import java.lang.reflect.Array;
import java.nio.file.Paths;

public class SaveCommand extends CatalogCommand{

    public SaveCommand(Catalog catalog) {
        super(catalog);
    }

    public void runCommand(String pathToSave) throws CommandException
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createArrayNode();

        for (var item : catalog.getCatalogItems())
        {
            ((ArrayNode) root).add(item.serialize());
        }

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(Paths.get(pathToSave).toFile(), root);
        }
        catch (Exception exception)
        {
            System.out.println(exception);
            throw new CommandException("Save command problem", exception);
        }
    }

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "Load";
    }

}
