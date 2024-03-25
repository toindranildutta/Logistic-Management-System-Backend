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
	public float getCompletedPer() {
		return completedPer;
	}
	public void setCompletedPer(float completedPer) {
		this.completedPer = completedPer;
	}
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	private int deptId;;
	
	private int staffCount;

	private int staffCompleted;
	
	private float completedPer;
	
 
	
}
