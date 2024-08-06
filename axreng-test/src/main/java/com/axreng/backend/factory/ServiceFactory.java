package com.axreng.backend.factory;

import com.axreng.backend.service.CrawlService;
import com.axreng.backend.service.CrawlServiceImpl;

public class ServiceFactory {

    public static CrawlService createCrawlService() {
        return new CrawlServiceImpl();
    }

}
