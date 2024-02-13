package com.catrion.catrionbe.repository;

import java.io.StringWriter;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catrion.catrionbe.dto.LoginResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class LoginRepository {

	@Autowired
	private SessionFactory sessionFactory;
	final Logger logger = Logger.getLogger(LoginRepository.class);

	
	public String ValidateUser(String emailId, String password) throws Exception {
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
			 passwordString = getUserPassword(emailId);
		 
			 System.out.println("passwordString  1   " + passwordString); 
		if (passwordString != "" || passwordString !="null"  ) {
		
			System.out.println("passwordString  2 " + passwordString);
			System.out.println("emailId " + emailId);
		Query query = session.createQuery("from LoginUserDetails as o where o.emailId= :emailId  and o.password=:password and o.isAprroved=:yesFlag");
 		query.setString("emailId",emailId);
 		query.setString("password",password);
 		query.setString("yesFlag",yesFlag);
		
		List list = query.list();
		
		if ((list != null) && (list.size() > 0)) {
			Session session11 = this.sessionFactory.openSession();	
			userFound= "true";
			System.out.println("userFound    true   "  );
		Query query11 = session11.createQuery("from LoginUserDetails as o where o.emailId=:emailId");
		query11.setString("emailId",emailId);
		 
		List list11 = query11.list();
		System.out.println("userFound    next    "  );
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(sw, list11);
			} else {
				throw new Exception("This mobile number/password does not match in our records. Please Sign Up");  

			}	
		}		 
		
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

	
}
