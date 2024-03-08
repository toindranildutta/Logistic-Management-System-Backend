package com.catrionbe.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.catrionbe.api.service.DateStringDeserializer;
import com.catrionbe.api.service.DateStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "ccpnewsupdatedetails")
public class CCPNews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newsId", nullable = false)
	private int newsId;
	
	@Column(name = "newsTitle", nullable = false)
	private String newsTitle;
	@Column(name = "imageUrl", nullable = false)
	private String imageUrl;
	@Column(name = "newsContent", nullable = false)
	private String newsContent;
	
	@Column(name = "rating", nullable = false)
	private int rating;
	
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
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
	@Type(type="date")
	@JsonSerialize(using=DateStringSerializer.class)
	@JsonDeserialize(using=DateStringDeserializer.class)
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
	
	@Type(type="date")
	@JsonSerialize(using=DateStringSerializer.class)
	@JsonDeserialize(using=DateStringDeserializer.class)
	public Date getModifiedDate() {
		return modifiedDate;
	}
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
	
}
