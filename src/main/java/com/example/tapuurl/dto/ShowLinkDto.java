package com.example.tapuurl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowLinkDto {

    private String shortUrl;
    private String origin;
}
