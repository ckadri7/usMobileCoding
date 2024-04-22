package com.example.usmobile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.usmobile.request.UserRequest;
import com.example.usmobile.response.UserResponse;
import com.example.usmobile.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest("cherif", "Kadri", "Cherif@usmobile.com", "password","mdn");
        UserResponse userResponse = new UserResponse("1", "cherif", "Kadri", "Cherif@usmobile.com","mdsn");

        when(userService.createUser(any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userResponse)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userResponse.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(userResponse.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userResponse.getEmail()));

        verify(userService, times(1)).createUser(any(UserRequest.class));
    }
}
