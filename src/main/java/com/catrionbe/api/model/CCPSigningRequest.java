package com.catrionbe.api.model;

import java.io.Serializable;
import java.util.Date;

public class CCPSigningRequest  implements Serializable {
	 
    private static final long serialVersionUID = 5926468583005150707L;
    
    public CCPSigningRequest () {
    	
    }
    
	private int signId;
	
 	private int  userId;
	
 	private int  statusId;
	
 	private String signingItem;
	
 	private String tokenVal;
	
 	private String displayDate;
	
 	private String isActive;
 
 	private String isAprroved;
 
 	private int createdBy;	
 
 	private Date createdDate;	
 
 	private int modifiedBy;	
 
 	private Date modifiedDate;
 
	public int getSignId() {
	return signId;
}
public void setSignId(int signId) {
	this.signId = signId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getStatusId() {
	return statusId;
}
public void setStatusId(int statusId) {
	this.statusId = statusId;
}
public String getSigningItem() {
	return signingItem;
}
public void setSigningItem(String signingItem) {
	this.signingItem = signingItem;
}
public String getTokenVal() {
	return tokenVal;
}
public void setTokenVal(String tokenVal) {
	this.tokenVal = tokenVal;
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

}
