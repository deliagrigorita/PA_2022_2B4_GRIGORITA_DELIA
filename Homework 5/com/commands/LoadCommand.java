package com.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.items.Book;
import com.organizer.Catalog;

import java.nio.file.Paths;

public class LoadCommand extends CatalogCommand{

    public LoadCommand(Catalog catalog) {
        super(catalog);
    }

    public void runCommand(String pathToSave) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(Paths.get(pathToSave).toFile());

        if (root.isArray())
        {
            for (var node : ((ArrayNode) root))
            {
                String typeName = ((ObjectNode) node).path("typeName").textValue();
                if (typeName.equals("book"))
                {
                    Book book = mapper.treeToValue(node.path("value"), Book.class);
                    this.catalog.addItem(book);
                }
            }
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
