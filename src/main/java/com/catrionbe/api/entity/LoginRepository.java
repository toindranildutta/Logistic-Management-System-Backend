package  com.catrionbe.api.entity;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catrionbe.api.model.CCPUserDetailsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@Repository
public class LoginRepository {

 
	

 
	 private SessionFactory sessionFactory;
	
	final Logger logger = Logger.getLogger(LoginRepository.class);

	public String getUserDetailsById (int id) throws Exception {
		Session session =null;
		logger.info("Inside getUserDetailsById");
		  session =  sessionFactory.openSession();
		Query query = session.createQuery("from DAOUser e where  e.userId = :userId");// queryby id
		query.setInteger("userId", id);
		List list = query.list();
		StringWriter sw = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(sw, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in  getUserDetailsById " + e.getMessage());
			throw e;
		}

		logger.info("returning getStudentInformation with DATA " + sw.toString());
		return sw.toString();		
	} 

 
	public String ValidateUser(String mobileNumber, String prnNumber) throws Exception {
	//	System.out.println();
		Session session123 =null;
		StringWriter sw = new StringWriter();

		try {
			System.out.println("51");
			  session123 = sessionFactory.openSession();
			System.out.println("52");
			Base64.Decoder decoder = Base64.getDecoder();
			String emailIdAvailable = "false";
			String yesFlag = "Y";
			String userFound = "false";
			String passwordString = "";
			// emailIdAvailable = (checkForUniqueemailId(emailId)) ;

			// if (emailIdAvailable == "false") {
			// passwordString = getUserPassword(emailId);

			// if (passwordString != "" || passwordString !="null" ) {

			 System.out.println("passwordString 2 " + passwordString);
			 
			 
 			Query query = session123.createQuery(
					"from DAOUser as o where o.mobileNumber= :mobileNumber  and o.prnNumber=:prnNumber and o.isAprroved=:isAprroved");
			 
		   
			query.setString("mobileNumber", mobileNumber);
			query.setString("prnNumber", prnNumber);
			query.setString("yesFlag", yesFlag);
			System.out.println("2");
			List list = query.list();

			if ((list != null) && (list.size() > 0)) {
				Session session11 = sessionFactory.getCurrentSession();
				userFound = "true";
				System.out.println("userFound true " );
				Query query11 = session11.createQuery("from DAOUser as o where o.mobileNumber=:mobileNumber");
				query11.setString("mobileNumber", mobileNumber);

				List list11 = query11.list();

				String emailAddress = getUserEmail(mobileNumber);
				System.out.println("emailAddress " +emailAddress  );
				String OTPGenerated = generateSixDigitOTP();
				System.out.println("Otp gen 6 digits" +OTPGenerated );
				int otpgen = Integer.parseInt(OTPGenerated);
				updateOTP(mobileNumber, otpgen);
				if (emailAddress != "" && emailAddress != null) {
					this.sendEmail(emailAddress, OTPGenerated);
				} else {
					throw new Exception("This Email not found in the record");
				}

				//System.out.println("userFound next " );
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(sw, list11);
			} else {
				
				throw new Exception("This Email /PRN does not match in our records. Please Sign Up");

			}
			// }

		} catch (Exception e) {
			  System.out.println(" User validation failed " +e);
			logger.error("Exception in listUniversities " + e.getMessage());
		}

		logger.info("returning listUniversities with DATA " + sw.toString());
		return sw.toString();
	}

 
	public void updateOTP(String mobileNumber, int otpGenerated) {

		try {
		 System.out.println("OTP Update 1 ");
			Session session12 = sessionFactory.getCurrentSession();
			//session12.getTransaction().begin();
			Query query12 = session12.createQuery(
					"update  DAOUser as o  set o.otpGenerated=:otpGenerated  where o.mobileNumber=:mobileNumber");
			query12.setString("mobileNumber", mobileNumber);
			query12.setInteger("otpGenerated", otpGenerated);
			query12.executeUpdate();
			//session12.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			//e.printStackTrace();
		}
	}

