package com.urlShortner.services;

import com.urlShortner.dtos.requests.LoginUserRequest;
import com.urlShortner.dtos.requests.RegisterUserRequesst;
import com.urlShortner.dtos.responses.LoginUserResponse;
import com.urlShortner.dtos.responses.RegisterUserResponse;

public interface AuthService {
    RegisterUserResponse registerUser(RegisterUserRequesst registerUserRequesst);
    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);
}
