package com.catrionbe.api.model;

import java.io.Serializable;
import java.util.Date;

public class CCPIncidentRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	public CCPIncidentRequest() {

	}

	private int incidentId;

	private int userId;

	private int  severityId;
	private int statusId;

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	private String  incidentSubject;
	
	private String description;

	private byte[]   image;
	
	
	
	private String isActive;

	private String isAprroved;

	private int createdBy;

	private Date createdDate;

	private int modifiedBy;

	private Date modifiedDate;

	private int feedbackStatus;

	public int getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(int feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsAprroved() {
		return isAprroved;
	}

	public void setIsAprroved(String isAprroved) {
		this.isAprroved = isAprroved;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}
 
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}

	public int getSeverityId() {
		return severityId;
	}

	public void setSeverityId(int severityId) {
		this.severityId = severityId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getIncidentSubject() {
		return incidentSubject;
	}

	public void setIncidentSubject(String incidentSubject) {
		this.incidentSubject = incidentSubject;
	}



}
