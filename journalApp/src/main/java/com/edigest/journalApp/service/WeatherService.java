package com.edigest.journalApp.service;

import com.edigest.journalApp.apiresponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private  static final String apiKey="4128679f24a44516959105156242507";

    private static final String api="http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
            String finalApi=api.replace("CITY",city)
                    .replace("API_KEY",apiKey);
            ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
          WeatherResponse body=  response.getBody();
    return  body;
    }



}
