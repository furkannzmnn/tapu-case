package com.example.tapuurl.dto.Response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseApi {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus httpStatus;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;
}

