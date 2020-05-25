package ru.sbt.mipt.services.predictor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictorController {

    private final PredictorService service;

    public PredictorController(PredictorService service) {
        this.service = service;
    }

    @RequestMapping("/predict/")
    public String getMaxIndex() {
        //return "Greetings from Spring Boot!";

        try {
            return service.getPrediction();
        } catch (RuntimeException e) {
            return "Server is not available";
        }
    }


}