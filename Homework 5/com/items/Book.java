package com.items;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Book extends Item {
    public Book() {};

    public Book(int id, String title, String location) {
        super(id, title, location);
    }

    public JsonNode serialize()
    {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode itemNode = mapper.createObjectNode();
        JsonNode itemContentNode = mapper.valueToTree(this);

        ((ObjectNode) itemNode).put("typeName", "book");
        ((ObjectNode) itemNode).set("value", itemContentNode);

        return itemNode;
    }

    @Override
    public String findType() { return "Book"; };

    @Override
    public String findTextRepresentation() { return textRepresentation(); }

    @Override
    public String textRepresentation()
    {
        String representation = "";
        representation += super.textRepresentation();
        representation += "type : book\n";
        return representation;
    }
}
