package com.edigest.journalApp.apiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    @JsonProperty("current")
    private Current current;
//    @JsonProperty("condition")
//    private Condition condition;

    @Getter
    @Setter
    public  static class Condition {
        @JsonProperty("text")
        private String text;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("code")
        private int code;

    }
    @Setter
    @Getter
    public  static class Current {

        @JsonProperty("temp_c")
        private double tempC;

        @JsonProperty("temp_f")
        private double tempF;

        @JsonProperty("is_day")
        private int isDay;

        @JsonProperty("condition")
        private Condition condition;

        @JsonProperty("wind_mph")
        private double windMph;

        @JsonProperty("wind_kph")
        private double windKph;

        @JsonProperty("wind_degree")
        private int windDegree;

        @JsonProperty("wind_dir")
        private String windDir;


        @JsonProperty("humidity")
        private int humidity;

        @JsonProperty("cloud")
        private int cloud;

        @JsonProperty("feelslike_c")
        private double feelsLikeC;

        @JsonProperty("feelslike_f")
        private double feelsLikeF;

        @JsonProperty("windchill_c")
        private double windChillC;

        @JsonProperty("windchill_f")
        private double windChillF;


    }
}
