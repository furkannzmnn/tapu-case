package com.example.tapuurl.dto.converter;

import com.example.tapuurl.dto.UserDto;
import com.example.tapuurl.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class UserDtoConverter {

    public UserDto convertToUserDto(User from){
        return new UserDto(from.getId());
    }

}
