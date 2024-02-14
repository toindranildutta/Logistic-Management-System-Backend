package com.catrion.catrionbe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catrion.catrionbe.entity.LoginUserDetails;
import com.catrion.catrionbe.service.CCPUserService;
import com.catrion.catrionbe.utils.ErrorResponse;
import com.catrion.catrionbe.utils.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
 

@RestController
public class CCPUserController {
	
	@Autowired
	CCPUserService userService;
	final Logger logger = Logger.getLogger(CCPUserController.class);
	
	
	@RequestMapping(value = "/checkUserCredentials",params = { "userName", "password" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> checkStudentWallet(@RequestParam( value="userName",  required=true ) String userName, 
			@RequestParam( value="password",   required = true ) String password)  throws Exception {
		//String item = null;
		String resultText = "";
		List<ErrorResponse> errorMap = new ArrayList<>();
		try {
			boolean flag = false;
			logger.info("Inside ValidateUser");
			System.out.println("Inside ValidateUser 1");
			if(Validator.nullCheck(userName)|| Validator.emptyCheck(userName)) {
				flag = true;
				System.out.println("userName cannot be null 2 ");
				errorMap.add(new ErrorResponse("ERR-001", "userName  can not be Empty"));
			}else if(Validator.nullCheck(password)|| Validator.emptyCheck(password)) {
				flag = true;
				System.out.println("Password cannot be null 3");
				errorMap.add(new ErrorResponse("ERR-002", "Password  can not be Empty"));
 			}
			 
			if(flag) {
				String errorjson = new ObjectMapper().writeValueAsString(errorMap);
				//logger.info("Error "+errorjson);
				logger.error(errorjson);
				System.out.println("Validation Failed 4");
 				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorjson);
			}
			else{
			System.out.println("Calling Validate User Service..... 5");	
			
		 resultText = userService.ValidateUser(userName, password);
			
			System.out.println("Ended  Validate User Service..... 6");	
			if (resultText.equals(null) || resultText.equals(""))
			{
				logger.error("Validation Failed 7");
				errorMap.add(new ErrorResponse("ERROR-001", "The mobile number/password entered is incorrect. Please enter correct login details"));
				String errorjson = new ObjectMapper().writeValueAsString(errorMap);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorjson);	
			}
			else{
				System.out.println("Validation Success 8"); 
				errorMap.add(new ErrorResponse("ERROR-001", "User Found"));
				return ResponseEntity.status(HttpStatus.OK).body(resultText);
			}
			
			}
			
 		} catch (Exception e) {
			System.out.println("Exception Occured 7" + e);
			errorMap.add(new ErrorResponse("ERROR-001", "The mobile number/password entered is incorrect. Please enter correct login details"));
			logger.error("Exception Occured Validate User : " + e.getMessage());
			logger.error("Error occured"+e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Failed");		
	}	
	
	
	@RequestMapping(value = "/updateLoginUserDetails", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateLoginUserDetails(@RequestBody LoginUserDetails loginUserDetails) {
		
		List<ErrorResponse> errorMap = new ArrayList<>();
		String successjson = "";
		
		try {
			logger.info("Inside updateLoginUserDetails ");
			userService.updateUserDetails(loginUserDetails);
			errorMap.add(new ErrorResponse("SAVE-001", "LoginUserDetails updated successfully."));
			successjson = new ObjectMapper().writeValueAsString(errorMap);
			logger.info("return from updateLoginUserDetails "+successjson);
		} catch (Exception e) {
			logger.error("Exception Occured updateLoginUserDetails: " + e.getMessage());
			logger.error("Error occured"+e);
			errorMap.add(new ErrorResponse("EXEC-001", e.getMessage()));
			String errjson = null;
			try {
			errjson = new ObjectMapper().writeValueAsString(errorMap);
			}catch (Exception e1) { } 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( errjson);
		}
		return ResponseEntity.status(HttpStatus.OK).body(successjson);		
	}

}
