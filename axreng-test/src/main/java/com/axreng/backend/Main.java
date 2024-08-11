package com.axreng.backend;

import com.axreng.backend.service.CrawlService;

import static spark.Spark.*;
import static com.axreng.backend.factory.ServiceFactory.createCrawlService;

public class Main {
    public static void main(String[] args) {
        CrawlService crawlService = createCrawlService();
        get("/crawl/:id", (req, res) ->
                "GET /crawl/" + req.params("id"));
        post("/crawl", (req, res) ->
                crawlService.crawl(req.body()));
    }
}
