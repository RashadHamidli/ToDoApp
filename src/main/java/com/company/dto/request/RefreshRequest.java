package com.company.dto.request;

import lombok.Data;

@Data
public class RefreshRequest {
    Long userId;
    String refreshToken;
}
