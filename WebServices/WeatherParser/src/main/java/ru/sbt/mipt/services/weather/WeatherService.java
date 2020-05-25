package ru.sbt.mipt.services.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    public WeatherService(RestTemplate service) {
        this.restTemplate = service;
    }

    public String getWeatherData() {
        String url = "https://api.darksky.net/forecast/eba0aac253297ed6ba6c6b20ecfe2f78/55.751244,37.618423?units=auto";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return Double.toString(root.get("currently").get("temperature").asDouble());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
