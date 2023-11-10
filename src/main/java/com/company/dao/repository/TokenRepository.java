package com.company.dao.repository;

import com.company.dao.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long>{

	Token findByUserId(Long userId);
	
}
