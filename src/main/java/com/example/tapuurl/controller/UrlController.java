package com.example.tapuurl.controller;

import com.example.tapuurl.dto.Response.ResponseApi;
import com.example.tapuurl.dto.request.ShortUrlRequest;
import com.example.tapuurl.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UrlController {
    private final UrlService urlService;

    public UrlController( UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> makeRedirect(@PathVariable String code) throws URISyntaxException {
        return new ResponseEntity<>(urlService.getUrl(code), HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/short")
    public ResponseEntity<?> shortUrl(@RequestBody ShortUrlRequest request){
        return ResponseEntity.ok(
               ResponseApi.builder()
                       .httpStatus(HttpStatus.CREATED)
                       .statusCode(201)
                       .message("ORIGIN URL CONVERTED TO SHORT URL")
                       .timeStamp(now())
                       .data(Map.of("data",urlService.shortUrl(request)))
                       .build()
        );
    }

    @GetMapping("/links/{userId}")
    public ResponseEntity<?> showLinks(@PathVariable int userId){
        return ResponseEntity.ok(
                ResponseApi.builder()
                        .httpStatus(HttpStatus.OK)
                        .statusCode(200)
                        .message("url listed")
                        .timeStamp(now())
                        .data(Map.of("data",urlService.showLinks(userId)))
                        .build()
        );
    }

    @DeleteMapping("/links/{id}")
    public ResponseEntity<?> deleteUrl(@PathVariable int id){
        return ResponseEntity.ok(
                ResponseApi.builder()
                        .httpStatus(HttpStatus.OK)
                        .statusCode(202)
                        .message("url deleted")
                        .timeStamp(now())
                        .data(Map.of("data",urlService.deleteLinks(id)))
                        .build()
        );
    }




}
