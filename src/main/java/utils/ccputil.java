package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ccputil {

	public void sendEmail(String emailId, String OTPGenerated) {
		Properties connectionProperties = new Properties();
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
						return new PasswordAuthentication("support@aptpath.in", "pfzrhzqpkljvpwvc");
					}
				}); //System.out.println("done!");
		try {
			MimeMessage message = new MimeMessage(session);
			BodyPart messageBodyPart = new MimeBodyPart();
			MimeMultipart multipart = new MimeMultipart("related");
			// Create the message
		//	Message message = new MimeMessage(session);
			// Set sender
			message.setFrom(new InternetAddress("support@aptpath.in"));
			// Set the recipients
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
			// Set message subject
			message.setSubject("CATRION Cybersecurity Portal Verification Code");
			// Set message text
			DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date currentDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			String msg = "<html><head></head><body>"
					+"<p>It looks like you are trying to sign in to the CATRION Cybersecurity Portal:</p>"
					+"<p>"+"Date : "+dateFormat.format(date)+"</p>"
					+"<p>Enter the digits below on the sign in page to confirm your identity:</p>"
					+"<h2>"+OTPGenerated+"</h2>"
					+"<p>Yours securely,</p>"
					+"<p>Cybersecurity Team</p>"
					+"<p>CATRION</p>"
					;
					
			//message.setText( "It looks like you are trying to sign in to the CATRION Cybersecurity Portal: ");
			//message.setText("<br>");
			//message.setText(msg);
			messageBodyPart.setContent(msg, "text/html; charset=utf-8");
			// add it
			multipart.addBodyPart(messageBodyPart);
			// second part (the image)
			messageBodyPart = new MimeBodyPart();
		 
			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);
			// put everything together
			message.setContent(multipart);
			message.setContent(msg, "text/html; charset=utf-8");

			//System.out.print("Sending message...");
			// Send the message
			Transport.send(message);

			//System.out.println("done!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sendWelcomeEmail(String emailId, String prnNumber, String fn, String un) {
		Properties connectionProperties = new Properties();
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
						return new PasswordAuthentication("support@aptpath.in", "pfzrhzqpkljvpwvc");
					}
				}); //System.out.println("done!");
		try {
			MimeMessage message = new MimeMessage(session);
			BodyPart messageBodyPart = new MimeBodyPart();
			MimeMultipart multipart = new MimeMultipart("related");
			// Create the message
		//	Message message = new MimeMessage(session);
			// Set sender
			message.setFrom(new InternetAddress("support@aptpath.in"));
			// Set the recipients
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
			// Set message subject
			message.setSubject("CATRION Cybersecurity Portal Welcome Kit");
			// Set message text
			DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date currentDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			String msg = "<html><head></head><body>"
					+"<h2> Hello "+fn+"</h2>"
					+"<p>Welcome to the CATRION Cybersecurity Portal! </p>"
					+"<p>Your account has been created with the following credentials. </p>"
					
					+"<h2>PRN Number :  "+prnNumber+"</h2>"
					+"<h2>Mobile Number :  "+un+"</h2>"
					+"<p>You will need this information to login to our portal. You are requested to complete the Course on Cybersecurity as well as take the assessment from our Cybersecurity Portal. </p>"
					+"<p>Here is the link to the CATRION Cybersecurity Portal. </p>"
					+"<h3>https://catrion-cyber-dev.spotlabs.in/login</h3>"
					+"<p>Yours securely,</p>"
					+"<p>Cybersecurity Team</p>"
					+"<p>CATRION</p>"
					+"<p>"+"Date : "+dateFormat.format(date)+"</p>"
					;
					
			//message.setText( "It looks like you are trying to sign in to the CATRION Cybersecurity Portal: ");
			//message.setText("<br>");
			//message.setText(msg);
			messageBodyPart.setContent(msg, "text/html; charset=utf-8");
			// add it
			multipart.addBodyPart(messageBodyPart);
			// second part (the image)
			messageBodyPart = new MimeBodyPart();
		 
			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);
			// put everything together
			message.setContent(multipart);
			message.setContent(msg, "text/html; charset=utf-8");

			//System.out.print("Sending message...");
			// Send the message
			Transport.send(message);

			//System.out.println("done!");

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
    
}
