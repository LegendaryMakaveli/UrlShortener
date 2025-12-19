package com.urlShortner.controllers;

import com.urlShortner.dtos.requests.LoginUserRequest;
import com.urlShortner.dtos.requests.RegisterUserRequesst;
import com.urlShortner.dtos.responses.ApiResponse;
import com.urlShortner.exceptions.ShortUrlException;
import com.urlShortner.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserRequesst request){
        try{
            return new ResponseEntity<>(new ApiResponse(true,authService.registerUser(request)), HttpStatus.CREATED);
        } catch (ShortUrlException error){
            return new ResponseEntity<>(new ApiResponse(false,error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody LoginUserRequest request){
        try{
            return new ResponseEntity<>(new ApiResponse(true, authService.loginUser(request)), HttpStatus.OK);
        } catch (ShortUrlException error){
            return new ResponseEntity<>(new ApiResponse(false,error.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
