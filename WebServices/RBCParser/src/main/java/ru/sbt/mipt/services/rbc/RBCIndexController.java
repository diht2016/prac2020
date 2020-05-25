package ru.sbt.mipt.services.rbc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RBCIndexController {

    private final RBCService service;

    //public RBCIndexController() {
    //    this.service = new RBCService();
    //}

    public RBCIndexController(RBCService service) {
        this.service = service;
    }

    @RequestMapping("/rbc/")
    public String getMaxIndex() {
        //return "Greetings from Spring Boot!";

        try {
            return service.getUSDData();
        } catch (RuntimeException e) {
            return "Server is not available";
        }
    }


}