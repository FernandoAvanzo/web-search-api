package com.axreng.backend.service;

import com.axreng.backend.factory.ServiceFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrawlServiceTest {

    @Test
    void testCrawlServiceError() {
        CrawlService crawlService = ServiceFactory.createCrawlService();
        assertEquals(crawlService.crawl("Linux"), "500");
    }

    @Test
    void testCrawlServiceSuccess() {
        String body = "{\"keyword\": \"linux\"}";
        CrawlService crawlService = ServiceFactory.createCrawlService();
        assertEquals(crawlService.crawl(body), "200");
    }
}