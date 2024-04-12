package com.catrionbe.api.model;

import java.util.Date;

import javax.persistence.Column;

public class UpdateUserDTO {
	private int userId;
	
    private String username;
    private String password;
 private String firstName;
    public String getFirstName() {
	return firstName;
}
    private String isAdmin;
	 private String workEmail;
 
    public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	
    private String managerEmail;
	 
	 public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

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

public String getConfPassword() {
	return confPassword;
}

public void setConfPassword(String confPassword) {
	this.confPassword = confPassword;
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

	private String lastName;
 	private String emailId;
 	private String prnNumber;
 	private String confPassword;
 	private int  otpGenerated;
 	private int  otpEntered;
 	private String fatherName; 
 	private String grandFatherName; 
 	 private String nationalId;
 	 
 

 	  private String mobileNumber;
 	 public String getMobileNumber() {
 		return mobileNumber;
 	}
 	public void setMobileNumber(String mobileNumber) {
 		this.mobileNumber = mobileNumber;
 	}
 	 	private int isArchived;
 	  
 	 
 	 
 	 public int getIsArchived() {
 		return isArchived;
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

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

		private String isActive;
 		private String isAprroved;
 		private int createdBy;	
 		private Date createdDate;	
 		private int modifiedBy;	
 		private Date modifiedDate;
 	 	 private int deptId;
 	 	private int locationId;
	 
 		public int getLocationId() {
 	 		return locationId;
 	 	}
 	 	public void setLocationId(int locationId) {
 	 		this.locationId = locationId;
 	 	}
 	 	public int getDeptId() {
 			return deptId;
 		}

 		public void setDeptId(int deptId) {
 			this.deptId = deptId;
 		}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
 
 
}
