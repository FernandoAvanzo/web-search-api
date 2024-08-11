package com.axreng.backend.repositories.scrapper;

public interface ScrapperRepository {

    void scrapeWebsite(String keyword) throws Exception;

    String scraperResult(String id);
}