package com.catrion.catrionbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 import com.catrion.catrionbe.repository.LoginRepository;
@Service("CCPUserService")
public class CCPUserService {

	@Autowired
LoginRepository userDAO;
	
	
	@Transactional
	public String ValidateUser(String userName, String password) throws Exception {
		return userDAO.ValidateUser(userName, password);
	}
	
	@Transactional
	public void updateUserDetails(Object LoginUserDetails) throws Exception {
		System.out.println("  Inside updateLoginUserDetails 2" );
		userDAO.updateUserDetails(LoginUserDetails);
	}
	
	
	@Transactional
	public String validateOTP(String otpNumber, String phoneNumber) throws Exception {
		return userDAO.validateOTP(otpNumber,phoneNumber);
	} 
	
	
}
