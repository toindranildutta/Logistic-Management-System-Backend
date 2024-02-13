package com.catrion.catrionbe.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="ccpuserdetails") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginUserDetails {
 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	 
	 @Column(name = "firstName", nullable = false) 
	private String  firstName;
	 @Column(name = "lastName", nullable = false)
	private String lastName;
	 @Column(name = "emailId", nullable = false)
	private String emailId;
	 @Column(name = "prnNumber", nullable = false)
	private String prnNumber;
	 @Column(name = "password", nullable = false)
	private String password;
	 @Column(name = "confPassword", nullable = false)
	private String confPassword;
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
		
	    public LoginUserDetails(String  firstName,String  lastName, String emailId ,  String password, String confPassword , int  otpGenerated,
	    		 int  otpEntered, String fatherName, 	String grandFatherName , String nationalId, String mobileNumber , String isActive ,
	    		 String isAprroved,  int createdBy , Date createdDate,  int modifiedBy , Date modifiedDate
	    		) {
	        this.firstName = firstName;
	        this.lastName  =  lastName ;
	        this.emailId  =   emailId ;
	        this.password  =  password  ;
	        this.confPassword  =  confPassword ;
	        this.otpGenerated  =  otpGenerated  ;
	        this.otpEntered  =  otpEntered ;
	        this.fatherName  = fatherName  ;
	        this.grandFatherName  =  grandFatherName;
	        this.nationalId  =  nationalId ;
	        this.mobileNumber  =  mobileNumber ;
	        this.isActive  = isActive   ;
	        this.isAprroved  = isAprroved ;
	        this.createdBy  =   createdBy;
	        this.createdDate  = createdDate ;
	        this.modifiedBy  =  modifiedBy ;
	        this.modifiedDate  =  modifiedDate ;
	    }
	  
}
