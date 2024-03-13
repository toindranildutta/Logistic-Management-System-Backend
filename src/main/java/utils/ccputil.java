package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    
}
