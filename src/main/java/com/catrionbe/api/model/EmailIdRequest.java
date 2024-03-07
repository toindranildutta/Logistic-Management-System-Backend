package com.catrionbe.api.model;

 

import java.io.Serializable;

public class EmailIdRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    
     private String emailId;

    //need default constructor for JSON Parsing
    public EmailIdRequest()
    {

    }
 

 

	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


}
