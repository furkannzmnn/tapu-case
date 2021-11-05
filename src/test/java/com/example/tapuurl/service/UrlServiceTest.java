package com.example.tapuurl.service;

import com.example.tapuurl.TestSupport;
import com.example.tapuurl.dto.ShowLinkDto;
import com.example.tapuurl.dto.converter.UrlDtoConverter;
import com.example.tapuurl.dto.request.ShortUrlRequest;
import com.example.tapuurl.exception.UrlNotFoundException;
import com.example.tapuurl.exception.UserNotFoundException;
import com.example.tapuurl.model.Url;
import com.example.tapuurl.model.User;
import com.example.tapuurl.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UrlServiceTest extends TestSupport {

    private UrlRepository urlRepository;
    private UserService userService;
    private UrlDtoConverter urlDtoConverter;
    private UrlService urlService;

    @BeforeEach
    void setUp() {
        urlRepository = mock(UrlRepository.class);
        userService = mock(UserService.class);
        urlDtoConverter = mock(UrlDtoConverter.class);
        urlService = new UrlService(urlRepository,userService,urlDtoConverter);
    }

    @Test
    void shortUrl() {

        ShortUrlRequest request = generateShortUrlRequest();
        Url url = generateUrl("origin","",null);

        Mockito.when(urlRepository.save(url)).thenReturn(url);
        Mockito.when(userService.getById(request.getUser_id())).thenThrow(UserNotFoundException.class);


        assertThrows(UserNotFoundException.class,
                ()-> urlService.shortUrl(request));
    }

    @Test
    void getShortUrl() {

        User user = generateUser();
        Url url = generateUrl("originUrl", "short_url",user);

        Mockito.when(urlRepository.findByShortUrl(url.getShortUrl())).thenReturn(Optional.of(url));

        Url result = urlService.getShortUrl(url.getShortUrl());
        assertEquals(url,result);
    }

    @Test
    void getUrl() throws URISyntaxException {
        User user = generateUser();
        Url url = generateUrl("original","short", user);

        URI uri = new URI(url.getOriginUrl());

        HttpHeaders actual = new HttpHeaders();
        actual.setLocation(uri);
        Mockito.when(urlRepository.findByShortUrl(url.getShortUrl())).thenReturn(Optional.of(url));

        HttpHeaders result = urlService.getUrl(url.getShortUrl());
        assertEquals(actual,result);
    }

    @Test
    void showLinks() {
        User user = generateUser();
        Url url = generateUrl("origin","short",user);
        ShowLinkDto showLinkDto = new ShowLinkDto(url.getShortUrl(),url.getOriginUrl());

        Mockito.when(urlRepository.findAll()).thenReturn(List.of(url));
        Mockito.when(urlDtoConverter.convertToShortLinkDto(url)).thenReturn(showLinkDto);

        List<ShowLinkDto> result = urlService.showLinks(user.getId());
        assertEquals(List.of(showLinkDto),result);
    }
}