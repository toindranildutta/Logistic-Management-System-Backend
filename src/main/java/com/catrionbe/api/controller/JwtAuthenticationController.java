package com.catrionbe.api.controller;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.catrionbe.api.config.JwtTokenUtil;
import com.catrionbe.api.entity.DAOUser;
import com.catrionbe.api.model.EmailIdRequest;
import com.catrionbe.api.model.JwtRequest;
import com.catrionbe.api.model.JwtResponse;
import com.catrionbe.api.model.JwtResponsewithEmail;
import com.catrionbe.api.model.OtpRequest;
import com.catrionbe.api.model.UpdateUserDTO;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.service.JwtUserDetailsService;

import utils.ccputil;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
Expose a POST API /authenticate using the JwtAuthenticationController. The POST API gets username and password in the
body- Using Spring Authentication Manager we authenticate the username and password.If the credentials are valid,
a JWT token is created using the JWTTokenUtil and provided to the client.
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
    	ccputil.sendWelcomeEmail(user.getEmailId(), user.getPrnNumber());
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
     System.out.println("Inside  / Authenticate ");
        try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (Exception e) {
		 
			e.printStackTrace();
		}

        System.out.println(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        //JwtResponsewithEmail JwtResponsewithEmailObj = new JwtResponsewithEmail();
        final String token = jwtTokenUtil.generateToken(userDetails);
        String username= userDetails.getUsername();
        String OTPGenerated = generateSixDigitOTP();
        System.out.println(userDetails.getUsername());
        String email = userDetailsService.loadUserByUsername1(username);
        //JwtResponsewithEmailObj.setEmailId(email);
        //JwtResponsewithEmailObj.setToken(token);
    	if (email != "" && email != null) {
    		System.out.println(email);
			ccputil ccputil = new ccputil();
			 userDetailsService.updateUserByUsername(username,Integer.parseInt(OTPGenerated));
			ccputil.sendEmail(email, OTPGenerated);
		} else {
			throw new Exception("This Email not found in the record");
		}
        return ResponseEntity.ok(new JwtResponsewithEmail(token,email));
    }

    @RequestMapping(value = "/validateotp", method = RequestMethod.POST)
    public ResponseEntity<?> validateOtp(@RequestBody OtpRequest  otpValidationRequest) throws Exception {
    	System.out.println(" Hit the Controller  0 00  0 0");
    	 
    	 String username = null; 
    	 
    	DAOUser userDetails = (DAOUser) userDetailsService.autheticateOtp( otpValidationRequest.getEmailId(), otpValidationRequest.getOtpgenerated());
    	
 
        
        String jsonString = new com.google.gson.Gson().toJson(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString.getBytes("UTF-8"));		
        //return ResponseEntity.ok(new JwtResponse(token));
    }
    
    @RequestMapping(value = "/fetchuserdetails", method = RequestMethod.POST)
    public ResponseEntity<?> fetchUserDetailsByEmail(@RequestBody EmailIdRequest  emailId) throws Exception {
    	System.out.println(" Hit the Controller  0 00  20");
    	 
     
    	 
    	DAOUser userDetails = (DAOUser) userDetailsService.fetchUserDetails( emailId.getEmailId());
    	
 
        
        String jsonString = new com.google.gson.Gson().toJson(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString.getBytes("UTF-8"));		
        //return ResponseEntity.ok(new JwtResponse(token));
    }
    

    private void authenticate(String username, String password) throws Exception {
        try {
        	
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
	public String generateSixDigitOTP() {
		String random = RandomStringUtils.randomNumeric(6);
		return random;
	}
    
	
	@RequestMapping(value = "/generateccptoken", method = RequestMethod.POST)
    public ResponseEntity<?> generateCCPToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

     //   authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

          String token = null;
		try {
			final UserDetails userDetails = userDetailsService
			        .loadUserByUsername(authenticationRequest.getUsername());
			token = jwtTokenUtil.generateToken(userDetails);
  			System.out.println(userDetails.getUsername()); 			 
		} catch (UsernameNotFoundException e) {
			throw new Exception("User not found in the record");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("User not found in the record");
		}
 
        return ResponseEntity.ok(new JwtResponse(token));
    }

	 @RequestMapping(value = "/totalcatrionusers", method = RequestMethod.GET)
	    public ResponseEntity<?> totalCatrionUsers( ) throws Exception {
	    	 return ResponseEntity.ok(  (userDetailsService.totalCatrionUsers( )));
	    }
	  
	  
	  @RequestMapping(value = "/loginusers", method = RequestMethod.GET)
	    public ResponseEntity<?> loginUsers( ) throws Exception {
	    	 return ResponseEntity.ok(  (userDetailsService.loginUsers( )));
	    }
	  
	  @RequestMapping(value = "/commencedcyberbasic", method = RequestMethod.GET)
	    public ResponseEntity<?> commencedCyberBasic( ) throws Exception {
	    	 return ResponseEntity.ok(  (userDetailsService.commencedCyberBasic( )));
	    }
	  
	  @RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO user) throws Exception {
	        return ResponseEntity.ok(userDetailsService.update(user));
	    }
    
	  @RequestMapping(value = "/updateuserasarchived", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateuserasarchived(@RequestBody UserIdRequest userObj) throws Exception {
	        return ResponseEntity.ok(userDetailsService.updateuserasarchived(userObj));
	    }
	  
	  @RequestMapping(value = "/listallactiveusers", method = RequestMethod.GET)
	    public ResponseEntity<?> listallactiveusers() throws Exception {
	        return ResponseEntity.ok(userDetailsService.listallactiveusers());
	    }
	  
	  @RequestMapping(value = "/listallnewusers", method = RequestMethod.GET)
	    public ResponseEntity<?> listallnewusers() throws Exception {
	        return ResponseEntity.ok(userDetailsService.listallnewusers());
	    }
	  @RequestMapping(value = "/listallarchivedusers", method = RequestMethod.GET)
	    public ResponseEntity<?> listallarchivedusers() throws Exception {
	        return ResponseEntity.ok(userDetailsService.listallarchivedusers());
	    }
}
