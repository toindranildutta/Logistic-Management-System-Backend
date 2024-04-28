package com.routemasterapi.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "indranil_trackparcel")
public class TrackParcelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trackParcelId", nullable = false)
	private int trackParcelId;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parcelId", unique = true, nullable = false)
    private ParcelEntity parcel;

    @Column(name = "employeeId", nullable = false)
    private int employeeId;
    
    @Column(name = "approveReject", nullable = false)
    private String approveReject;
    
    
    // constructors

	public TrackParcelEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	

    public TrackParcelEntity(int trackParcelId, ParcelEntity parcel, int employeeId, String approveReject) {
		super();
		this.trackParcelId = trackParcelId;
		this.parcel = parcel;
		this.employeeId = employeeId;
		this.approveReject = approveReject;
	}

	// Getter and Setter

	public int getTrackParcelId() {
		return trackParcelId;
	}

	public void setTrackParcelId(int trackParcelId) {
		this.trackParcelId = trackParcelId;
	}

	public ParcelEntity getParcel() {
		return parcel;
	}

	public void setParcel(ParcelEntity parcel) {
		this.parcel = parcel;
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


	@Override
	public String toString() {
		return "TrackParcelEntity [trackParcelId=" + trackParcelId + ", parcel=" + parcel + ", employeeId=" + employeeId
				+ ", approveReject=" + approveReject + "]";
	}


	
	
	

}
