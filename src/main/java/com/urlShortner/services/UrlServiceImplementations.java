package com.urlShortner.services;

import com.urlShortner.datas.models.ShortUrl;
import com.urlShortner.datas.models.Subscription;
import com.urlShortner.datas.models.User;
import com.urlShortner.datas.repositories.ShortUrlRepository;
import com.urlShortner.datas.repositories.UserRepository;
import com.urlShortner.dtos.requests.ShortUrlRequest;
import com.urlShortner.dtos.responses.ShortUrlResponse;
import com.urlShortner.exceptions.UrlLimitExceededException;
import com.urlShortner.utils.ShortCodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UrlServiceImplementations implements UrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShortCodeGenerator shortCodeGenerator;



    @Override
    public ShortUrlResponse shortenUrl(ShortUrlRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user.getSubscription() == Subscription.FREE && user.getUrlCount() >= 2) throw new UrlLimitExceededException("Upgrade to premium to shorten more URLs");
        String shortCode;
        do {
            shortCode = shortCodeGenerator.generate();
        } while (shortUrlRepository.existsByShortUrl(shortCode));

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(request.getLongUrl());
        shortUrl.setShortUrl(shortCode);
        shortUrl.setOwner(user.getEmail());
        shortUrlRepository.save(shortUrl);

        user.setUrlCount(user.getUrlCount() + 1);
        userRepository.save(user);

        return new ShortUrlResponse(shortCode);
    }

    public String getLongUrlByShortCode(String shortCode) {
        ShortUrl shortUrl = shortUrlRepository.findByShortUrl(shortCode)
                .orElse(null);
        return (shortUrl != null) ? shortUrl.getLongUrl() : null;
    }

}
