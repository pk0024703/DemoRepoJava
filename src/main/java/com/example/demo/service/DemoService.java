package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService {

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<User> getMyServiceData() {
        final String uri = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<User> result = restTemplate.getForEntity(uri, User.class);
        return result;
    }

    public ResponseEntity<User> postData(User user){
        final String uri = "https://jsonplaceholder.typicode.com/posts/";
        return restTemplate.postForEntity(uri,user,User.class);
    }
}
