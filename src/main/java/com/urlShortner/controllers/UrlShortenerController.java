package com.urlShortner.controllers;


import com.urlShortner.dtos.requests.ShortUrlRequest;
import com.urlShortner.dtos.responses.ApiResponse;
import com.urlShortner.exceptions.ShortUrlException;
import com.urlShortner.exceptions.UrlLimitExceededException;
import com.urlShortner.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url")
public class UrlShortenerController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shortenUrl")
    public ResponseEntity<?> shortenUrl(@RequestBody ShortUrlRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse(true, urlService.shortenUrl(request)),HttpStatus.OK);

        } catch (UrlLimitExceededException error){
            return new ResponseEntity<>(new ApiResponse(false,error.getMessage()),HttpStatus.BAD_REQUEST);
        }catch (ShortUrlException error) {
            return new ResponseEntity<>(new ApiResponse(false, error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable("shortCode") String shortCode) {
        String longUrl = urlService.getLongUrlByShortCode(shortCode);
        if (longUrl == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(longUrl));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}

