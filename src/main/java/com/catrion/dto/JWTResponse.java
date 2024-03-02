/**
 * 
 */
package com.catrion.dto;

import com.catrion.entity.User;

/**
 * 
 */
public class JWTResponse {

	private User user;
	private String jwtToken;
	
	public JWTResponse() {}
	
	public JWTResponse(User user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
