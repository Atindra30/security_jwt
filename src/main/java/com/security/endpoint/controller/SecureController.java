package com.security.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.endpoint.jwt.JwtUtil;

@RestController
@RequestMapping("/secure")
public class SecureController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/api")
	public String securedApi(@RequestHeader("Authorization") String authorizationHeader) {
		
		String token = authorizationHeader;
		
		String username = jwtUtil.getUsernameFromToken(token);
		
		
		return "This " + username +" is secured API";
		
	}

}
