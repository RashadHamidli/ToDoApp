package com.company.controller.inter;

import com.company.dto.request.SignUpRequest;
import com.company.dto.request.SigninRequest;
import com.company.dto.response.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthenticationRestController {
    ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request);

    ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request);

    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody SigninRequest request);
}
