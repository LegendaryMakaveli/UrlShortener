package com.urlShortner.dtos.responses;


import lombok.Data;

@Data
public class LoginUserResponse {
    private String token;
    private String message;
}
