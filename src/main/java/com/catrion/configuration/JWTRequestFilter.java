/**
 * 
 */
package com.catrion.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.catrion.service.JWTService;
import com.catrion.utils.JWTUtils;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * 
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
			, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		final String header = request.getHeader("Authorization");
		
		String jwtToken = null;
		String userName = null;
		if(header!=null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			try {
				userName = jwtUtils.getUserNameFromToken(jwtToken);
			}catch(IllegalArgumentException e) {
				e.getMessage();
			}catch(ExpiredJwtException e) {
				e.getMessage();
			}
		}
		else {
			System.out.println("JWT Token does not start with Bearer");
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			
			if(jwtUtils.validateToken(jwtToken,userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new  UsernamePasswordAuthenticationToken(userDetails
						, null,
						userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request,response);
	}

	
}
