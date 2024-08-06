package com.axreng.backend.service;

import com.axreng.backend.factory.ServiceFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrawlServiceTest {


    @Test
    void crawl() {
        CrawlService crawlService = ServiceFactory.createCrawlService();;
        assertDoesNotThrow(() -> crawlService.crawl("Linux"));
    }
}