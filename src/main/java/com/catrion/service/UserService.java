/**
 * 
 */
package com.catrion.service;

import org.springframework.stereotype.Service;

import com.catrion.entity.User;

/**
 * 
 */
@Service
public interface UserService {

	User registerUser(User user);
	
}
