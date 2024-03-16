package com.catrionbe.api.model;

import java.io.Serializable;
import java.util.Date;

public class CCPUpdateFeedbackReq implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	public CCPUpdateFeedbackReq() {

	}

	private int feedbackId;

 
	private int feedbackStatus;

	public int getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(int feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
  
   
}
