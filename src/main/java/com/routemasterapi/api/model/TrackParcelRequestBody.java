package com.routemasterapi.api.model;

public class TrackParcelRequestBody { 
		 
	private int trackParcelId;
	
	private int parcelId;
	
	private int employeeId;
	
	private String approveReject;
	

	public int getTrackParcelId() {
		return trackParcelId;
	}

	public void setTrackParcelId(int trackParcelId) {
		this.trackParcelId = trackParcelId;
	}

	public int getParcelId() {
		return parcelId;
	}

	public void setParcelId(int parcelId) {
		this.parcelId = parcelId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getApproveReject() {
		return approveReject;
	}

	public void setApproveReject(String approveReject) {
		this.approveReject = approveReject;
	}
		 
	}
