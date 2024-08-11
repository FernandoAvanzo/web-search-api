package com.axreng.backend.service;

public interface CrawlService {
    void crawl(String keyword);
    String crawlResult(String id);
}
