package com.example.tapuurl.dto.converter;

import com.example.tapuurl.dto.ShowLinkDto;
import com.example.tapuurl.model.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlDtoConverter {

    public ShowLinkDto convertToShortLinkDto(Url from){
        return new ShowLinkDto(from.getShortUrl(), from.getOriginUrl());
    }
}
