package com.routemasterapi.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "indranil_parcel")
public class ParcelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parcelId", nullable = false)
	private int parcelId;

	@Column(name = "parcelType", nullable = false)
	private String parcelType;

	@Column(name = "parcelName", nullable = false)
	private String parcelName;

	@Column(name = "destinationAddress", nullable = false)
	private String destinationAddress;

	@Column(name = "destinationPincode", nullable = false)
	private String destinationPincode;

	@Column(name = "senderName", nullable = false)
	private String senderName;

	@Column(name = "receiverName", nullable = false)
	private String receiverName;

	@Column(name = "consignmentNumber", nullable = false)
	private String consignmentNumber;

	@Column(name = "parcelStatus", nullable = false)
	private String parcelStatus;

	@Column(name = "imageUrl", nullable = false)
	private String imageUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId", nullable = false)
	private CustomerEntity customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "routeId", nullable = false)
	private RouteEntity route;
	
	@Column(name = "addedOn", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date addedOn;

	// Getter and Setter
	
	public int getParcelId() {
		return parcelId;
	}

	public void setParcelId(int parcelId) {
		this.parcelId = parcelId;
	}

	public String getParcelType() {
		return parcelType;
	}

	public void setParcelType(String parcelType) {
		this.parcelType = parcelType;
	}

	public String getParcelName() {
		return parcelName;
	}

	public void setParcelName(String parcelName) {
		this.parcelName = parcelName;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationPincode() {
		return destinationPincode;
	}

	public void setDestinationPincode(String destinationPincode) {
		this.destinationPincode = destinationPincode;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getConsignmentNumber() {
		return consignmentNumber;
	}

	public void setConsignmentNumber(String consignmentNumber) {
		this.consignmentNumber = consignmentNumber;
	}

	public String getParcelStatus() {
		return parcelStatus;
	}

	public void setParcelStatus(String parcelStatus) {
		this.parcelStatus = parcelStatus;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public RouteEntity getRoute() {
		return route;
	}

	public void setRoute(RouteEntity route) {
		this.route = route;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	
	

}
