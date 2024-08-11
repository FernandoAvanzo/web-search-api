package com.axreng.backend.repositories.scrapper;

public interface ScrapperRepository {

    void scrapeWebsite(String keyword);

    String scraperResult(String id);
}
