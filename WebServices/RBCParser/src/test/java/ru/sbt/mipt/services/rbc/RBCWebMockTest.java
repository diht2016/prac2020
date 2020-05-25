package ru.sbt.mipt.services.rbc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RBCIndexController.class)
public class RBCWebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RBCService serviceMock;

    /*
    @Test
    public void testResponse() throws Exception {
        assertThat(new RestTemplate().getForObject("http://localhost:8080/",
                String.class)).contains(expectedResponse);
    }
    */

    @Test
    public void webResultTest() throws Exception {
        String expectedResponse = "63.7946";

        when(serviceMock.getUSDData()).thenReturn(expectedResponse);
        this.mockMvc
                .perform(get("/rbc/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedResponse)));
    }
}