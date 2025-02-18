package com.developersuraj.journalApp.service;

import com.developersuraj.journalApp.apiResponse.WeatherAPIResponsePOJO;
import com.developersuraj.journalApp.controller.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherAPIService {

    private static final String weatherApiKey = "14d427ea169f0099166705e942499174";
    private static final String api = "https://api.weatherstack.com/current?access_key=<API_KEY>&query=<CITY>";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;


    public WeatherAPIResponsePOJO getWeather(String city){

        WeatherAPIResponsePOJO responsePOJO = redisService.get( city, WeatherAPIResponsePOJO.class);

        if(responsePOJO != null){
            return responsePOJO;
        }

        else {
            String URL = api.replace("<CITY>" , city).replace("<API_KEY>" , weatherApiKey);

            ResponseEntity<WeatherAPIResponsePOJO> response = restTemplate.exchange(URL, HttpMethod.GET, null, WeatherAPIResponsePOJO.class);//Deserialize

            WeatherAPIResponsePOJO body = response.getBody();

            if(body != null){
                redisService.set( city , body , 300L);
            }
            return  body;
        }
    }

}
