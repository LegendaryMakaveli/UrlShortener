package com.urlShortner.services;

import com.urlShortner.Validation.AuthValidations;
import com.urlShortner.datas.models.User;
import com.urlShortner.datas.repositories.UserRepository;
import com.urlShortner.dtos.requests.LoginUserRequest;
import com.urlShortner.dtos.requests.RegisterUserRequesst;
import com.urlShortner.dtos.responses.LoginUserResponse;
import com.urlShortner.dtos.responses.RegisterUserResponse;
import com.urlShortner.exceptions.InvalidCredentialsException;
import com.urlShortner.exceptions.UserAlreadyExistEception;
import com.urlShortner.security.JwtService;
import com.urlShortner.utils.HashPassword;
import com.urlShortner.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import static com.urlShortner.utils.Mapper.mapToRegisterUserResponse;

@AllArgsConstructor
@Service
public class AuthServiceImplementations implements AuthService{
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private AuthValidations validations;


    @Override
    public RegisterUserResponse registerUser(RegisterUserRequesst request) {
        validations.registerValidation(request);
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {throw new UserAlreadyExistEception("User already exists");});
        User user = Mapper.mapToRegisterUser(request);
        user.setPassword(HashPassword.hash(request.getPassword()));

        User savedUser = userRepository.save(user);
        return mapToRegisterUserResponse(savedUser);
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) {
        User user = userRepository.findByEmail(loginUserRequest.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if(!HashPassword.checkPassword(loginUserRequest.getPassword(),user.getPassword())) throw new InvalidCredentialsException("Invalid credentials");
        String token = jwtService.generateToken(user);

        LoginUserResponse response = Mapper.mapToLoginResponse(token);

        return response;
    }
}
