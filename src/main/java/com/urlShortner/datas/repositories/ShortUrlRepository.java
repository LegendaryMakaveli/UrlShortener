package com.urlShortner.datas.repositories;

import com.urlShortner.datas.models.ShortUrl;
import com.urlShortner.datas.models.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShortUrlRepository extends MongoRepository<@NonNull ShortUrl, @NonNull String> {
    boolean existsByShortUrl(@NonNull String shortUrl);
    Optional<ShortUrl> findByShortUrl(@NonNull String shortUrl);
    List<ShortUrl> findByOwner(@NonNull User user);
    Long countByOwner(@NonNull User user);
}
