package com.urlShortner.dtos.requests;


import lombok.Data;

@Data
public class RegisterUserRequesst {
    private String email;
    private String password;
}
