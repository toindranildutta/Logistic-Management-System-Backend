package com.catrion.catrionbe.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.catrion.catrionbe.constant.MessageConstants;
import com.catrion.catrionbe.dto.LoginResponseDTO;
import com.catrion.catrionbe.entity.ActiveToken;
import com.catrion.catrionbe.repository.ActiveTokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements MessageConstants {
    private final AuthenticationManager authenticationManager;
    private final String jwtSecret;
    private final String jwtExpirationDays;
    private final ActiveTokenRepository activeTokenRepository;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, String jwtSecret, String jwtExpirationDays, ActiveTokenRepository activeTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtSecret = jwtSecret;
        this.jwtExpirationDays = jwtExpirationDays;
        this.activeTokenRepository = activeTokenRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(Integer.parseInt(jwtExpirationDays))))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(ROLES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        saveTokenToRepo(accessToken, user.getUsername());

        response.setContentType(APPLICATION_JSON_VALUE);
       // new ObjectMapper().writeValue(response.getOutputStream(), new LoginResponseDTO(user.getUsername(), accessToken));
    }

    private void saveTokenToRepo(String token, String username) {
    //    ActiveToken activeToken = new ActiveToken(token, username, LocalDateTime.now());
    //    activeTokenRepository.save(activeToken);
    }
}
