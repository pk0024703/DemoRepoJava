package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService {


    public RestTemplate restTemplate;


    public String getMyServiceData() {

        ResponseEntity response = this.restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1" , String.class);


        return "dd";
    }



    public void postData(){

    }
}
