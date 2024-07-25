package com.edigest.journalApp.service;

import com.edigest.journalApp.apiresponse.WeatherResponse;
import com.edigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private  String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
            String finalApi=appCache.APP_CACHE.get("weather_api").replace("CITY",city)
                    .replace("API_KEY",apiKey);
            ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
          WeatherResponse body=  response.getBody();
    return  body;
    }



}
