package com.catrion.catrionbe.repository;

import java.io.StringWriter;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catrion.catrionbe.dto.LoginResponseDTO;
import com.catrion.catrionbe.entity.LoginUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class LoginRepository {

	@Autowired
	private SessionFactory sessionFactory;
	final Logger logger = Logger.getLogger(LoginRepository.class);

	
	public String ValidateUser(String mobileNumber, String prnNumber) throws Exception {
		logger.info("Inside listUniversities");
		Session session = this.sessionFactory.openSession();	
		StringWriter sw = new StringWriter();

		try {
		Base64.Decoder decoder = Base64.getDecoder();  
		String emailIdAvailable = "false";	
		String yesFlag = "Y";
		String userFound = "false";
		String passwordString ="";
		// emailIdAvailable =  (checkForUniqueemailId(emailId)) ;
		
		// if (emailIdAvailable == "false") {
	 //		 passwordString = getUserPassword(emailId);
		 
 	 //	if (passwordString != "" || passwordString !="null"  ) {
		
  		Query query = session.createQuery("from LoginUserDetails as o where o.mobileNumber= :mobileNumber  and o.prnNumber=:prnNumber and o.isAprroved=:yesFlag");
 		query.setString("mobileNumber",mobileNumber);
 		query.setString("prnNumber",prnNumber); 
 		query.setString("yesFlag",yesFlag);
		
		List list = query.list();
		
		if ((list != null) && (list.size() > 0)) {
			Session session11 = this.sessionFactory.openSession();	
			userFound= "true";
			System.out.println("userFound    true   "  );
		Query query11 = session11.createQuery("from LoginUserDetails as o where o.mobileNumber=:mobileNumber");
		query11.setString("mobileNumber",mobileNumber);
		 
		List list11 = query11.list();
		System.out.println("userFound    next    "  );
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(sw, list11);
			} else {
				throw new Exception("This Email /PRN does not match in our records. Please Sign Up");  

			}	
	//	}		 
		
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Exception in listUniversities " + e.getMessage()); 
		}
		 
		logger.info("returning listUniversities with DATA " + sw.toString());	 
			return sw.toString();		
	}
	public String getUserPassword(String emailId) throws Exception {
		System.out.println("getUserPassword   -- " + emailId);
			Session session = this.sessionFactory.openSession();		
			String userPassword = "";
		try {
			String hql ="SELECT password  FROM LoginUserDetails u WHERE u.emailId=:emailId";
	                 
			Query query = session.createQuery(hql);
			query.setString("emailId",emailId);
			List list = query.list();
			
			if ((list != null) && (list.size() > 0)) {
				userPassword =  (String) query.list().get(0);
				System.out.println(" Password fetched  - -    "+userPassword);
	 		} 		 
			else {
				System.out.println(" Password fetched  - -     null  ");
				userPassword="null";
			}
			
			} catch (Exception e) {
				logger.error("getUserPassword Exception");
			throw e;
		}

		return userPassword;
	}
	public void updateUserDetails(Object   loginUserDetails) throws Exception {
		logger.info("Inside updateUserDetails");
		try {
			System.out.println("  Inside updateLoginUserDetails 3" );
			Session session = this.sessionFactory.openSession();
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			LoginUserDetails pojo = mapper.convertValue(loginUserDetails, LoginUserDetails.class);
			 
		 
			System.out.println("  Inside updateLoginUserDetails 4" );
 		 
			session.update(pojo);
		} catch (Exception e) {
			System.out.println("Exception Occured 7" + e);
			e.printStackTrace();
			logger.error("Exception in updateUserDetails " + e.getMessage());
			throw e;
		}
		logger.info("returning updateUserDetails");
	} 
	
	
	public String validateOTP (String otpNumber, String phoneNumber) throws Exception {
		String smsMessage= "Your Mobile Number Verified successfully.";
		try {
			System.out.println("1");

			 
			
			String username = phoneNumber;
			String userNameAvailable = "false";	
			
			int optpNum  = Integer.parseInt(otpNumber);
			String otpGenerated = "";
 			  userNameAvailable =  (checkForValidOTP(optpNum , phoneNumber)) ;
			System.out.println("2");
			if (userNameAvailable == "false") {
				smsMessage="Mobile Number not found";
				 
				 // throw new Exception("Mobile Number not found");  
			}
        else {
        	 otpGenerated = getGeneratedOTP(phoneNumber);
				 
				// throw new Exception("Mobile Number already exists, please use different mobile number");  
			 }
			
			
			System.out.println("Generated OTP "+otpGenerated);
			
			
			System.out.println(" otpGenerated    "+otpGenerated); 
			
			if (otpGenerated == otpNumber && userNameAvailable == "true") {
				 
			 
				smsMessage="OTP Verified";
				
			}
			else {
				smsMessage="OTP not matched";
				throw new Exception("This mobile number/Last Name does not match in our records. Please Sign Up");
				
			}
			

		} catch(Exception ex) {

		System.out.println(ex.getMessage());

		}
		return smsMessage;	
	}
	
	public String checkForValidOTP (int otpNumber, String phoneNumber) throws Exception{
		logger.info("Inside checkForUniqueUsername");
		System.out.println("Inside checkForUniqueUsername");
		Session session = this.sessionFactory.getCurrentSession();	
		String userFound = "false";

		try {
 			
 		Query query = session.createQuery("  from StudentAdmission as o where o.userName=? and o.OTPGenerated=?   ");
		query.setParameter(0,phoneNumber);
		query.setParameter(1,otpNumber);
		
 		List list = query.list();
		
		if ((list != null) && (list.size() > 0)) {
			userFound= "true";
 		} 		 
		} catch (Exception e) {
			//System.out.println( " checkForUniqueUsername - User not found");
			e.printStackTrace();
			logger.error("Exception in listusers " + e.getMessage());
			//throw e;
		}
		 
		logger.info("returning listusers with DATA ");	 
 		return userFound;		
	}
	
	public String getGeneratedOTP(String phoneNumber) throws Exception {
		System.out.println("getUserPassword " + phoneNumber);
 		Session session = this.sessionFactory.getCurrentSession();		
 		String generatedOTP =  "0000";
		try {
			String hql ="SELECT u.OTPGenerated  FROM StudentAdmission u WHERE u.userName=? ";
	                 
			Query query = session.createQuery(hql);
			query.setParameter(0,phoneNumber);
			List list = query.list();
			
			if ((list != null) && (list.size() > 0)) {
				generatedOTP =  (String) query.list().get(0);
	 		} 		 
			else {
				generatedOTP= "0000";
			}
			
 		} catch (Exception e) {
 			getUserPassword("getUserPassword Exception");
			throw e;
		}

		return generatedOTP;
	}
	
	public String recoverPassword(String phoneNumber , String password) throws Exception {
		String smsMessage= "New Passord :";
		try {
			System.out.println("1");

			String gatewayUrl = "https://www.smsgatewayhub.com/api/mt/SendSMS?";

			String APIKey = "kfm8QUjyBk6GzEIhbIHGEA";

 			String senderid = "TESTIN";

			String channel = "2";

			String DCS = "0";
			
			String flashsms = "0";
			System.out.println("2");
			 
				 
			 
				
				Session session = this.sessionFactory.getCurrentSession();
				
				Query query = session.createSQLQuery("update  SCHOOL_admission  set password ='"+password+"' where userName = '" + phoneNumber + "'");
				query.executeUpdate();
				
				 
				smsMessage="Successfully reset the password "; 
			 
			

		} catch(Exception ex) {

		System.out.println(ex.getMessage());
		smsMessage="reset password not happening";
		}
		return smsMessage;	
	}
	
}
