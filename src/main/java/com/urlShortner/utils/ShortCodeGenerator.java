package com.urlShortner.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class ShortCodeGenerator {

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int CODE_LENGTH = 5;
    private static final Random random = new SecureRandom();

    public static String generate() {
        StringBuilder code = new StringBuilder();

        for (int count = 0; count < CODE_LENGTH; count++) {
            code.append(CHARACTERS.charAt(
                    random.nextInt(CHARACTERS.length())));
        }
        return "maka" + code.toString();
    }
}
