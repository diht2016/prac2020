package ru.sbt.mipt.services.weather;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class WeatherControllerTest {
    private final String testBody = "{\"currently\": {\"temperature\": 15.8}}";
    private final String expectedResponse = "15.8";
    private final String serviceRequestURL = "https://api.darksky.net/forecast/eba0aac253297ed6ba6c6b20ecfe2f78/55.751244,37.618423?units=auto";

    @Test
    public void mockWeatherServiceTest() {
        WeatherService serviceMock = Mockito.mock(WeatherService.class);
        Mockito.when(serviceMock.getWeatherData()).thenReturn(expectedResponse);

        assertEquals(expectedResponse, new WeatherController(serviceMock).getCurrentTemperature());
    }

    @Test
    public void mockRestTemplateTest() {
        RestTemplate templateMock = Mockito.mock(RestTemplate.class);
        ResponseEntity responseMock = Mockito.mock(ResponseEntity.class);
        Mockito.when(templateMock.getForEntity(serviceRequestURL, String.class)).thenReturn(responseMock);
        Mockito.when(responseMock.getBody()).thenReturn(testBody);

        Mockito.inOrder(templateMock, responseMock);

        assertEquals(expectedResponse, new WeatherController(new WeatherService(templateMock)).getCurrentTemperature());
    }

}
