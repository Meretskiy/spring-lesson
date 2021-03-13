package com.flamexander.spring.cloud.rest.template;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final RestTemplate restTemplate;
    // http://localhost:8190/rest
    // Expected: DATA FROM SERVICE: Hello
    @GetMapping
    public String getDataFromAnotherService() {
        String data = restTemplate.getForObject("http://localhost:8189/app/demo/data", String.class);

        return "DATA FROM SERVICE: " + data;
    }
}
