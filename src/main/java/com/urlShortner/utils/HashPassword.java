package com.urlShortner.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HashPassword {
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(4));
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
