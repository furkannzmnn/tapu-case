package com.example.tapuurl.service;

import com.example.tapuurl.dto.Response.Constant;
import com.example.tapuurl.dto.UserDto;
import com.example.tapuurl.dto.converter.UserDtoConverter;
import com.example.tapuurl.dto.converter.UserSaveRequestConverter;
import com.example.tapuurl.dto.request.UserSaveRequest;
import com.example.tapuurl.exception.UserNotFoundException;
import com.example.tapuurl.model.User;
import com.example.tapuurl.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final UserSaveRequestConverter userSaveRequestConverter;


    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, UserSaveRequestConverter userSaveRequestConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;

        this.userSaveRequestConverter = userSaveRequestConverter;
    }

    public UserDto signUp(UserSaveRequest userSaveRequest){

        User user =userSaveRequestConverter.convertToSaveRequest(userSaveRequest);
        return userDtoConverter.convertToUserDto(userRepository.save(user));


    }

    public List<UserDto> list(){
        return this.userRepository.findAll()
                .stream()
                .map(userDtoConverter::convertToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(int id){
        return userDtoConverter.convertToUserDto(getById(id));
    }
    protected User getById(int id){
        return userRepository.findById(id).orElseThrow(
                () ->  new UserNotFoundException(Constant.USER_ID_NOT_EXIST.toString()));
    }


    }




