package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class DemoController {

    @Autowired
    DemoService service;

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public String getData(@PathVariable("id") String id){
        String output = service.getMyServiceData(id);
        return output;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User postData(@RequestBody User user){
        User response = service.postData(user);
        return response;
    }
}
