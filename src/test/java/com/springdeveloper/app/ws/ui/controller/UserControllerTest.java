package com.springdeveloper.app.ws.ui.controller;

import com.springdeveloper.app.ws.service.impl.UserServiceImpl;
import com.springdeveloper.app.ws.shared.dto.UserDto;
import com.springdeveloper.app.ws.ui.model.response.UserRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    UserDto userDto;

    final String userId = "fdwefewf433r";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDto = new UserDto();
        userDto.setFirstName("Surya");
        userDto.setLastName("Telikepalli");
        userDto.setEncryptedPassword("3h3rh4354ufh");
        userDto.setEmail("surya@test.com");
        userDto.setUserId(userId);
    }

    @Test
    void testGetUser() {
        when(userService.getUserByUserId(anyString())).thenReturn(userDto);

        UserRest userRest = userController.getUser(userId);
        assertNotNull(userRest);
        assertEquals(userId,userRest.getUserId() );
        assertEquals(userDto.getFirstName(), userRest.getFirstName());
        assertEquals(userDto.getLastName(), userRest.getLastName());
    }
}