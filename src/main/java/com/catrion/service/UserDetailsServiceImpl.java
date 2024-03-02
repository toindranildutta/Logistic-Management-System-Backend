/**
 * 
 */
package com.catrion.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.catrion.entity.User;
import com.catrion.repository.UserRepository;

/**
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		User user = userRepository.findById(userName).get();
		if(user!=null){
			return new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getUserPassword()
					,getAuthorities(user));
		}
		else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}
	private Set<SimpleGrantedAuthority> getAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		
		/*
		 * user.getRoles().forEach(role->{ authorities.add(new
		 * SimpleGrantedAuthority("ROLE_"+role.getRoleName())); });
		 */
		return authorities;
	}
}
