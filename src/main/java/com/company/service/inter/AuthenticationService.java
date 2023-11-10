package com.company.service.inter;

import com.company.dto.request.RefreshRequest;
import com.company.dto.request.SignUpRequest;
import com.company.dto.request.SigninRequest;
import com.company.dto.response.AuthResponse;
import com.company.dto.response.JwtAuthenticationResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    AuthResponse refresh(@RequestBody RefreshRequest refreshRequest);
}
