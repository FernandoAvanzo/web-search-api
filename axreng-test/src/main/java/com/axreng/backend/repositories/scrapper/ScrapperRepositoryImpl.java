package com.axreng.backend.repositories.scrapper;

import com.google.gson.JsonObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Objects;

import static com.axreng.backend.searchs.SearchQueue.getInstance;
import static com.axreng.helpers.JsonHelpers.buildJsonFromDocument;
import static com.axreng.helpers.JsonHelpers.parseJsonObject;
import static com.axreng.helpers.WebDocument.loadDocument;

public class ScrapperRepositoryImpl implements ScrapperRepository {

    @Override
    public void scrapeWebsite(String keyword) throws Exception {
        final Document document = loadDocument();
        document.getDocumentElement().normalize();
        final NodeList nodeList = document.getElementsByTagName("p");
        for (int i = 0; i < Objects.requireNonNull(nodeList).getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String textContent = element.getTextContent();
                if (textContent.contains(keyword)) {
                    getInstance().put(buildJsonFromDocument(document));
                }
            }
        }
    }

    @Override
    public String scraperResult (String id){
        for (JsonObject jsonObject : getInstance().queueOfSearchs()) {
            if (jsonObject.get("id").getAsString().equals(id)) {
                return parseJsonObject(jsonObject);
            }
        }
        return "404";
    }
}