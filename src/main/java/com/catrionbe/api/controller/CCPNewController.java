package com.catrionbe.api.controller;

import com.catrionbe.api.config.JwtTokenUtil;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.service.CCPNewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
Expose a POST API /authenticate using the JwtAuthenticationController. The POST API gets username and password in the
body- Using Spring Authentication Manager we authenticate the username and password.If the credentials are valid,
a JWT token is created using the JWTTokenUtil and provided to the client.
 */
@RestController
@CrossOrigin
public class CCPNewController {

 

    @Autowired
    private CCPNewsService objCCPNewsService;

    @RequestMapping(value = "/fetchnews", method = RequestMethod.GET)
    public ResponseEntity<?> fetchnews( ) throws Exception {
    	
        return ResponseEntity.ok(objCCPNewsService.viewAllNews());
    }
    
    
    
    
}