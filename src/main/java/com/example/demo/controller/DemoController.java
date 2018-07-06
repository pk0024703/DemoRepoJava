package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class DemoController {

    @Autowired
    DemoService service;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getData(){
        String output = service.getMyServiceData();
        System.out.println("------>" + output);
        return output;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User postData(@RequestBody User user){
        ResponseEntity<User> response = service.postData(user);
        return response.getBody();
    }
}
