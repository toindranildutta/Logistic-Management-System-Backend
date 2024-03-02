/**
 * 
 */
package com.catrion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catrion.entity.User;
import com.catrion.repository.UserRepository;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		String encoderPassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encoderPassword);
		return userRepository.save(user);
	}
}
