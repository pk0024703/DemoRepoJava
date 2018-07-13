package com.example.demo.service;

import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import sun.jvm.hotspot.oops.OopUtilities;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;


@RunWith(SpringRunner.class)
@RestClientTest(DemoService.class)
//@TestPropertySource(properties = "service.url=https://jsonplaceholder.typicode.com/posts/1")
@TestPropertySource(properties = "service.url=https://jsonplaceholder.typicode.com/posts/1")
public class DemoServiceTests {

    @Autowired
    DemoService demoService;

    @Autowired
    MockRestServiceServer mockRestServiceServer;




    final String output = "{\"id\": 1 , \"name\" : \"testName\"}";

    @Test
    public void testGetMyServiceDataMethodOutput() {



        mockRestServiceServer.expect(ExpectedCount.once(), requestTo("https://jsonplaceholder.typicode.com/posts/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(output, MediaType.TEXT_PLAIN));

        String result = demoService.getMyServiceData("1");
        //Assert.assertEquals(output, result);
        mockRestServiceServer.verify();

    }




    @Test
    public void testPostDataMethod() {
        User user = new User("testUserId","testTitle","testBody","testId");
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        mockRestServiceServer.expect(requestTo("https://jsonplaceholder.typicode.com/posts/")).andExpect(method(HttpMethod.POST)).andRespond(withSuccess(output,MediaType.APPLICATION_JSON));
        User output = demoService.postData(user);

        Assert.assertEquals(1,output.getId());
        mockRestServiceServer.verify();
    }








}
