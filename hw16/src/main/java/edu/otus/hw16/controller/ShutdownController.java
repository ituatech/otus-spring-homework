package edu.otus.hw16.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Nik Bespalov on 18/10/2018.
 *
 * @author Nik Bespalov.
 */
@Controller
public class ShutdownController {

    @ResponseBody
    @RequestMapping(path = "/shutdown")
    public String callActuatorShutdown() {

        String url = "http://localhost:8080/actuator/shutdown";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestBody = new HttpEntity<>("", headers);

        String e = restTemplate.postForObject(url, requestBody, String.class);

        return "Result: " + e;
    }

}
