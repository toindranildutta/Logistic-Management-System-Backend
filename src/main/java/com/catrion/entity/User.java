/**
 * 
 */
package com.catrion.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.catrion.utils.DateStringDeserializer;
import com.catrion.utils.DateStringSerializer;

/**
 * 
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name="userName")
	private String userName;
	
	@Column(name="userFirstName")
	private String userFirstName;
	
	@Column(name="userLastName")
	private String userLastName;
	
	@Column(name="userPassword")
	private String userPassword;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",
	joinColumns = { @JoinColumn(name = "USER_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
	)
	private Set<Role> roles;
	
	public User() {}

	public User(String userName, String userFirstName, String userLastName, String userPassword, Set<Role> roles ,
			String emailId , 
			String prnNumber,
			int otpGenerated,
			int otpEntered,
			String fatherName,
			String grandFatherName,
			String nationalId ,
			String mobileNumber,
			String isActive , 
			String isAprroved,
			int createdBy,
			Date createdDate,
			int modifiedBy,
			Date modifiedDate
			
			) {
		super();
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.roles = roles;
		this.emailId =emailId;
		this.prnNumber =prnNumber;
		this.otpGenerated =otpGenerated;
		this.otpEntered =otpEntered;
		this.fatherName =fatherName;
		this.grandFatherName =grandFatherName;
		this.nationalId =nationalId;
		this.mobileNumber =mobileNumber;
		this.isActive = isActive;
		this.isAprroved =isAprroved;
		this.createdBy =createdBy;
		this.createdDate = createdDate;
		this.modifiedBy =modifiedBy;
		this.modifiedDate =modifiedDate;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

	
	 @Column(name = "emailId", nullable = false)
		private String emailId;
		 @Column(name = "prnNumber", nullable = false)
		private String prnNumber;
		 
		 @Column(name = "otpGenerated", nullable = false)
		private int  otpGenerated;
		 @Column(name = "otpEntered", nullable = false)
		private int  otpEntered;
		 @Column(name = "fatherName", nullable = false)
		private String fatherName; 
		 @Column(name = "grandFatherName", nullable = false)
		private String grandFatherName; 
		 @Column(name = "nationalId", nullable = false)
		 private String nationalId;
		 @Column(name = "mobileNumber", nullable = false)
		 private String mobileNumber; 
		 @Column(name = "isActive", nullable = false)
			private String isActive;
		 @Column(name = "isAprroved", nullable = false)
			private String isAprroved;
		 @Column(name = "createdBy", nullable = false)
			private int createdBy;	
		 @Column(name = "createdDate", nullable = false)
			private Date createdDate;	
		 @Column(name = "modifiedBy", nullable = false)
			private int modifiedBy;	
		 @Column(name = "modifiedDate", nullable = false)
			private Date modifiedDate;
			
		 
		 
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPrnNumber() {
		return prnNumber;
	}
	public void setPrnNumber(String prnNumber) {
		this.prnNumber = prnNumber;
	}
	public int getOtpGenerated() {
		return otpGenerated;
	}
	public void setOtpGenerated(int otpGenerated) {
		this.otpGenerated = otpGenerated;
	}
	public int getOtpEntered() {
		return otpEntered;
	}
	public void setOtpEntered(int otpEntered) {
		this.otpEntered = otpEntered;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getGrandFatherName() {
		return grandFatherName;
	}
	public void setGrandFatherName(String grandFatherName) {
		this.grandFatherName = grandFatherName;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
