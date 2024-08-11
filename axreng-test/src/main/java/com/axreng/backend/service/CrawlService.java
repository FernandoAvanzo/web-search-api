package com.axreng.backend.service;

public interface CrawlService {
    String crawl(String body);
    String crawlResult(String id);
}