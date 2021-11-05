package com.example.tapuurl.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserSaveRequest implements Serializable {

    @NotBlank(message = "password not be empty")
    private String password;

    @NotBlank(message = "username not be empty")
    private String userName;
}
