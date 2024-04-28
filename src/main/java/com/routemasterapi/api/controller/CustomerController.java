package com.routemasterapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routemasterapi.api.model.CustomerIdRequest;
import com.routemasterapi.api.model.CustomerRequestBody;
import com.routemasterapi.api.service.CustomerService;
  
@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;	
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestBody customerReqBody) throws Exception {
		return ResponseEntity.ok(customerService.createCustomer(customerReqBody));
	}
	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequestBody customerReqBody) throws Exception {
		return ResponseEntity.ok(customerService.updateCustomer(customerReqBody));
	}		
	
	@RequestMapping(value = "/listAllCustomers", method = RequestMethod.GET)
	public ResponseEntity<?> listAllCustomers(@RequestParam(defaultValue = "0") final Integer pageNumber,
			@RequestParam(defaultValue = "10") final Integer size) throws Exception {
		return ResponseEntity.ok(customerService.listallcustomersfromdb(pageNumber, size));
	}	
	
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@RequestBody CustomerIdRequest customerIdRequest) throws Exception {
		return ResponseEntity.ok(customerService.deleteCustomer(customerIdRequest));
	}		
	
}
