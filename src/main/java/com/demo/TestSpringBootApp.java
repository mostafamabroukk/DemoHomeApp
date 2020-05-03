package com.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSpringBootApp {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate;
    public TestSpringBootApp(){
        restTemplate = new RestTemplate();
    }

    @Test
    public void getAllPayments() throws URISyntaxException {
        String baseUrl = "http://localhost:"+port+"/payments";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        String expectedStr = "";
        Assert.assertEquals(expectedStr, result.getBody());
    }

    @Test
    public void getSumOfPayments() throws URISyntaxException {
        int contractId = 17689;
        String baseUrl = "http://localhost:"+port+"/sumOfPayments/"+contractId;
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        System.out.println(result.getBody());
    }
}