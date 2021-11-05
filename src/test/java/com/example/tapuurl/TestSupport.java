package com.example.tapuurl;

import com.example.tapuurl.dto.request.ShortUrlRequest;
import com.example.tapuurl.dto.request.UserSaveRequest;
import com.example.tapuurl.model.Url;
import com.example.tapuurl.model.User;

import java.util.Set;

public class TestSupport {

    public static final String USER_API_ENDPOINT = "/api/v1/users/signup";
    public static final String ACCOUNT_API_ENDPOINT = "/v1/account/";

    public ShortUrlRequest generateShortUrlRequest(){
        return new ShortUrlRequest("origin_url","short_url",1);
    }

    public Url generateUrl(String originUrl , String shortUrl , User user){
        return new Url(
                originUrl,
                shortUrl,
                user
        );
    }

    public User generateUser(){
        return new User(1,"username","password", Set.of());
    }
    public UserSaveRequest generateSaveRequest(){
        return new UserSaveRequest("password","username");
    }
}
