package com.routemasterapi.api.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.routemasterapi.api.entity.CustomerEntity;
import com.routemasterapi.api.entity.ParcelEntity;
import com.routemasterapi.api.entity.RouteEntity;
import com.routemasterapi.api.model.ParcelIdRequest;
import com.routemasterapi.api.model.ParcelRequestBody;
import com.routemasterapi.api.repositories.CustomerRepository;
import com.routemasterapi.api.repositories.ParcelRepository;
import com.routemasterapi.api.repositories.RouteRepository;

import utils.DateUtils;

@Service
public class ParcelService  {

	@Autowired
	private ParcelRepository parcelRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private RouteRepository routeRepository;

	

	public ParcelEntity createParcel(ParcelRequestBody parcelReqBody) {
		
		Optional<CustomerEntity> optionalCustomer = customerRepository.findById(parcelReqBody.getCustomerId());
		CustomerEntity customer = optionalCustomer.orElseThrow(() -> new Error("No customer with this id") );
		
		Optional<RouteEntity> optionalRoute = routeRepository.findById(parcelReqBody.getRouteId());
		RouteEntity route = optionalRoute.orElseThrow(() -> new Error("No route with this id") );

		ParcelEntity newParcel = new ParcelEntity();
		newParcel.setParcelType(parcelReqBody.getParcelType());
		newParcel.setParcelName(parcelReqBody.getParcelName());
		newParcel.setDestinationAddress(parcelReqBody.getDestinationAddress());
		newParcel.setDestinationPincode(parcelReqBody.getDestinationPincode());
		newParcel.setSenderName(parcelReqBody.getSenderName());
		newParcel.setReceiverName(parcelReqBody.getReceiverName());
		newParcel.setConsignmentNumber(parcelReqBody.getConsignmentNumber());
		newParcel.setParcelStatus(parcelReqBody.getParcelStatus());
		newParcel.setImageUrl(parcelReqBody.getImageUrl());
		newParcel.setCustomer(customer);
		newParcel.setRoute(route);
		try {
			newParcel.setAddedOn(DateUtils.parseDateString(parcelReqBody.getAddedOn()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parcelRepository.save(newParcel);		 
	}

	public ParcelEntity updateParcel(ParcelRequestBody parcelReqBody) {
		
		Optional<CustomerEntity> optionalCustomer = customerRepository.findById(parcelReqBody.getCustomerId());
		CustomerEntity customer = optionalCustomer.orElseThrow(() -> new Error("No customer with this id") );
		
		Optional<RouteEntity> optionalRoute = routeRepository.findById(parcelReqBody.getRouteId());
		RouteEntity route = optionalRoute.orElseThrow(() -> new Error("No route with this id") );
		
		ParcelEntity newParcel = new ParcelEntity();
		newParcel.setParcelId(parcelReqBody.getParcelId());
		newParcel.setParcelType(parcelReqBody.getParcelType());
		newParcel.setParcelName(parcelReqBody.getParcelName());
		newParcel.setDestinationAddress(parcelReqBody.getDestinationAddress());
		newParcel.setDestinationPincode(parcelReqBody.getDestinationPincode());
		newParcel.setSenderName(parcelReqBody.getSenderName());
		newParcel.setReceiverName(parcelReqBody.getReceiverName());
		newParcel.setConsignmentNumber(parcelReqBody.getConsignmentNumber());
		newParcel.setParcelStatus(parcelReqBody.getParcelStatus());
		newParcel.setImageUrl(parcelReqBody.getImageUrl());
		newParcel.setCustomer(customer);
		newParcel.setRoute(route);
		try {
			newParcel.setAddedOn(DateUtils.parseDateString(parcelReqBody.getAddedOn()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parcelRepository.save(newParcel);		 
	}

	public Page<ParcelEntity> listallparcelsfromdb(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return parcelRepository.listallparcelsfromdb(pageable);
	}
	
	public Page<ParcelEntity> listcustomerparcelstatusfromdb(int pageNumber, int size, int customerId) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return parcelRepository.listcustomerparcelstatusfromdb(customerId, pageable);
	}
	
	public Page<ParcelEntity> listonemonthparcelsfromdb(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
		return parcelRepository.listonemonthparcelsfromdb(oneMonthAgo, pageable);
	}
	
	public Page<ParcelEntity> listonemonthdelayedparcelsfromdb(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
		return parcelRepository.listonemonthdelayedparcelsfromdb(oneMonthAgo, pageable);
	}
 
 
	public String deleteParcel(ParcelIdRequest parcelIdReq) {
		int ParcelId= parcelIdReq.getParcelId();
		parcelRepository.deleteById(ParcelId);
		return "Record Deleted";
	}

}
