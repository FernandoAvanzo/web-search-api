package org.example;

import static org.example.WebScraper.scrapeWebsite;

public class Main {
    public static void main(String[] args) {
        String keyword = "modify";
        String result = scrapeWebsite(keyword);
        System.out.println(result);
    }
}