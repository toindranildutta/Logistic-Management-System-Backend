package com.catrionbe.api.model;

import java.io.Serializable;

/*
This is class is required for creating a response containing the JWT to be returned to the user.
 */
public class JwtResponsewithEmail implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
    public String getJwttoken() {
		return jwttoken;
	}

	private String emailId;

    public JwtResponsewithEmail(String jwttoken , String emailId) {
        this.jwttoken = jwttoken;
        this.emailId = emailId;
    }
 

    public void setToken ( String jwttoken ) {
          this.jwttoken = jwttoken;
    }
    
    public void setEmailId(String emailId) {
    	this.emailId = emailId;
    }
    
	public String getEmailId() {
		return emailId;
	}
}
