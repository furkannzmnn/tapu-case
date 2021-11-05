package com.example.tapuurl.dto.converter;

import com.example.tapuurl.dto.request.UserSaveRequest;
import com.example.tapuurl.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserSaveRequestConverter {

    public User convertToSaveRequest(UserSaveRequest request){
        return new User(request.getUserName(),request.getPassword());
    }
}