	public String generateSixDigitOTP() {
		String random = RandomStringUtils.randomNumeric(6);
		return random;
	}

	public void sendEmail(String emailId, String OTPGenerated) {
		Properties connectionProperties = new Properties();
		/*connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
		connectionProperties.put("mail.smtp.auth", "true");
		connectionProperties.put("mail.smtp.starttls.enable", "true");
		connectionProperties.put("mail.smtp.socketFactory.port", "587");
		connectionProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		connectionProperties.put("mail.smtp.port", "587");
		*/
		connectionProperties.put("mail.smtp.host", "smtp.office365.com");
		connectionProperties.put("mail.smtp.auth", "true");
		connectionProperties.put("mail.smtp.starttls.enable", "true");
		connectionProperties.put("mail.smtp.socketFactory.port", "587");
		connectionProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		connectionProperties.put("mail.smtp.port", "587");
		
		//System.out.print("Creating the session...");
		// Create the session
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(connectionProperties,
				new javax.mail.Authenticator() { // Define the authenticator
					protected PasswordAuthentication getPasswordAuthentication() {
						//return new PasswordAuthentication("support@smartxtech.co", "Zaq12@wsX");
						return new PasswordAuthentication("support@aptpath.in", "pfzrhzqpkljvpwvc");
					}
				}); //System.out.println("done!");
		try {
			// Create the message
			Message message = new MimeMessage(session);
			// Set sender
			message.setFrom(new InternetAddress("support@aptpath.in"));
			// Set the recipients
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
			// Set message subject
			message.setSubject("Hello from Team  ");
			// Set message text
			message.setText("OTP  " + OTPGenerated);

			//System.out.print("Sending message...");
			// Send the message
			Transport.send(message);

			//System.out.println("done!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getUserEmail(String mobileNumber) throws Exception {
		//System.out.println("getUserPassword -- " + mobileNumber);
		Session session = sessionFactory.getCurrentSession();
		String emailId = "";
		try {
			String hql = "SELECT emailId  FROM DAOUser u WHERE u.mobileNumber=:mobileNumber";

			Query query = session.createQuery(hql);
			query.setString("mobileNumber", mobileNumber);
			List list = query.list();

			if ((list != null) && (list.size() > 0)) {
				emailId = (String) query.list().get(0);
				//System.out.println(" Email fetched - - "+emailId);
			} else {
				//System.out.println(" Password fetched - - null ");
				emailId = "null";
			}

		} catch (Exception e) {
			logger.error("getUserPassword Exception");
			throw e;
		}

		return emailId;
	}

	public String getUserPassword(String emailId) throws Exception {
		//System.out.println("getUserPassword -- " + emailId);
		Session session = sessionFactory.getCurrentSession();
		String userPassword = "";
		try {
			String hql = "SELECT password  FROM DAOUser u WHERE u.emailId=:emailId";

			Query query = session.createQuery(hql);
			query.setString("emailId", emailId);
			List list = query.list();

			if ((list != null) && (list.size() > 0)) {
				userPassword = (String) query.list().get(0);
				//System.out.println(" Password fetched - - "+userPassword);
			} else {
				//System.out.println(" Password fetched - - null ");
				userPassword = "null";
			}

		} catch (Exception e) {
			logger.error("getUserPassword Exception");
			throw e;
		}

		return userPassword;
	}

	public void updateUserDetails(Object DAOUser) throws Exception {
		logger.info("Inside updateUserDetails");
		try {
			//System.out.println(" Inside updateLoginUserDetails 3" );
			Session session14 = sessionFactory.getCurrentSession();
			//session14.getTransaction().begin();
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			DAOUser pojo = mapper.convertValue(DAOUser, DAOUser.class);
			//System.out.println(" Inside updateLoginUserDetails 4" );

			session14.update(pojo);
			//session14.getTransaction().commit();
		} catch (Exception e) {
			//System.out.println("Exception Occured 7" + e);
			e.printStackTrace();
			logger.error("Exception in updateUserDetails " + e.getMessage());
			throw e;
		}
		logger.info("returning updateUserDetails");
	}

	public String validateOTP(String otpNumber, String phoneNumber) throws Exception {
		String smsMessage = "Your Mobile Number Verified successfully.";
		try {
		 //System.out.println("1");
 
 			int otpGenerated = 0;
			Session session = sessionFactory.getCurrentSession();
			 //System.out.println("11");
			int  generatedOTP =0;
			try {
				 
				
				Query query = session.createQuery("SELECT u.otpGenerated  FROM DAOUser u WHERE u.mobileNumber=:mobileNumber");
				query.setString("mobileNumber", phoneNumber);
				
				List list = query.list();
				  System.out.println("12");
				if ((list != null) && (list.size() > 0)) {
					generatedOTP = (Integer) query.list().get(0);
					 System.out.println(generatedOTP);
				} else {
					generatedOTP = 0;
					//System.out.println("0000");
				}

			} catch (Exception e) {
				 System.out.println("13 "+e);
				//getUserPassword("getUserPassword Exception");
			 
			}
			//userNameAvailable = (checkForValidOTP(optpNum, phoneNumber));
			
		   System.out.println("2");
			 
				//otpGenerated = getGeneratedOTP(phoneNumber);

				// throw new Exception("Mobile Number already exists, please use different
				// mobile number");
			 
		  
		  System.out.println("Provided OTP "+otpNumber);
			 System.out.println("Generated OTP "+generatedOTP);

 
			if (Integer.parseInt(otpNumber) ==   generatedOTP ) {

				smsMessage = "OTP Verified";

			} else {
				smsMessage = "OTP not matched";
				throw new Exception("This mobile number/Last Name does not match in our records. Please Sign Up");

			}

		} catch (Exception ex) {

			  System.out.println(ex.getMessage());

		}
		return smsMessage;
	}

	public String checkForValidOTP(int otpNumber, String phoneNumber) throws Exception {
		//System.out.println("Inside checkForUniqueUsername  " + phoneNumber );
		  //System.out.println("Inside checkForUniqueUsername "+otpNumber );
			Session session = sessionFactory.getCurrentSession();
		String userFound = "false";

		try {
			//System.out.println("Inside checkForUniqueUsername  00"   );
			Query query = session
					.createQuery(" from DAOUser as o where o.mobileNumber:=mobileNumber and o.otpGenerated:= otpGenerated");
			query.setString("mobileNumber", phoneNumber);
			query.setInteger("otpGenerated", otpNumber);
			
			//System.out.println("Inside checkForUniqueUsername  0"   );
			List list = query.list();

			if ((list != null) && (list.size() > 0)) {
				//System.out.println("Inside checkForUniqueUsername  1 "   );
				userFound = "true";
				
			}
			else {
				//System.out.println("Inside checkForUniqueUsername 2 "   );
				throw new Exception("This mobile number/PRN does not match in our records. Please Sign Up");
			}
		} catch (Exception e) {
		 //System.out.println( " checkForUniqueUsername - User not found");
			//System.out.print("Wrong OTP");
			e.printStackTrace();
			logger.error("Exception in listusers " + e.getMessage());
			// throw e;
		}

		logger.info("returning listusers with DATA ");
		return userFound;
	}

	public String getGeneratedOTP(String phoneNumber) throws Exception {
	 //System.out.println("getUserPassword " + phoneNumber);
		Session session = sessionFactory.getCurrentSession();
		
		String generatedOTP = "0000";
		try {
			String hql = "SELECT u.OTPGenerated  FROM DAOUser u WHERE u.mobileNumber=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, phoneNumber);
			List list = query.list();
			if ((list != null) && (list.size() > 0)) {
				generatedOTP = (String) query.list().get(0);
				//System.out.println(generatedOTP);
			} else {
				generatedOTP = "0000";
				//System.out.println("0000");
			}

		} catch (Exception e) {
			throw new Exception("This mobile number/Last Name does not match in our records. Please Sign Up");
			//getUserPassword("getUserPassword Exception");
		 
		}

		return generatedOTP;
	}

	public void sendEmail(String emailId) {
		Properties connectionProperties = new Properties();
		connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
		connectionProperties.put("mail.smtp.auth", "true");
		connectionProperties.put("mail.smtp.starttls.enable", "true");
		connectionProperties.put("mail.smtp.socketFactory.port", "587");
		connectionProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		connectionProperties.put("mail.smtp.port", "587");
		//System.out.print("Creating the session...");
		// Create the session
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(connectionProperties,
				new javax.mail.Authenticator() { // Define the authenticator
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("support@aptpath.in", "pfzrhzqpkljvpwvc");
					}
				}); //System.out.println("done!");
		try {
			// Create the message
			Message message = new MimeMessage(session);
			// Set sender
			message.setFrom(new InternetAddress("support@aptpath.in"));
			// Set the recipients
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
			// Set message subject
			message.setSubject("Hello from Team  ");
			// Set message text
			message.setText("OTP  123456");

			//System.out.print("Sending message...");
			// Send the message
			Transport.send(message);

			//System.out.println("done!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public CCPUserDetailsResponse loadCCPUserDetails(String mobileNumber) throws Exception {
		logger.info("Inside loadCCPUserDetails");
		Session session = sessionFactory.getCurrentSession();
		StringWriter sw = new StringWriter();
		Session session11 = sessionFactory.getCurrentSession();
		//System.out.println("userFound true " );

		CCPUserDetailsResponse cCPUserDetailsResponseobj = new CCPUserDetailsResponse();
		try {
			Query query11 = session11.createQuery("from DAOUser as o where o.mobileNumber=:mobileNumber");
			//System.out.println("4---------------- " +mobileNumber);
			query11.setString("mobileNumber", mobileNumber);
			//System.out.println("5---------------- ");
			List<CCPUserDetailsResponse> list = (List<CCPUserDetailsResponse>) query11.list();
			//System.out.println(" 6 ---------------- "+list.size());
			//System.out.println(list.get(0).getEmailId());
			// cCPUserDetailsResponseobj.setFirstName(list.get(0).getFirstName());

			/*
			 * cCPUserDetailsResponseobj.setFirstName(list.get(0).getFirstName());
			 * cCPUserDetailsResponseobj.setLastName(list.get(0).getLastName());
			 * cCPUserDetailsResponseobj.setEmailId(list.get(0).getEmailId());
			 * cCPUserDetailsResponseobj.setPrnNumber(list.get(0).getPrnNumber());
			 * cCPUserDetailsResponseobj.setPassword(list.get(0).getPassword());
			 * cCPUserDetailsResponseobj.setConfPassword(list.get(0).getConfPassword());
			 * cCPUserDetailsResponseobj.setOtpGenerated(list.get(0).getOtpGenerated());
			 * cCPUserDetailsResponseobj.setOtpEntered(list.get(0).getOtpEntered());
			 * cCPUserDetailsResponseobj.setFatherName(list.get(0).getFatherName());
			 * cCPUserDetailsResponseobj.setGrandFatherName(list.get(0).getGrandFatherName()
			 * );
			 */
			// cCPUserDetailsResponseobj.setUserId(list.get(0).getUserId());
			// cCPUserDetailsResponseobj.setUserId(list.get(0).getUserId());

			// ObjectMapper mapper = new ObjectMapper();
			// mapper.writeValue(sw, list);

		} catch (Exception e) {
			//System.out.println(" Exception in List ");
			// e.printStackTrace();
		}

		logger.info("returning   with DATA " + sw.toString());
		return cCPUserDetailsResponseobj;

	}

}
