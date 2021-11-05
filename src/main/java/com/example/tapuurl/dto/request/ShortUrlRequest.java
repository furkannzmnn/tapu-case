package com.example.tapuurl.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ShortUrlRequest {

    private String originalUrl;

    private String shortUrl;

    private int user_id;
}
