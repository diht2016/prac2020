package ru.sbt.mipt.services.predictor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictorRemoteService {

    private final RestTemplate restTemplate;

    public PredictorRemoteService() {
        this.restTemplate = new RestTemplate();
    }

    public PredictorRemoteService(RestTemplate service) {
        this.restTemplate = service;
    }

    public String getPrediction() {
        String urlIndex = "http://localhost:8080/rbc/";
        String urlWeather = "http://localhost:8080/weather/";

        ResponseEntity<String> responseIndex = restTemplate.getForEntity(urlIndex, String.class);
        String bodyIndex = responseIndex.getBody();
        ResponseEntity<String> responseWeather = restTemplate.getForEntity(urlWeather, String.class);
        String bodyWeather = responseWeather.getBody();

        return computePrediction(bodyIndex, bodyWeather);
    }

    public String computePrediction(String text1, String text2) {
        return "somePrediction(" + text1 + ", " + text2 + ") = " +
                Double.parseDouble(text1) * (Double.parseDouble(text2) + 50) / 60;
    }
}
