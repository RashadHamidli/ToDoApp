package com.company.service.impl;

import com.company.dao.entities.RefreshToken;
import com.company.dao.entities.User;
import com.company.dao.repository.RefreshTokenRepository;
import com.company.service.inter.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl {

    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(User user) {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
        if (token == null) {
            token = new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    public String createToken(User user, String ntoken) {
        RefreshToken token = new RefreshToken();
        token.setToken(ntoken);
        token.setUser(user);
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        RefreshToken save = refreshTokenRepository.save(token);
        return save.getToken();
    }


    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUser(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }

    public String getToken(Long id) {
        RefreshToken token = refreshTokenRepository.findByUserId(id);
        token.getToken();
        return token.getToken();
    }

}
