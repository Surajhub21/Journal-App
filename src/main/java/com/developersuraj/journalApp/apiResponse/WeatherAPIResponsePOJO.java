package com.developersuraj.journalApp.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
public class WeatherAPIResponsePOJO {
    //Plain Old Java Object
    private Current current;


    @Data
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        private ArrayList<String> weather_icons;
        private ArrayList<String> weather_descriptions;
        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private int humidity;
        private int cloudcover;
        private int feelslike;
    }

}

