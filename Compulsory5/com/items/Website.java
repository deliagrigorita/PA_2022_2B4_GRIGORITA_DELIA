package com.items;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
//un tip de item
public class Website extends Item {
    public Website() {}

    public Website(int id, String title, String location) {
        super(id, title, location);
    }//constructorul

    public JsonNode serialize()
    {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode itemNode = mapper.createObjectNode();
        JsonNode itemContentNode = mapper.valueToTree(this);

        ((ObjectNode) itemNode).put("typeName", "website");//un nod de json la care ii adaug un atribut care are o anumita valoare si inca un atribut care are alta valoare
        ((ObjectNode) itemNode).set("value", itemContentNode);

        return itemNode;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
