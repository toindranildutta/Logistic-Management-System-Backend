package com.catrionbe.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.catrionbe.api.service.DateStringDeserializer;
import com.catrionbe.api.service.DateStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "ccpfeedback")
public class CCPFeedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbackId", nullable = false)
	private int feedbackId;
	
	@Column(name = "userId", nullable = false)
	private int  userId;
	
	@Column(name = "feedbackType", nullable = false)
	private int  feedbackType;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "feedbackStatus", nullable = false)
	private int  feedbackStatus;
	 
	@Column(name = "rating", nullable = false)
	private int  rating;
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "userPhone", nullable = false)
	private String userPhone;
	
	public String  getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String  userPhone) {
		this.userPhone = userPhone;
	}
	public int getFeedbackStatus() {
		return feedbackStatus;
	}
	public void setFeedbackStatus(int feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	@Column(name = "isActive", nullable = false)
	private String isActive;
 @Column(name = "isApproved", nullable = false)
	private String isAprroved;
 @Column(name = "createdBy", nullable = false)
	private int createdBy;	
 @Column(name = "createdDate", nullable = false)
	private Date createdDate;	
 @Column(name = "modifiedBy", nullable = false)
	private int modifiedBy;	
 @Column(name = "modifiedDate", nullable = false)
	private Date modifiedDate;
 
	  
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
} 
 
 
public int getFeedbackId() {
	return feedbackId;
}
public void setFeedbackId(int feedbackId) {
	this.feedbackId = feedbackId;
}
public int getFeedbackType() {
	return feedbackType;
}
public void setFeedbackType(int feedbackType) {
	this.feedbackType = feedbackType;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
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
	@Type(type="date")
	@JsonSerialize(using=DateStringSerializer.class)
	@JsonDeserialize(using=DateStringDeserializer.class)
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
	
	@Type(type="date")
	@JsonSerialize(using=DateStringSerializer.class)
	@JsonDeserialize(using=DateStringDeserializer.class)
	public Date getModifiedDate() {
		return modifiedDate;
	}

}
