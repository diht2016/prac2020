package ru.sbt.mipt.services.rbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RBCService {

    @Autowired
    private DBRepo repo;

    private final RestTemplate restTemplate;

    public RBCService() {
        this.restTemplate = new RestTemplate();
    }

    public RBCService(RestTemplate service) {
        this.restTemplate = service;
    }

    public String getUSDData() {
        String result = getFromDB();
        if (result != null) {
            return result;
        }

        try {
            String url = "http://export.rbc.ru/free/selt.0/free.fcgi?" +
                    "period=DAILY&tickers=USD000000TOD&lastdays=30&separator=TAB&data_format=BROWSER";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            String body = response.getBody().trim();
            result = Double.toString(computeMaxIndex(body));
        } catch (RuntimeException e) {
            result = "70.0";
        }
        addToDB(result);

        return result;
    }

    private String getFromDB() {
        try {
            String currDate = getCurrDate();
            Optional<DBItem> item = repo.findByDate(currDate);
            return item.map(dbItem -> dbItem.getCurrency().toString()).orElse(null);
        } catch (RuntimeException e) {
            System.out.println("DB - failed to get");
            return null;
        }
    }

    private void addToDB(String text) {
        try {
            String currDate = getCurrDate();
            DBItem line = new DBItem(currDate, Double.parseDouble(text));
            repo.save(line);
        } catch (RuntimeException e) {
            System.out.println("DB - failed to put");
        }
    }

    private String getCurrDate() {
        return java.time.LocalDate.now().toString();
    }

    public double computeMaxIndex(String text) {
        String[] lines = text.split("\n");
        double max = Double.NEGATIVE_INFINITY;
        for (String line : lines) {
            String[] parts = line.split("\\s");
            double n = Double.parseDouble(parts[parts.length - 1]);
            if (max < n) {
                max = n;
            }
        }
        return max;
    }
}
