package com.axreng.backend.service;

import com.axreng.backend.repositories.scrapper.ScrapperRepository;

import static com.axreng.backend.factory.RepositoryFactory.createRepository;

public class CrawlServiceImpl implements CrawlService {

    private final ScrapperRepository scrapperRepository;

    public CrawlServiceImpl() {
        scrapperRepository = createRepository();
    }

    @Override
    public String crawl(String keyword) {
        scrapperRepository.scrapeWebsite(keyword);
        return "200";
    }

    @Override
    public String crawlResult(String id) {
        return scrapperRepository.scraperResult(id);
    }
}

