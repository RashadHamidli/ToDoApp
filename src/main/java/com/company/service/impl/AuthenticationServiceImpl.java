package com.company.service.impl;

import com.company.dao.entities.Token;
import com.company.model.dao.repository.TokenRepository;
import com.company.model.dto.request.SignUpRequest;
import com.company.model.dto.request.SigninRequest;
import com.company.model.dto.response.JwtAuthenticationResponse;
import com.company.model.dao.entities.Role;
import com.company.dao.entities.User;
import com.company.model.dao.repository.UserRepository;
import com.company.service.inter.AuthenticationService;
import com.company.service.inter.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${refresh.token.expires.in}")
    Long expireSeconds;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        Token token = new Token();
        token.setToken(jwt);
        token.setUser(user);
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        tokenRepository.save(token);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        Token foundedToken = tokenRepository.findByUserId(user.getId());
        String token = foundedToken.getToken();
        if (jwtService.isTokenValid(token, user)) {
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } else {
            JwtAuthenticationResponse refreshToken = refresh(request);
            return JwtAuthenticationResponse.builder().token(refreshToken.getToken()).build();
        }
    }


    @Override
    public JwtAuthenticationResponse refresh(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        Token token = tokenRepository.findByUserId(user.getId());
        token.setToken(jwt);
        token.setUser(user);
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        tokenRepository.save(token);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
