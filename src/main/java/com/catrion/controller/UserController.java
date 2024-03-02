/**
 * 
 */
package com.catrion.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catrion.entity.User;
import com.catrion.service.UserService;

/**
 * 
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON,
			method=RequestMethod.POST,
			value="/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User savedUser = userService.registerUser(user);
		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON,
			method=RequestMethod.GET,
			value="/forAdmin")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<String> forAdmin(){
		return new ResponseEntity<String>("Accessible to Admin Only",HttpStatus.OK);
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON,
			method=RequestMethod.GET,
			value="/forUser")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<String> forUser(){
		return new ResponseEntity<String>("Accessible to User Only",HttpStatus.OK);
	}
	
}
