package com.catrion.catrionbe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ccpuseraccepancetracker") 
public class CCPUserAcceptTrack {
	
	private int acceptanceId ;
	private int acceptScreen1;
	private int acceptScreen2;
	private int acceptScreen3;
	private int acceptScreen4;
	private int acceptScreen5;
	private int acceptScreen6;
	private int acceptScreen7;
	private int acceptScreen8;
	private int acceptScreen9;
	private int acceptScreen10;
	private int acceptScreen11;
 	private String isActive;
 	private String isAprroved;
 	private int createdBy;	
 	private Date createdDate;	
 	private int modifiedBy;	
 	private Date modifiedDate;
 	
 	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 	 
	public int getAcceptanceId() {
		return acceptanceId;
	}
	public void setAcceptanceId(int acceptanceId) {
		this.acceptanceId = acceptanceId;
	}
	public int getAcceptScreen1() {
		return acceptScreen1;
	}
	public void setAcceptScreen1(int acceptScreen1) {
		this.acceptScreen1 = acceptScreen1;
	}
	public int getAcceptScreen2() {
		return acceptScreen2;
	}
	public void setAcceptScreen2(int acceptScreen2) {
		this.acceptScreen2 = acceptScreen2;
	}
	public int getAcceptScreen3() {
		return acceptScreen3;
	}
	public void setAcceptScreen3(int acceptScreen3) {
		this.acceptScreen3 = acceptScreen3;
	}
	public int getAcceptScreen4() {
		return acceptScreen4;
	}
	public void setAcceptScreen4(int acceptScreen4) {
		this.acceptScreen4 = acceptScreen4;
	}
	public int getAcceptScreen5() {
		return acceptScreen5;
	}
	public void setAcceptScreen5(int acceptScreen5) {
		this.acceptScreen5 = acceptScreen5;
	}
	public int getAcceptScreen6() {
		return acceptScreen6;
	}
	public void setAcceptScreen6(int acceptScreen6) {
		this.acceptScreen6 = acceptScreen6;
	}
	public int getAcceptScreen7() {
		return acceptScreen7;
	}
	public void setAcceptScreen7(int acceptScreen7) {
		this.acceptScreen7 = acceptScreen7;
	}
	public int getAcceptScreen8() {
		return acceptScreen8;
	}
	public void setAcceptScreen8(int acceptScreen8) {
		this.acceptScreen8 = acceptScreen8;
	}
	public int getAcceptScreen9() {
		return acceptScreen9;
	}
	public void setAcceptScreen9(int acceptScreen9) {
		this.acceptScreen9 = acceptScreen9;
	}
	public int getAcceptScreen10() {
		return acceptScreen10;
	}
	public void setAcceptScreen10(int acceptScreen10) {
		this.acceptScreen10 = acceptScreen10;
	}
	public int getAcceptScreen11() {
		return acceptScreen11;
	}
	public void setAcceptScreen11(int acceptScreen11) {
		this.acceptScreen11 = acceptScreen11;
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
	
	
	
}
