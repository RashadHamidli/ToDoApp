package com.company.controller.impl;

import com.company.controller.inter.AuthenticationRestController;
import com.company.dto.request.SignUpRequest;
import com.company.dto.request.SigninRequest;
import com.company.dto.response.JwtAuthenticationResponse;
import com.company.service.inter.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todoapp/auth")
@RequiredArgsConstructor
public class AuthenticationRestControllerImpl implements AuthenticationRestController {
    private final AuthenticationService authenticationService;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @Override
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.refresh(request));
    }
}
