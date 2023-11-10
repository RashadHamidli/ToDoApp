package com.company.controller.inter;

import com.company.dto.request.RefreshRequest;
import com.company.dto.request.SignUpRequest;
import com.company.dto.request.SigninRequest;
import com.company.dto.response.AuthResponse;
import com.company.dto.response.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthenticationRestController {
    ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request);

    ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request);

    ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest);
}
