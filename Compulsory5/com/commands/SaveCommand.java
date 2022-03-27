package com.commands;

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

    public void runCommand(String pathToSave) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createArrayNode();

        for (var item : catalog.getCatalogItems())
        {
            ((ArrayNode) root).add(item.serialize());
        }

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());//instantiaza obiectul 'writer'

        writer.writeValue(Paths.get(pathToSave).toFile(), root);//se salveaza arborele json in format text
    }

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "Load";
    }

}
