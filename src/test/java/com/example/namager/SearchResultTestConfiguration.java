package com.example.namager;

import com.example.namager.logic.AgifyDataProvider;
import com.example.namager.logic.ComparatorFactory;
import com.example.namager.logic.SearchResultLogic;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class SearchResultTestConfiguration {

    @Bean
    public SearchResultLogic getSearchResultlogic() {
        return new SearchResultLogic(comparatorFactory(), agifyDataProvider());
    }

    @Bean
    public ComparatorFactory comparatorFactory() {
        return new ComparatorFactory();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AgifyDataProvider agifyDataProvider() {
        return new AgifyDataProvider(restTemplate());
    }
}
