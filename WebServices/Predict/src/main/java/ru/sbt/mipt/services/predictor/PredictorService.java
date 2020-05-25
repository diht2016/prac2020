package ru.sbt.mipt.services.predictor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictorService {

    private final RestTemplate restTemplate;

    public PredictorService() {
        this.restTemplate = new RestTemplate();
    }

    public PredictorService(RestTemplate service) {
        this.restTemplate = service;
    }

    public String getPrediction() {
        String urlR = "http://rbc:8080/rbc/";
        String urlW = "http://weather:8080/weather/";

        ResponseEntity<String> responseR = restTemplate.getForEntity(urlR, String.class);
        ResponseEntity<String> responseW = restTemplate.getForEntity(urlW, String.class);

        return computePrediction(responseR.getBody(), responseW.getBody());
    }

    public String computePrediction(String text1, String text2) {
        return "somePrediction(" + text1 + ", " + text2 + ") = " +
                Double.parseDouble(text1) * (Double.parseDouble(text2) + 50) / 60;
    }
}
