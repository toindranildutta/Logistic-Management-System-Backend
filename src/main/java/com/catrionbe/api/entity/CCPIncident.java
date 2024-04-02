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
@Table(name = "ccpincident")
public class CCPIncident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "incidentId", nullable = false)
	private int incidentId;
	
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
	public String getAttachementUrl() {
		return attachementUrl;
	}
	public void setAttachementUrl(String attachementUrl) {
		this.attachementUrl = attachementUrl;
	}

	
	@Column(name = "userId", nullable = false)
	private int  userId;
	
	@Column(name = "severityId", nullable = false)
	private int  severityId;
	
	@Column(name = "statusId", nullable = false)
	private int statusId;
	
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}


	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "attachementUrl", nullable = false)
	private String   attachementUrl;
	
	@Column(name = "incidentSubject", nullable = false)
	private String incidentSubject;
	 
	public String getIncidentSubject() {
		return incidentSubject;
	}
	public void setIncidentSubject(String incidentSubject) {
		this.incidentSubject = incidentSubject;
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
 @Column(name = "userContactInfo", nullable = false)
	private String userContactInfo;
	  
public String getUserContactInfo() {
	return userContactInfo;
}
public void setUserContactInfo(String userContactInfo) {
	this.userContactInfo = userContactInfo;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
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
