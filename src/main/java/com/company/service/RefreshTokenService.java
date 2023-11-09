package com.company.service;



import com.company.dao.entity.RefreshToken;
import com.company.dao.entity.User;
import com.company.dao.reposiroty.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
	
	@Value("${refresh.token.expires.in}")
	Long expireSeconds;
	
	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}
	
	public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
		if(token == null) {
			token =	new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}
	
	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}

	public RefreshToken getByUser(Long userId) {
		return refreshTokenRepository.findByUserId(userId);	
	}

}
