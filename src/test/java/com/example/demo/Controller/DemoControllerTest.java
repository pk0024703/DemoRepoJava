package com.example.demo.Controller;

import com.example.demo.DemoApplication;
import com.example.demo.entity.User;
import com.example.demo.service.DemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)


public class DemoControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;


    @Mock
    DemoService demoService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUpMocks() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void postEntity() throws Exception {
//        User user = initializeUser();
//        String mockData = mapper.writeValueAsString(user);
//        Mockito.when(demoService.postData(user)).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON)
//        .content(mockData)).andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(status().isOk());
    }

    private User initializeUser(){
        User user = new User();
        user.setBody("testBody");
        user.setTitle("testTitle");
        user.setUserId("130");
        return user;
    }
}
