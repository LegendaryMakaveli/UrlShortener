package com.urlShortner.datas.repositories;

import com.urlShortner.datas.models.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<@NonNull User, @NonNull String> {
    Optional<User> findByEmail(@NonNull String email);
}
