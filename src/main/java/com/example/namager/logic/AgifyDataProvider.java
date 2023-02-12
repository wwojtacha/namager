package com.example.namager.logic;

import com.example.namager.model.AgifyModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgifyDataProvider {

    private final RestTemplate restTemplate;

    public AgifyDataProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AgifyModel getAgeResultByName(String name) {
        String baseUrl = "https://api.agify.io?name=";
        String url = baseUrl + name;

        AgifyModel response;
        try {
            response = restTemplate.getForObject(url, AgifyModel.class);
        } catch (Exception e) {
            response = new AgifyModel();
        }

        return response;
    }

}
