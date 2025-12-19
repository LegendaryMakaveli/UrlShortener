package com.urlShortner.Validation;

import com.urlShortner.dtos.requests.RegisterUserRequesst;
import com.urlShortner.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;


@Service
public class AuthValidations {

    public void registerValidation(RegisterUserRequesst request) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[^\\s]{6,15}$";
        if(!request.getPassword().matches(passwordPattern)) throw new InvalidCredentialsException("Invalid credentials");
        if(request.getEmail() == null || request.getPassword().trim().isEmpty());
    }
}
