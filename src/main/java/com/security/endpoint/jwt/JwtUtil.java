package com.security.endpoint.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private final String secret = "skjnkdfjnsbkjvbksbhvuksbdkbskjhbviksdfuhuiachdbjhbjdfhuifnvkfjndbkfhiedjbhfbdksbfkdhkjbfkdbnkbfkj";
	
	
	public String generateToken(String username) {
		Date now = new Date();
		Date exDate = new Date(now.getTime() + 3600000);
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", username);
	
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(exDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		
		
		return claims.getSubject();
		
	}

}
