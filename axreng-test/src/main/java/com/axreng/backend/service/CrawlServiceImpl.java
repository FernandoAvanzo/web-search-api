package com.axreng.backend.service;

import com.axreng.backend.repositories.scrapper.ScrapperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.axreng.backend.factory.RepositoryFactory.createRepository;
import static com.axreng.helpers.JsonHelpers.extractFieldFromJson;

public class CrawlServiceImpl implements CrawlService {
    private static final Logger logger = LoggerFactory.getLogger(CrawlServiceImpl.class);
    private final ScrapperRepository scrapperRepository;

    public CrawlServiceImpl() {
        scrapperRepository = createRepository();
    }

    @Override
    public String crawl(String body) {
        try {
            final String id = extractFieldFromJson(body, "keyword");
            scrapperRepository.scrapeWebsite(id);
            return "200";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "500";
        }
    }

    @Override
    public String crawlResult(String id) {
        return scrapperRepository.scraperResult(id);
    }
}