package com.library.demo.services.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.library.demo.models.JwtAuthenticationResponse;
import com.library.demo.models.Role;
import com.library.demo.models.SignInRequest;
import com.library.demo.models.SignUpRequest;
import com.library.demo.models.User;
import com.library.demo.repository.UserRepository;
import com.library.demo.services.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        Optional<User> existingUser = userRepository.findByEmailUser(request.getEmail());
        if (existingUser.isPresent()) {
            return JwtAuthenticationResponse.builder().token("Correo en uso.").build();
        }
    
        User user = User.builder()
                .userName(request.getFirstName()) 
                .lastnameUser(request.getLastName())
                .emailUser(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roleUser(Role.USER)
                .build();
    
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmailUser(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Correo o contraseña incorrectos"));
        user.setUserName(request.getEmail());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}