package com.urlShortner.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortUrlResponse {
    private String shortUrl;
}
