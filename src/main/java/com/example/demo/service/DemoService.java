package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class DemoService {

    @Value("${service.url}")
    private String serviceUrl;


    private RestTemplate restTemplate = new RestTemplate();

    public DemoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public String getMyServiceData(String id) {
        String result = restTemplate.getForObject(serviceUrl, String.class);
        return result;
    }

    public User postData(User user){
        return restTemplate.postForEntity(serviceUrl,user,User.class).getBody();
    }
}
