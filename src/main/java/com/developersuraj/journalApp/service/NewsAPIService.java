package com.developersuraj.journalApp.service;

import com.developersuraj.journalApp.apiResponse.NewsApiPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NewsAPIService {
    private final String newsApiKey = "0b61a34e7dda4fefa80b4e99b9bdd9a6";

    private final String BASE_URL = "https://newsapi.org/v2/top-headlines";

    @Autowired
    private RestTemplate restTemplate;

    public NewsApiPOJO getNews() {
        // Build the URL with query parameters
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("country", "us")
                .queryParam("page", 1)
                .queryParam("apiKey", newsApiKey)
                .toUriString();

        // Make the GET request
        ResponseEntity<NewsApiPOJO> response = restTemplate.exchange(url, HttpMethod.GET, null, NewsApiPOJO.class);

        return response.getBody();
    }

}
