package com.axreng.backend.factory;

import com.axreng.backend.repositories.scrapper.ScrapperRepository;
import com.axreng.backend.repositories.scrapper.ScrapperRepositoryImpl;

public class RepositoryFactory {

    public static ScrapperRepository createRepository() {
        return new ScrapperRepositoryImpl();
    }
    
}
