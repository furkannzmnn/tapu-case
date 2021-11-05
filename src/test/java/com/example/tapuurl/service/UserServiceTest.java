package com.example.tapuurl.service;

import com.example.tapuurl.TestSupport;
import com.example.tapuurl.dto.UserDto;
import com.example.tapuurl.dto.converter.UrlDtoConverter;
import com.example.tapuurl.dto.converter.UserDtoConverter;
import com.example.tapuurl.dto.converter.UserSaveRequestConverter;
import com.example.tapuurl.dto.request.UserSaveRequest;
import com.example.tapuurl.exception.UserNotFoundException;
import com.example.tapuurl.model.User;
import com.example.tapuurl.repository.UrlRepository;
import com.example.tapuurl.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest extends TestSupport {

    private  UserRepository userRepository;
    private  UserDtoConverter userDtoConverter;
    private  UserSaveRequestConverter userSaveRequestConverter;
    private UserService userService;

    @BeforeEach
    void setUp() {
       userRepository = mock(UserRepository.class);
       userDtoConverter = mock(UserDtoConverter.class);
       userSaveRequestConverter = mock(UserSaveRequestConverter.class);
       userService = new UserService(userRepository,userDtoConverter,userSaveRequestConverter);
    }

    @Test
    void signUp() {

        User user = generateUser();
        UserSaveRequest saveRequest = new UserSaveRequest(user.getPassword(),user.getUserName());
        UserDto expected = new UserDto(user.getId());


        Mockito.when(userRepository.save(userSaveRequestConverter.convertToSaveRequest(saveRequest))).thenReturn(user);
        Mockito.when(userDtoConverter.convertToUserDto(user)).thenReturn(expected);

        UserDto result = userService.signUp(saveRequest);
        assertEquals(expected,result);

    }

    @Test
    void list() {

        User user = generateUser();
        UserDto expected = new UserDto(user.getId());

        Mockito.when(userDtoConverter.convertToUserDto(user)).thenReturn(expected);
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserDto> result = userService.list();

        assertEquals(result,List.of(expected));
    }

    @Test
    void getById() {
        User user = generateUser();

        Mockito.when(userRepository.getById(user.getId())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                ()-> userService.getById(user.getId()));
    }
}