package com.catrionbe.api.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobAccessPolicy;
import com.azure.storage.blob.models.BlobSignedIdentifier;
import com.azure.storage.blob.models.PublicAccessType;
import com.catrionbe.api.entity.CCPFeedback;
import com.catrionbe.api.entity.CCPIncident;
import com.catrionbe.api.entity.CCPSigning;
import com.catrionbe.api.model.CCPFeedbackRequest;
import com.catrionbe.api.model.CCPIncidentRequest;
import com.catrionbe.api.model.CCPSigningRequest;
import com.catrionbe.api.model.CCPUpdateFeedbackReq;
import com.catrionbe.api.model.IncidentUpdateRequest;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.repositories.CCPFeedbackRepsitory;
import com.catrionbe.api.repositories.CCPIncidentRepsitory;
import com.catrionbe.api.repositories.CCPSigningRepsitory;
import com.catrionbe.api.repositories.UserDao;

@Service
public class CCPIncidentService {

	 private static final String connectionString = "AccountName=cyberportal;AccountKey=9fKUci4CZQEhTL8eISLWZgwWpq+wqEg93XGtu+DcHTqT7/O7cfmqooeeuTrDh/uAWsw/xQzuRVkD+AStwk+iOg==;EndpointSuffix=core.windows.net";
	 private static final String containerName = "portal-image";  // Azure blob storage won't allow capital letters in container name
	 private static final String blobName = "test.jpg";
	 private static final String localFilePath = "D:\\Microservices\\logo.jpg";
	 private static SecureRandom random = new SecureRandom();
	 private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	    private static final String NUMERIC = "0123456789";
	    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
	    
	    
	    
	@Autowired
    private CCPIncidentRepsitory objCCPIncidentRepsitory;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	 
	 


	public String updateFeedback(CCPUpdateFeedbackReq feedbackReq) throws Exception {
		String message = "Feedback Status Updated";
		
		try {
			objCCPIncidentRepsitory.updateIncidentStatus(feedbackReq.getFeedbackId() , feedbackReq.getFeedbackStatus());
		} catch (Exception e) {
 			 throw new  Exception("Unable to Update" );
		}
		 
		return message;
	}
	
	public Page<CCPIncident> listallactiveincidents(int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
		  return ( objCCPIncidentRepsitory.listallactiveincidents(pageable));
		
		 }

	public Page<CCPIncident> listallarchivedfeedback(int pageNumber,int size) {
		Pageable pageable = PageRequest.of(pageNumber, size );
		  return ( objCCPIncidentRepsitory.listallarchivedfeedback(pageable));
		 }
	public static String generateFileNames() {
	    String result = "";
	    String dic =ALPHA_CAPS + ALPHA;
	    int len = 10;
	    for (int i = 0; i < len; i++) {
	        int index = random.nextInt(dic.length());
	        result += dic.charAt(index);
	    }
	    return result;
	    }
	public CCPIncident saveIncident(CCPIncidentRequest incidentReq) {
		CCPIncident objCCPIncident = new CCPIncident();
		objCCPIncident.setSeverityId(incidentReq.getSeverityId());
		objCCPIncident.setUserId(incidentReq.getUserId());
		objCCPIncident.setStatusId(incidentReq.getStatusId());
		objCCPIncident.setDescription(incidentReq.getDescription());
		objCCPIncident.setIncidentSubject(incidentReq.getIncidentSubject());
		String randomImageName =  this.generateFileNames();
		if  (  incidentReq.getImage().equals("" )|| incidentReq.equals("null")) {
			objCCPIncident.setAttachementUrl("test.jpg");
		}
		else {
			String attachUrl = this.getAttachementUrl(incidentReq.getImage() ,  randomImageName);
			System.out.println("attachUrl  - - - - -  >    "+attachUrl);
			objCCPIncident.setAttachementUrl(attachUrl);
		}
		
		objCCPIncident.setIsActive(incidentReq.getIsActive());
		objCCPIncident.setIsAprroved(incidentReq.getIsAprroved());
		objCCPIncident.setModifiedBy(incidentReq.getModifiedBy());
		objCCPIncident.setModifiedDate(incidentReq.getModifiedDate());
		objCCPIncident.setCreatedBy(incidentReq.getCreatedBy());
		objCCPIncident.setCreatedDate(incidentReq.getCreatedDate());
		
		
		
		
		return null;
	}


