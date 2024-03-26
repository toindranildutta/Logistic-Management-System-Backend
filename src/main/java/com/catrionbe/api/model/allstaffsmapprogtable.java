package com.catrionbe.api.model;

public class allstaffsmapprogtable {

	public allstaffsmapprogtable() {
		
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
	 
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getRemainingStaffs() {
		return remainingStaffs;
	}
	public void setRemainingStaffs(int  remainingStaffs) {
		this.remainingStaffs = remainingStaffs;
	}

	public int getCurWeekProgress() {
		return curWeekProgress;
	}
	public void setCurWeekProgress(int curWeekProgress) {
		this.curWeekProgress = curWeekProgress;
	}

	public int getPrevWeekProgress() {
		return prevWeekProgress;
	}
	public void setPrevWeekProgress(int prevWeekProgress) {
		this.prevWeekProgress = prevWeekProgress;
	}

	public int getImprovement() {
		return improvement;
	}
	public void setImprovement(int improvement) {
		this.improvement = improvement;
	}

	private int deptId;
	
	private int staffCount;

	private int staffCompleted;
	
	private int  remainingStaffs;
	
	
	private int curWeekProgress;

	
	private int prevWeekProgress;

	
	private int improvement;

	
	
 
	
}
