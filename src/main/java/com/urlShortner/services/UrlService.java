package com.urlShortner.services;

import com.urlShortner.dtos.requests.ShortUrlRequest;
import com.urlShortner.dtos.responses.ShortUrlResponse;

public interface UrlService {
    ShortUrlResponse shortenUrl(ShortUrlRequest request);
    String getLongUrlByShortCode(String shortCode);
}
