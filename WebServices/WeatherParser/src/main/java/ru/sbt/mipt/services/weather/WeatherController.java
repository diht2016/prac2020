package ru.sbt.mipt.services.weather;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @RequestMapping("/weather/")
    public String getCurrentTemperature() {
        try {
            return service.getWeatherData();
        } catch (RuntimeException e) {
            return "Server is not available";
        }
    }


}