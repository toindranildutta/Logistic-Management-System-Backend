/**
 * 
 */
package com.catrion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catrion.dto.JWTRequest;
import com.catrion.dto.JWTResponse;
import com.catrion.service.JWTService;

/**
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/api/auth/")
public class JWTController {

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate")
	public JWTResponse createJWTToken(@RequestBody JWTRequest jwtRequest) throws Exception {
		System.out.println("0");
		return jwtService.createJwtToken(jwtRequest,authenticationManager);
	}
	
}
