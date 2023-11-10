package com.company.service.inter;

import com.company.dao.entities.RefreshToken;
import com.company.dao.entities.User;
import com.company.dao.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public interface RefreshTokenService {
    public String createToken(User user, String token);

    String createRefreshToken(User user);

    boolean isRefreshExpired(RefreshToken token);

    RefreshToken getByUser(Long userId);

    String getToken();
}
