package com.catrion.catrionbe.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.catrion.catrionbe.dto.ApiResponse;
import com.catrion.catrionbe.dto.AuthenticationRequest;
import com.catrion.catrionbe.dto.AuthenticationResponse;
import com.catrion.catrionbe.dto.CCPAuthenticationRequest;
import com.catrion.catrionbe.dto.CCPLoginRequest;
import com.catrion.catrionbe.dto.CCPUserDetailsResponse;
import com.catrion.catrionbe.dto.LoginRequest;
import com.catrion.catrionbe.dto.SignUpRequest;
import com.catrion.catrionbe.model.JwtUser;
import com.catrion.catrionbe.repository.JwtUserRepository;
import com.catrion.catrionbe.service.CCPUserService;
import com.catrion.catrionbe.service.CustomUserDetailsService;
import com.catrion.catrionbe.utils.JwtUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService userDetailsService;
    
    @Autowired
    CCPUserService  cCPUserService;
    
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtUserRepository userRepository;

    @GetMapping("/hello")
    public String hello()
    {
        return  "Nil here";
    }


    @GetMapping("/checkUser")
    public String checkUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return  currentPrincipalName;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
    @PostMapping("/authenticateCCPUser")
    public ResponseEntity<?> authenticateCCPUser(@RequestBody CCPAuthenticationRequest authenticationRequest) {
    	System.out.println("1 ---------------- ");
		/*
		 * Authentication authenticate = authenticationManager.authenticate( new
		 * UsernamePasswordAuthenticationToken(authenticationRequest.getMobileNumber(),
		 * authenticationRequest.getPrnNumber()) );
		 */

        CCPUserDetailsResponse userDetails = null;
		try {
			System.out.println("2 ---------------- ");
			userDetails = cCPUserService.loadCCPUserDetails(authenticationRequest.getMobileNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        final String jwt = jwtUtil.generateCCPToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
    

    
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {


        if(userRepository.findUserByEmail(signUpRequest.getEmail()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmail(signUpRequest.getEmail());
        jwtUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(jwtUser);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
    
    
    @PostMapping("/generateCCPToken")
    public ResponseEntity<?> generateCCPToken(@Valid @RequestBody CCPLoginRequest loginRequest) {
    	  System.out.println(" Step 1 gcc ");
			/*
			 * Authentication authentication = authenticationManager.authenticate( new
			 * UsernamePasswordAuthenticationToken( loginRequest.getMobileNumber(),
			 * loginRequest.getPrnNumber() ) );
			 */
        System.out.println(" Step 2 gcc ");
       // SecurityContextHolder.getContext().setAuthentication(authentication);
        CCPUserDetailsResponse userDetails = null;
		try {
			userDetails = cCPUserService.loadCCPUserDetails(loginRequest.getMobileNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(" Step 2 gcc ");
        final String jwt = jwtUtil.generateCCPToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    
}
