package com.library.demo.services.authentication;

import com.library.demo.models.JwtAuthenticationResponse;
import com.library.demo.models.SignInRequest;
import com.library.demo.models.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}