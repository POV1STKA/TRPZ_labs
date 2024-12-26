package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.Role;
import com.OnlineRadio.OnlineRadioStation.models.Statistic;
import com.OnlineRadio.OnlineRadioStation.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
/**
 @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OnlineRadioFacade facade;

    @Test
    public void testLogin() throws Exception {
        Role role = new Role("USER");
        User mockUser = new User("123", "testUser", "testPassword","127.0.0.1", role);

        when(facade.login("testUser", "testPassword")).thenReturn(mockUser);

        mockMvc.perform(post("/api/users/login")
                        .param("login", "testUser")
                        .param("password", "testPassword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.login").value("testUser"))
                .andExpect(jsonPath("$.ipAddress").value("127.0.0.1"))
                .andExpect(jsonPath("$.roleName").value("USER"));
    }

    @Test
    public void testRegister() throws Exception {
        when(facade.register("newUser", "newPassword", "127.0.0.1", "USER")).thenReturn(true);

        mockMvc.perform(post("/api/users/register")
                        .param("login", "newUser")
                        .param("password", "newPassword")
                        .param("ipAddress", "127.0.0.1")
                        .param("roleName", "USER"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }*/

}
