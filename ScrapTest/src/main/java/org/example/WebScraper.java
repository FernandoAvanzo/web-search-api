package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    public static String scrapeWebsite(String keyword) {
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
            NodeList nodeList = doc.getElementsByTagName("p");

            List<String> matchingData = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String textContent = element.getTextContent();
                    if (textContent.contains(keyword)) {
                        matchingData.add(textContent);
                    }
                }
            }

            // Step 3: Convert the filtered data into JSON format using Gson
            Gson gson = new Gson();
            JsonObject json = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            for (String data : matchingData) {
                jsonArray.add(data);
            }
            json.add("data", jsonArray);

            return gson.toJson(json);

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
