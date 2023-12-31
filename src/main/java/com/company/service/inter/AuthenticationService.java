package com.company.service.inter;

import com.company.model.dto.request.SignUpRequest;
import com.company.model.dto.request.SigninRequest;
import com.company.model.dto.response.JwtAuthenticationResponse;
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    JwtAuthenticationResponse refresh(SigninRequest request);
}
