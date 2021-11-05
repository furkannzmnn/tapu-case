package com.example.tapuurl.service;

import com.example.tapuurl.dto.Response.Constant;
import com.example.tapuurl.dto.ShowLinkDto;
import com.example.tapuurl.dto.converter.UrlDtoConverter;
import com.example.tapuurl.dto.request.ShortUrlRequest;
import com.example.tapuurl.exception.UrlNotFoundException;
import com.example.tapuurl.model.Url;
import com.example.tapuurl.model.User;
import com.example.tapuurl.repository.UrlRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final UserService userService;
    private final UrlDtoConverter urlDtoConverter;

    public UrlService(UrlRepository urlRepository, UserService userService, UrlDtoConverter urlDtoConverter) {
        this.urlRepository = urlRepository;
        this.userService = userService;
        this.urlDtoConverter = urlDtoConverter;
    }

    public String shortUrl(ShortUrlRequest request){

        User user = userService.getById(request.getUser_id());

        Url url = new Url();

        url.setOriginUrl( request.getOriginalUrl());
        url.setShortUrl(request.getShortUrl());
        url.setUsers(user);

        String urls = getString();
        url.setShortUrl(urls);
        urlRepository.save(url);
        return url.getShortUrl();
    }


    protected Url getShortUrl(String code) {
        return urlRepository.findByShortUrl(code).orElseThrow(
                () -> new UrlNotFoundException(Constant.SHORT_URL_NOT_EXIST.toString())
        );

    }
    private String getString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public HttpHeaders getUrl(String code) throws URISyntaxException {
        Url shortUrl = getShortUrl(code);
        URI uri = new URI(shortUrl.getOriginUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return httpHeaders;
    }


    public List<ShowLinkDto> showLinks(int userId){
        User user = userService.getById(userId);
        return urlRepository.findAll().stream()
                .filter(p -> p.getUsers().getId() == userId)
                .map(urlDtoConverter::convertToShortLinkDto)
                .collect(Collectors.toList());

    }

    public String deleteLinks(int id){
        Url url = urlRepository.findById(id).orElseThrow( () -> new UrlNotFoundException(Constant.URL_ID_NOT_EXIST.toString()));
        this.urlRepository.deleteById(id);
        return Constant.OBJECT_DELETED.toString();
    }

}
