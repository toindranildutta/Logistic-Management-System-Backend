package com.routemasterapi.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.routemasterapi.api.entity.CustomerEntity;
import com.routemasterapi.api.model.CustomerIdRequest;
import com.routemasterapi.api.model.CustomerRequestBody;
import com.routemasterapi.api.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	

	public CustomerEntity createCustomer(CustomerRequestBody customerReqBody) {

		CustomerEntity newCustomer = new CustomerEntity();
		newCustomer.setFirstName(customerReqBody.getFirstName());
		newCustomer.setLastName(customerReqBody.getLastName());
		newCustomer.setPhone(customerReqBody.getPhone());
		newCustomer.setEmail(customerReqBody.getEmail());
		newCustomer.setAddress(customerReqBody.getAddress());
		return customerRepository.save(newCustomer);		 
	}

	public CustomerEntity updateCustomer(CustomerRequestBody customerReqBody) {
		CustomerEntity newCustomer = new CustomerEntity();
		newCustomer.setCustomerId(customerReqBody.getCustomerId());
		newCustomer.setFirstName(customerReqBody.getFirstName());
		newCustomer.setLastName(customerReqBody.getLastName());
		newCustomer.setPhone(customerReqBody.getPhone());
		newCustomer.setEmail(customerReqBody.getEmail());
		newCustomer.setAddress(customerReqBody.getAddress());
		return customerRepository.save(newCustomer);		 
	}

	public Page<CustomerEntity> listallcustomersfromdb(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return customerRepository.listallcustomersfromdb(pageable);
	}
	
	
 
	public String deleteCustomer(CustomerIdRequest customerIdReq) {
		int customerId= customerIdReq.getCustomerId();
		customerRepository.deleteById(customerId);
		return "Record Deleted";
	}
}
