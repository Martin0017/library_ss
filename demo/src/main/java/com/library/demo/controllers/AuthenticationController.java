package com.library.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.demo.models.JwtAuthenticationResponse;
import com.library.demo.models.SignInRequest;
import com.library.demo.models.SignUpRequest;
import com.library.demo.models.Validator;
import com.library.demo.services.authentication.AuthenticationService;
import com.library.demo.services.jwt.JwtService;
import com.library.demo.services.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService  jwtService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/isvalidtoken")
    public ResponseEntity<Boolean> isvalidtoken(@RequestBody Validator request) {
        final String userEmail = request.getEmail();
        final String jwt = request.getToken();
        UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(userEmail);
        if(jwtService.isTokenValid(jwt, userDetails)){
          return  ResponseEntity.ok(true);
        }
        return  ResponseEntity.ok(false);
    }
}