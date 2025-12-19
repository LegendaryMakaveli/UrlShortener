package com.urlShortner.dtos.responses;


import lombok.Data;

@Data
public class RegisterUserResponse {
    private String email;
    private String message;
    private String registerAt;
}
