package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
        //service.getMyServiceData();
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postData(){
        service.postData();
        return "Success";
    }
}
