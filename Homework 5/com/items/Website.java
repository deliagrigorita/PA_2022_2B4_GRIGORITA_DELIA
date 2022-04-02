package com.items;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Website extends Item {
    public Website() {}

    public Website(int id, String title, String location) {
        super(id, title, location);
    }

    public JsonNode serialize()
    {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode itemNode = mapper.createObjectNode();
        JsonNode itemContentNode = mapper.valueToTree(this);

        ((ObjectNode) itemNode).put("typeName", "website");
        ((ObjectNode) itemNode).set("value", itemContentNode);

        return itemNode;
    }

    @Override
    public String findType() { return "Website"; };

    @Override
    public String findTextRepresentation() { return textRepresentation(); }

    @Override
    public String textRepresentation()
    {
        String representation = "";
        representation += super.textRepresentation();
        representation += "type : website\n";
        return representation;
    }
}
