package com.catrionbe.api.model;

public class allstaffsmap {

	public allstaffsmap() {
		
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
	public float getCompletePer() {
		return completePer;
	}
	public void setCompletePer(float completePer) {
		this.completePer = completePer;
	}
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	private int deptId;
	
	private int staffCount;

	private int staffCompleted;
	
	private float completePer;
	
 
	
}
