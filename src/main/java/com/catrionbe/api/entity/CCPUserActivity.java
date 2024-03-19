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
@Table(name = "ccpuseractivity")
public class CCPUserActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actId", nullable = false)
	private int actId;
	 
	@Column(name = "userId", nullable = false)
	private int  userId;
	 
	 
	@Column(name = "activity", nullable = false)
	private String activity;
	 
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
   
public int getActId() {
	return actId;
}
public void setActId(int actId) {
	this.actId = actId;
}
public String getActivity() {
	return activity;
}
public void setActivity(String activity) {
	this.activity = activity;
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