	private String  getAttachementUrl(byte[] image , String randomImageName) {
		 BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	      final BlobContainerClient devContainer = blobServiceClient.createBlobContainerIfNotExists(containerName);

	        BlobSignedIdentifier accessPolicy = new BlobSignedIdentifier()
	                .setId("policy1")  // Identifier for the policy
	                .setAccessPolicy(new BlobAccessPolicy().setStartsOn(OffsetDateTime.now()).setExpiresOn(OffsetDateTime.now().plusDays(7)).setPermissions("r"));


	        devContainer.setAccessPolicy(PublicAccessType.BLOB, List.of(accessPolicy));

	        BlobClient blobClient = devContainer.getBlobClient(blobName);

	        InputStream is = new ByteArrayInputStream(image);
	        try (FileInputStream fileInputStream = new FileInputStream(localFilePath)) {
	            blobClient.upload(is, new File(randomImageName).length(), true);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		return  "https://cyberportal.blob.core.windows.net/portal-image/"+randomImageName;		
	}


	public String updateIncident(IncidentUpdateRequest incidentUpdateReq) throws Exception {
	String message = "Incident  Status Updated";
		
		try {
			objCCPIncidentRepsitory.updateIncidentStatus(incidentUpdateReq.getIncidentId() , incidentUpdateReq.getStatusId());
		} catch (Exception e) {
 			 throw new  Exception("Unable to Update" );
		}
		 
		return message;
		
	}

	
	/*
	public CCPSigning  acceptUndertaking(CCPSigningRequest  signObj) {
		String username =  objCCPSigningRepsitory.fetchFirstandLastName ( signObj.getUserId());
		System.out.println(" username  "+ username);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		CCPSigning objCCPSigning = new CCPSigning();
		objCCPSigning.setUserId(signObj.getUserId());
		objCCPSigning.setStatusId(signObj.getStatusId());
		objCCPSigning.setSigningItem("UNDERTAKING");
		objCCPSigning.setTokenVal(bcryptEncoder.encode(username));
		objCCPSigning.setDisplayDate((dateFormat.format(date)).toString()+" UTC");
		objCCPSigning.setFullName( username);
		objCCPSigning.setIsActive(signObj.getIsActive());
		objCCPSigning.setIsAprroved(signObj.getIsAprroved());
		objCCPSigning.setModifiedBy(signObj.getModifiedBy());
		objCCPSigning.setModifiedDate(signObj.getModifiedDate());
		objCCPSigning.setCreatedBy(signObj.getCreatedBy());
		objCCPSigning.setCreatedDate(signObj.getCreatedDate());
		objCCPSigningRepsitory.save(objCCPSigning);
		return objCCPSigning;
	}

	public Object acceptDeclaration(CCPSigningRequest signObj) {
		String username =  objCCPSigningRepsitory.fetchFirstandLastName ( signObj.getUserId());
		System.out.println(" username  "+ username);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		CCPSigning objCCPSigning = new CCPSigning();
		objCCPSigning.setUserId(signObj.getUserId());
		objCCPSigning.setStatusId(signObj.getStatusId());
		objCCPSigning.setSigningItem("DECLARATION");
		objCCPSigning.setTokenVal(bcryptEncoder.encode(username));
		objCCPSigning.setDisplayDate((dateFormat.format(date)).toString()+" UTC");
		objCCPSigning.setFullName( username);
		objCCPSigning.setIsActive(signObj.getIsActive());
		objCCPSigning.setIsAprroved(signObj.getIsAprroved());
		objCCPSigning.setModifiedBy(signObj.getModifiedBy());
		objCCPSigning.setModifiedDate(signObj.getModifiedDate());
		objCCPSigning.setCreatedBy(signObj.getCreatedBy());
		objCCPSigning.setCreatedDate(signObj.getCreatedDate());
		objCCPSigningRepsitory.save(objCCPSigning);
		return objCCPSigning;
	}

	public Object acceptPolicy(CCPSigningRequest signObj) {
		String username =  objCCPSigningRepsitory.fetchFirstandLastName ( signObj.getUserId());
		System.out.println(" username  "+ username);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		CCPSigning objCCPSigning = new CCPSigning();
		objCCPSigning.setUserId(signObj.getUserId());
		objCCPSigning.setStatusId(signObj.getStatusId());
		objCCPSigning.setSigningItem("POLICY");
		objCCPSigning.setTokenVal(bcryptEncoder.encode(username));
		objCCPSigning.setDisplayDate((dateFormat.format(date)).toString()+" UTC");
		objCCPSigning.setFullName( username);
		objCCPSigning.setIsActive(signObj.getIsActive());
		objCCPSigning.setIsAprroved(signObj.getIsAprroved());
		objCCPSigning.setModifiedBy(signObj.getModifiedBy());
		objCCPSigning.setModifiedDate(signObj.getModifiedDate());
		objCCPSigning.setCreatedBy(signObj.getCreatedBy());
		objCCPSigning.setCreatedDate(signObj.getCreatedDate());
		objCCPSigningRepsitory.save(objCCPSigning);
		return objCCPSigning;
	}

	
	public boolean checkUndertaking ( UserIdRequest userIdObj) {
		int userId = userIdObj.getUserId();
		boolean undertakeAccepted = false;
        int count = objCCPSigningRepsitory.checkUndertaking( userId);
		if (count > 0) {
			undertakeAccepted = true;
		}
		return undertakeAccepted;
	}

	public Object checkDeclaration(UserIdRequest userIdObj) {
		boolean declAccepted = false;
		int userId = userIdObj.getUserId();
        int count = objCCPSigningRepsitory.checkUndertaking( userId);
		if (count > 0) {
			declAccepted = true;
		}
		return declAccepted;
	}

	public Object checkPolicy(UserIdRequest userIdObj) {
		boolean policyAccepted = false;
		int userId = userIdObj.getUserId();
        int count = objCCPSigningRepsitory.checkUndertaking( userId);
		if (count > 0) {
			policyAccepted = true;
		}
		return policyAccepted;
	}
	*/
}
