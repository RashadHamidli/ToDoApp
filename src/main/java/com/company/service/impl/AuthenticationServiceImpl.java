package com.company.service.impl;

import com.company.dao.entities.RefreshToken;
import com.company.dto.request.RefreshRequest;
import com.company.dto.request.SignUpRequest;
import com.company.dto.request.SigninRequest;
import com.company.dto.response.AuthResponse;
import com.company.dto.response.JwtAuthenticationResponse;
import com.company.dao.entities.Role;
import com.company.dao.entities.User;
import com.company.dao.repository.UserRepository;
import com.company.service.inter.AuthenticationService;
import com.company.service.inter.JwtService;
import com.company.service.inter.RefreshTokenService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenServiceImpl refreshTokenService;

    //    @Override
//    public JwtAuthenticationResponse signin(SigninRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        var user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
//        var jwt = jwtService.generateToken(user);
//        return JwtAuthenticationResponse.builder().token(jwt).build();
//    }
//
//
//    @Override
//    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
//                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER).build();
//        userRepository.save(user);
//        var jwt = jwtService.generateToken(user);
//        return JwtAuthenticationResponse.builder().token(jwt).build();
//    }
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        refreshTokenService.createToken(user, jwt);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String token = refreshTokenService.getToken(user.getId());
        System.out.println("token " + user.getId() + "=" + token);
        if (jwtService.isTokenValid(token, user)) {
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } else {
            throw new IllegalArgumentException("Invalid token.");
        }
    }

    @Override
    public AuthResponse refresh(@RequestBody RefreshRequest refreshRequest) {
//        AuthResponse response = new AuthResponse();
//        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
//        if (token.getToken().equals(refreshRequest.getRefreshToken()) &&
//                !refreshTokenService.isRefreshExpired(token)) {
//            User user = token.getUser();
//            String jwtToken = jwtService.generateToken(user);
//            response.setMessage("token successfully refreshed.");
//            response.setAccessToken("Bearer " + jwtToken);
//            response.setUserId(user.getId());
//        }
//        return response;
        return null;
    }
}
