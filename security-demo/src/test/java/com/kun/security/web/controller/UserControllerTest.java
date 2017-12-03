package com.kun.security.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/11 23:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    
    @Autowired
    private WebApplicationContext ctx;
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("username", "kun")
                .param("age", "23")
                .param("page", "3")
                .param("size", "10")
                .param("sort", "age,DESC").param("sort", "username,ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].username").value("kun"))
                .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("kun"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    
    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(get("/users/kun")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }
    
    @Test
    public void whenCreateSuccess() throws Exception {
        Date birthday = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli());
        String content = "{\"username\":\"kun\",\"password\":null,\"birthday\":" + birthday.getTime() + "}";
        String result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("10"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    
    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt",
                        "multipart/form-data", "hello upload".getBytes("UTF-8"))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    
}
