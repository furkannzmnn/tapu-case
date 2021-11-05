package com.example.tapuurl.controller;

import com.example.tapuurl.dto.Response.ResponseApi;
import com.example.tapuurl.dto.request.UserSaveRequest;
import com.example.tapuurl.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<ResponseApi> signUp(@RequestBody @Valid UserSaveRequest userSaveRequest){
        return ResponseEntity.ok(
               ResponseApi.builder()
                       .timeStamp(now())
                       .message("SIGN UP SUCCESSFULLY")
                       .httpStatus(HttpStatus.CREATED)
                       .statusCode(201)
                       .data(Map.of("object",userService.signUp(userSaveRequest)))
                       .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(
                this.userService.list()
        );
    }



}
