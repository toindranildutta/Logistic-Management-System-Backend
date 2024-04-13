package com.catrionbe.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catrionbe.api.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import utils.UnauthorizedException;

/*
The JwtRequestFilter extends the Spring Web Filter OncePerRequestFilter class. For any incoming request this Filter
class gets executed. It checks if the request has a valid JWT token. If it has a valid JWT Token then it sets the
 Authentication in the context, to specify that the current user is authenticated.
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(" -- Servlet path " + request.getServletPath());
		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			// System.out.println(" Inside Filter Internal ----- > 1 ");
			jwtToken = requestTokenHeader.substring(7);
			// System.out.println(" Inside Filter Internal ----- > 1.1 "+jwtToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				// System.out.println(" Inside Filter Internal ----- > 1.2 "+username);
			} catch (IllegalArgumentException e) {
				// System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				// System.out.println("JWT Token has expired");
				throw new UnauthorizedException("JWT Token has expired");
			}
		} else {

			logger.warn("JWT Token does not begin with Bearer String");
			if (request.getServletPath().equals("/authenticate")) {

				chain.doFilter(request, response);
				return;
			} else {
				throw new UnauthorizedException("authentication failed");
			}
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// System.out.println(" Inside Filter Internal ----- > 2");
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				// System.out.println(" Inside Filter Internal ----- > 3 ");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		// System.out.println(" Inside Filter Internal ----- > 14 ");
		chain.doFilter(request, response);
	}

}
