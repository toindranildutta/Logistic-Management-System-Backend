package com.catrionbe.api.model;

public class allstaffsmaploc {

	public allstaffsmaploc() {
		
	}
	public int getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(int staffCount) {
		this.staffCount = staffCount;
	}
	public int getStaffCompleted() {
		return staffCompleted;
	}
	public void setStaffCompleted(int staffCompleted) {
		this.staffCompleted = staffCompleted;
	}
	public float getCompletedPer() {
		return completedPer;
	}
	public void setCompletedPer(float completedPer) {
		this.completedPer = completedPer;
	}
	
 

	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}



	private int  locationId;
	
	private int staffCount;

	private int staffCompleted;
	
	private float completedPer;
	
 
	
}
