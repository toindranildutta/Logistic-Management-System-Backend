package com.catrionbe.api.model;

import java.io.Serializable;

public class OtpRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    
    private int otpgenerated;
    private String emailId;

    //need default constructor for JSON Parsing
    public OtpRequest()
    {

    }
 

	public int  getOtpgenerated() {
		return otpgenerated;
	}

	public void setOtpgenerated(int otpgenerated) {
		this.otpgenerated = otpgenerated;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


}
