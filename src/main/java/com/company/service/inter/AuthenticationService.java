package com.company.service.inter;

import com.company.dto.request.SignUpRequest;
import com.company.dto.request.SigninRequest;
import com.company.dto.response.JwtAuthenticationResponse;
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    JwtAuthenticationResponse refresh(SigninRequest request);
}
