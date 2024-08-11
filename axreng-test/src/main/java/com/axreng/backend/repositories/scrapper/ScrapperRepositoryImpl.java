package com.axreng.backend.repositories.scrapper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import static com.axreng.backend.searchs.SearchQueue.getInstance;


public class ScrapperRepositoryImpl implements ScrapperRepository {

    private static NodeList scraper(String keyword) {
        String urlString = "http://hiring.axreng.com/"; // Corrected here
        try {
            // Step 1: Make an HTTP request to the website
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            // Step 2: Parse the HTML content to extract relevant data
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(content.toString().getBytes()));
            doc.getDocumentElement().normalize();

            // Assuming the data we want is in <p> tags or similar
            return doc.getElementsByTagName("p");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void scrapeWebsite(String keyword) {
        NodeList nodeList = scraper(keyword);
        for (int i = 0; i < Objects.requireNonNull(nodeList).getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String textContent = element.getTextContent();
                if (textContent.contains(keyword)) {
                    getInstance().put(textContent);
                }
            }
        }
    }

    @Override
    public String scraperResult(String id) {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (String data : getInstance().queueOfSearchs()) {
            jsonArray.add(data);
        }
        json.add("data", jsonArray);

        return gson.toJson(json);
    }
}
