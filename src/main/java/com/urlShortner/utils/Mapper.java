package com.urlShortner.utils;

import com.urlShortner.datas.models.Role;
import com.urlShortner.datas.models.Subscription;
import com.urlShortner.datas.models.User;
import com.urlShortner.dtos.requests.RegisterUserRequesst;
import com.urlShortner.dtos.responses.LoginUserResponse;
import com.urlShortner.dtos.responses.RegisterUserResponse;


public class Mapper {

    public static User mapToRegisterUser(RegisterUserRequesst request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);
        user.setSubscription(Subscription.FREE);
        return user;
    }

    public static RegisterUserResponse mapToRegisterUserResponse(User user){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(user.getEmail());
        response.setMessage("User Registered Successfully");
        return response;
    }

    public static LoginUserResponse mapToLoginResponse(String token) {
        LoginUserResponse response = new LoginUserResponse();
        response.setToken(token);
        response.setMessage("Login Successful");
        return response;
    }

}


