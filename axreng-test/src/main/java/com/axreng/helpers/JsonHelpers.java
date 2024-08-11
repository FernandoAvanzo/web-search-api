package com.axreng.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import static com.axreng.helpers.IDGenerator.generateId;

@SuppressWarnings("ALL")
public class JsonHelpers {

    private static JsonArray buildJsonArray(){
        return new JsonArray();
    }

    public static JsonObject buildJsonObject(){
        return new JsonObject();
    }

    public static String parseJsonObject(JsonObject jsonObject){
        Gson gson = new Gson();
        return gson.toJson(jsonObject);
    }

    public static String extractFieldFromJson(String json, String field) {
        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        com.google.gson.JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        return jsonObject.get(field).getAsString();
    }

    public static JsonObject buildJsonFromDocument(Document document) {
        final String id = generateId();
        final JsonObject jsonObject = buildJsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("status", "active");

        NodeList urlNodes = document.getElementsByTagName("url");
        final JsonArray urls = buildJsonArray();

        for (int i = 0; i < urlNodes.getLength(); i++) {
            Element urlElement = (Element) urlNodes.item(i);
            urls.add(urlElement.getTextContent());
        }

        jsonObject.add("urls", urls);

        return jsonObject;
    }

}