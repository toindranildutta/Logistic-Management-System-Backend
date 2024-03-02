/**
 * 
 */
package com.catrion.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.catrion.dto.JWTRequest;
import com.catrion.dto.JWTResponse;
import com.catrion.entity.User;
import com.catrion.repository.UserRepository;
import com.catrion.utils.JWTUtils;

/**
 * 
 */
@Service
public class JWTService{

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	public JWTResponse createJwtToken(JWTRequest jwtRequest,
			AuthenticationManager authenticationManager) throws Exception {
		System.out.println("1");
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		System.out.println(" 2 "+userName);
		System.out.println(" 3 "+userPassword);
		authenticate(userName,userPassword,authenticationManager);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		String newGenerateToken = jwtUtils.generateToken(userDetails);
		User user = userRepository.findById(userName).get();
		return new JWTResponse(user,newGenerateToken);
	}
	

	
	private void authenticate(String userName,String userPassword,
			AuthenticationManager authenticationManager) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		}catch(DisabledException e) {
			throw new Exception("User is disabled");
		}catch(BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
	}
}
