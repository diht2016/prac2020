package ru.sbt.mipt.services.weather;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherWebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService serviceMock;

    /*
    @Test
    public void testResponse() throws Exception {
        assertThat(new RestTemplate().getForObject("http://localhost:8080/",
                String.class)).contains(expectedResponse);
    }
    */

    @Test
    public void webResultTest() throws Exception {
        String expectedResponse = "15.85";

        when(serviceMock.getWeatherData()).thenReturn(expectedResponse);
        this.mockMvc
                .perform(get("/weather/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedResponse)));
    }
}