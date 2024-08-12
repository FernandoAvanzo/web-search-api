package com.axreng.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentConfig {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig.class);

    public static String getBaseUrl()  {
        final String baseUrlString = System.getenv("BASE_URL");

        if (baseUrlString == null || baseUrlString.isEmpty()) {
            logger.error("Environment variable BASE_URL is not set or is empty.");
            return "http://hiring.axreng.com/";
        }
        return baseUrlString;
    }
}