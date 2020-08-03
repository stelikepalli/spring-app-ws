package com.springdeveloper.app.ws.service.impl;

import com.springdeveloper.app.ws.exceptions.UserServiceException;
import com.springdeveloper.app.ws.io.entity.UserEntity;
import com.springdeveloper.app.ws.io.repositories.UserRepository;
import com.springdeveloper.app.ws.shared.Utils;
import com.springdeveloper.app.ws.shared.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String userId = "fdwefewf433r";
    String encryptedPassword = "3h3rh4354ufh";
    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Surya");
        userEntity.setLastName("Telikepalli");
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword(encryptedPassword);
        userEntity.setEmail("surya@test.com");
    }

    @Test
    void testGetUser() {


        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserDto userDto = userService.getUser("test@test.com");

        assertNotNull(userDto);
        assertEquals("Surya", userDto.getFirstName());
    }

    @Test
    void testGetUser_UsernameNotFoundException() {

        when(userRepository.findByEmail(anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class,
                () -> {
                    userService.getUser("surya@test.com");
                });
    }

    @Test
    final void testCreateUser_CreateUserServiceException(){
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        UserDto userDto = new UserDto();
        userDto.setFirstName("Surya");
        userDto.setLastName("Telikepalli");
        userDto.setPassword("12342356");
        userDto.setEmail("surya@test.com");

        assertThrows(UserServiceException.class,
                () -> {
                    userService.createUser(userDto);
                });
    }
    @Test
    void testCreateUser(){

        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateUserId(anyInt())).thenReturn(userId);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDto userDto = new UserDto();
        userDto.setFirstName("Surya");
        userDto.setLastName("Telikepalli");
        userDto.setPassword("12342356");
        userDto.setEmail("surya@test.com");
        UserDto storedUserDetails = userService.createUser(userDto);
        assertNotNull(storedUserDetails);
        assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
        assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
        assertNotNull(storedUserDetails.getUserId());
        verify(bCryptPasswordEncoder, times(1)).encode("12342356");
        verify(userRepository,times(1)).save(any(UserEntity.class));
    }
}