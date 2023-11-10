package com.company.service;

import com.company.dto.request.SignUpRequest;
import com.company.dto.request.SigninRequest;
import com.company.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
