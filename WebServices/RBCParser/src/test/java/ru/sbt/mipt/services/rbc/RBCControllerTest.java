package ru.sbt.mipt.services.rbc;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class RBCControllerTest {
    private final String testBody = "USD000000TOD\t2019-07-05\t63.4425\t63.8\t63.3975\t63.79\t849731000\t63.5605\n" +
            "USD000000TOD\t2019-07-08\t63.9\t63.9875\t63.5675\t63.6375\t575008000\t63.7946\n" +
            "USD000000TOD\t2019-07-09\t63.7\t63.905\t63.6725\t63.88\t621468000\t63.7913\n" +
            "USD000000TOD\t2019-07-10\t63.86\t63.9025\t63.225\t63.27\t762383000\t63.611\n";
    private final String expectedResponse = "63.7946";
    private final double expectedNumber = Double.parseDouble(expectedResponse);

    //@Test
    //public void maxComputationTest() {
    //    assertEquals(expectedNumber, new RBCService().computeMaxIndex(testBody), 1e-9);
    //}

    @Test
    public void mockRBCServiceTest() {
        RBCService serviceMock = Mockito.mock(RBCService.class);
        Mockito.when(serviceMock.getUSDData()).thenReturn(expectedResponse);

        assertEquals(expectedResponse, new RBCIndexController(serviceMock).getMaxIndex());
    }

    @Test
    public void mockRestTemplateTest() {
        RestTemplate templateMock = Mockito.mock(RestTemplate.class);
        ResponseEntity responseMock = Mockito.mock(ResponseEntity.class);
        Mockito.when(templateMock.getForEntity(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(responseMock);
        Mockito.when(responseMock.getBody()).thenReturn(testBody);

        Mockito.inOrder(templateMock, responseMock);

        assertEquals(expectedResponse, new RBCIndexController(new RBCService(templateMock)).getMaxIndex());
    }

}
