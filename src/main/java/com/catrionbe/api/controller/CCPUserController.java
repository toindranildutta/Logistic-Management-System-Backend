package  com.catrionbe.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 
import org.apache.log4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.service.CCPUserService;
import com.catrionbe.api.service.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.AuthenticationResponse;
import common.ErrorResponse;
import common.LoginUserDetails;
import common.Validator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
 

@RestController
@RequiredArgsConstructor
public class CCPUserController {
	
  
	 private final CCPUserService userService  ;
	
	 
	//CCPUserService userService;
	final Logger logger = Logger.getLogger(CCPUserController.class);
	
	
	@RequestMapping(value = "/getUserDetailsById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<byte[]> getUserDetailsById(@PathVariable int id)   throws Exception {
		String item = null;
		try {
			logger.info("Inside getUserDetailsById "+ id);
			item = userService.getUserDetailsById(id);
			logger.info("return from getUserDetailsById ");
		} catch (Exception e) {
			logger.error("Exception Occured getUserDetailsById : " + e.getMessage());
			logger.error("Error occured"+e);
		}
	
		return ResponseEntity.status(HttpStatus.OK).body(item.getBytes("UTF-8"));		
	}
	
	@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	@RequestMapping(value = "/checkUserCredentials",params = { "userName", "prnNumber" }, method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> checkStudentWallet(@RequestParam( value="userName",  required=true ) String userName, 
			@RequestParam( value="prnNumber",   required = true ) String prnNumber)  throws Exception {
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
			}else if(Validator.nullCheck(prnNumber)|| Validator.emptyCheck(prnNumber)) {
				flag = true;
				System.out.println("prnNumber cannot be null 3");
				errorMap.add(new ErrorResponse("ERR-002", "prnNumber  can not be Empty"));
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
			
		 resultText = userService.ValidateUser(userName, prnNumber);
			
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
			errorMap.add(new ErrorResponse("ERROR-001", "Service API or DB unable to communicate.Please contact Admin"));
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
			System.out.println("  Inside updateLoginUserDetails 1" );
			userService.updateUserDetails(loginUserDetails);
			errorMap.add(new ErrorResponse("SAVE-001", "LoginUserDetails updated successfully."));
			successjson = new ObjectMapper().writeValueAsString(errorMap);
			logger.info("return from updateLoginUserDetails "+successjson);
		} catch (Exception e) {
			System.out.println("Exception Occured 7" + e);
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

	
	@RequestMapping(value = "/validateOTP",params = { "otpNumber", "phoneNumber" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> validateOTP(@RequestParam( value="otpNumber",  required=true ) String otpNumber, 
			@RequestParam( value="phoneNumber",   required = true ) String phoneNumber){
		//String item = null;
		String resultText = "";
		List<ErrorResponse> errorMap = new ArrayList<>();
		try {
			boolean flag = false;
			logger.info("Inside recoverPassword");
			System.out.println("Inside recoverPassword 1");
			if(Validator.nullCheck(phoneNumber)|| Validator.emptyCheck(phoneNumber)) {
				flag = true;
				System.out.println("Phone Number cannot be null 2 ");
				errorMap.add(new ErrorResponse("ERR-001", "Phone Number  can not be Empty"));
			} 
			
			/*else if(Validator.nullCheck(password)|| Validator.emptyCheck(password)) {
				flag = true;
				System.out.println("Password cannot be null 3");
				errorMap.add(new ErrorResponse("ERR-002", "Password  can not be Empty"));
 			}*/
			 
			if(flag) {
				String errorjson = new ObjectMapper().writeValueAsString(errorMap);
				//logger.info("Error "+errorjson);
				logger.error(errorjson);
				System.out.println("Validation Failed 4");
 				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorjson);
			}
			else{
			System.out.println("validateOTP  ..... 1");	
			
			resultText = userService.validateOTP(otpNumber,phoneNumber);
			if  (resultText.equals("OTP not matched") ) {
				logger.error("Validation Failed  ");
				errorMap.add(new ErrorResponse("ERROR-001", "OTP not Matched "));
				String errorjson = new ObjectMapper().writeValueAsString(errorMap);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorjson);	
			}
			else {
			System.out.println(" validateOTP    .......  4");	
			//resultText = "The Mobile Number Verified";
			errorMap.add(new ErrorResponse("ERR-002", resultText));
			String errorjson = new ObjectMapper().writeValueAsString(errorMap);
			return ResponseEntity.status(HttpStatus.OK).body(errorjson);
			} 
			
			}
			
 		} catch (Exception e) {
			System.out.println("Exception Occured 8" + e);
			logger.error("Exception Occured Validate User : " + e.getMessage());
			logger.error("Error occured"+e);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultText);
 	}	
	
	
	@RequestMapping(value = "/generateCCPToken/{mobileNumber}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?>generateCCPToken(@PathVariable String mobileNumber)  throws Exception  {
	    	  System.out.println(" Step 1 gcc ");
	     String token = null;
				/*
				 * Authentication authentication = authenticationManager.authenticate( new
				 * UsernamePasswordAuthenticationToken( loginRequest.getMobileNumber(),
				 * loginRequest.getPrnNumber() ) );
				 */
	        System.out.println(" Step 2 gcc ");
	       // SecurityContextHolder.getContext().setAuthentication(authentication);
 			try {
 				
 				//token = userService.loadCCPUserDetails(mobileNumber);
			} catch (Exception e) {
				 System.out.println(" Step 2 gcc  "+e);
				// TODO Auto-generated catch block
				 
			}
			  System.out.println(" Step 2 gcc ");
	       // final String jwt = jwtUtil.generateCCPToken(token);
	        String subject = "user123";
	        String issuer = "myApp";
	        Date now = new Date();
	        Date expiration = new Date(now.getTime() + 3600000); // 1 hour
	        String secretKey = "mySecretKey";

	        String token2 = Jwts.builder()
	                        .setSubject(mobileNumber)
	                        .setIssuer(issuer)
	                        .setIssuedAt(now)
	                        .setExpiration(expiration)
	                        .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
	                        .compact();
	        

	        return ResponseEntity.ok(new AuthenticationResponse(token2));
	    }
	 
	
}
