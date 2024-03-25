package com.catrionbe.api.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.azure.storage.common.StorageSharedKeyCredential;
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
import com.azure.storage.blob.BlobContainerClient;


import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
public class CCPIncidentService {

	private static final String connectionString = "AccountName=cyberportal;AccountKey=9fKUci4CZQEhTL8eISLWZgwWpq+wqEg93XGtu+DcHTqT7/O7cfmqooeeuTrDh/uAWsw/xQzuRVkD+AStwk+iOg==;EndpointSuffix=core.windows.net";
	private static final String containerName = "portal-image"; // Azure blob storage won't allow capital letters in
																// container name
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

	
	 @Value("${azure.storage.account-name}")
	    private String accountName;

	    @Value("${azure.storage.account-key}")
	    private String accountKey;

	    @Value("${azure.storage.blob-endpoint}")
	    private String url;

	    @Value("${azure.storage.container-name}")
	    private String container;
	    
 

	public String updateFeedback(CCPUpdateFeedbackReq feedbackReq) throws Exception {
		String message = "Feedback Status Updated";

		try {
			objCCPIncidentRepsitory.updateIncidentStatus(feedbackReq.getFeedbackId(), feedbackReq.getFeedbackStatus());
		} catch (Exception e) {
			throw new Exception("Unable to Update");
		}

		return message;
	}

	public Page<CCPIncident> listallactiveincidents(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return (objCCPIncidentRepsitory.listallactiveincidents(pageable));

	}

	public Page<CCPIncident> listallarchivedfeedback(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return (objCCPIncidentRepsitory.listallarchivedfeedback(pageable));
	}

	public static String generateFileNames() {
		String result = "";
		String dic = ALPHA_CAPS + ALPHA;
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
		String randomImageName = this.generateFileNames();
	 System.out.println("Step 1 ");
		if ( incidentReq.getImage()==null   ) {
			System.out.println("Step  -1  ");
			objCCPIncident.setAttachementUrl("test.jpg");
			
		} else {
			System.out.println("Step 2 ");
			String attachUrl = null;
			try {
				attachUrl = this.uploadBlob(incidentReq.getImage(), randomImageName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("attachUrl  - - - - -  >    " + attachUrl);
			objCCPIncident.setAttachementUrl(attachUrl);
		}

		objCCPIncident.setIsActive(incidentReq.getIsActive());
		objCCPIncident.setIsAprroved(incidentReq.getIsAprroved());
		objCCPIncident.setModifiedBy(incidentReq.getModifiedBy());
		objCCPIncident.setModifiedDate(incidentReq.getModifiedDate());
		objCCPIncident.setCreatedBy(incidentReq.getCreatedBy());
		objCCPIncident.setCreatedDate(incidentReq.getCreatedDate());

		objCCPIncidentRepsitory.save(objCCPIncident);

		return objCCPIncident;
	}

	 

	
	@Bean
    public BlobContainerClient getBlobContainerClient(){

        BlobContainerClient blobContainerClient = getBlobServiceClient().getBlobContainerClient(container);

        return blobContainerClient;

    }
	
	@Bean
    public BlobServiceClient getBlobServiceClient() {

    StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);
    String endpoint = String.format(Locale.ROOT, url, accountName);
    BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();
    return storageClient;
    }
	
    public String createBlobContainer (String blobContainerName) {

        BlobContainerClient blobContainerClient = getBlobServiceClient().createBlobContainer(blobContainerName);

        return  blobContainerClient.getBlobContainerUrl();
    }

    public String uploadBlob(MultipartFile file , String randomImageName) throws IOException {

      /*  BlockBlobClient blobClient = getBlobContainerClient().getBlobClient(file.getOriginalFilename()).getBlockBlobClient();

        File fileData=convertMultiPartToFile(file );
        InputStream dataStream = new ByteArrayInputStream(file.getBytes());

         
        blobClient.upload(dataStream, file.getSize());

        dataStream.close();
        */
        String constr="";


        BlobContainerClient container = new BlobContainerClientBuilder()
                .connectionString(constr)
                .containerName("upload")
                .buildClient();


        BlobClient blob=container.getBlobClient(file.getOriginalFilename());

        blob.upload(file.getInputStream(),file.getSize(),true);


        String response=url+container+"/"+file.getOriginalFilename();
        
        return response;
    }
    
    public File convertMultiPartToFile(MultipartFile file ) throws IOException
    {
        File convFile = new File( file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }
    
	public String uploadImage(MultipartFile image, String randomImageName) {

		try {
			String blobName = generateFileNames();
			// Get a blob reference for a text file.
			//CloudBlockBlob blob = container.getBlockBlobReference(blobName);
			//new FileOutputStream(randomImageName).write(image);
			// Upload some text into the blob.
		//	blob.upload(image.getInputStream(), image.getSize()); // Mani - Enable this to test file upload
		} catch (Exception e) {
			// Output the stack trace.
			e.printStackTrace();
		}
		return "Success";
	}

	public String updateIncident(IncidentUpdateRequest incidentUpdateReq) throws Exception {
		String message = "Incident  Status Updated";

		try {
			objCCPIncidentRepsitory.updateIncidentStatus(incidentUpdateReq.getIncidentId(),
					incidentUpdateReq.getStatusId());
		} catch (Exception e) {
			throw new Exception("Unable to Update");
		}

		return message;

	}

 

	public Page<CCPIncident> listallarchivedincidents(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return (objCCPIncidentRepsitory.listallarchivedincidents(pageable));

	}
	/*
	 * public CCPSigning acceptUndertaking(CCPSigningRequest signObj) { String
	 * username = objCCPSigningRepsitory.fetchFirstandLastName (
	 * signObj.getUserId()); System.out.println(" username  "+ username); DateFormat
	 * dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); Date date = new Date();
	 * 
	 * System.out.println(dateFormat.format(date));
	 * 
	 * CCPSigning objCCPSigning = new CCPSigning();
	 * objCCPSigning.setUserId(signObj.getUserId());
	 * objCCPSigning.setStatusId(signObj.getStatusId());
	 * objCCPSigning.setSigningItem("UNDERTAKING");
	 * objCCPSigning.setTokenVal(bcryptEncoder.encode(username));
	 * objCCPSigning.setDisplayDate((dateFormat.format(date)).toString()+" UTC");
	 * objCCPSigning.setFullName( username);
	 * objCCPSigning.setIsActive(signObj.getIsActive());
	 * objCCPSigning.setIsAprroved(signObj.getIsAprroved());
	 * objCCPSigning.setModifiedBy(signObj.getModifiedBy());
	 * objCCPSigning.setModifiedDate(signObj.getModifiedDate());
	 * objCCPSigning.setCreatedBy(signObj.getCreatedBy());
	 * objCCPSigning.setCreatedDate(signObj.getCreatedDate());
	 * objCCPSigningRepsitory.save(objCCPSigning); return objCCPSigning; }
	 * 
	 * public Object acceptDeclaration(CCPSigningRequest signObj) { String username
	 * = objCCPSigningRepsitory.fetchFirstandLastName ( signObj.getUserId());
	 * System.out.println(" username  "+ username); DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); Date date = new Date();
	 * 
	 * System.out.println(dateFormat.format(date));
	 * 
	 * CCPSigning objCCPSigning = new CCPSigning();
	 * objCCPSigning.setUserId(signObj.getUserId());
	 * objCCPSigning.setStatusId(signObj.getStatusId());
	 * objCCPSigning.setSigningItem("DECLARATION");
	 * objCCPSigning.setTokenVal(bcryptEncoder.encode(username));
	 * objCCPSigning.setDisplayDate((dateFormat.format(date)).toString()+" UTC");
	 * objCCPSigning.setFullName( username);
	 * objCCPSigning.setIsActive(signObj.getIsActive());
	 * objCCPSigning.setIsAprroved(signObj.getIsAprroved());
	 * objCCPSigning.setModifiedBy(signObj.getModifiedBy());
	 * objCCPSigning.setModifiedDate(signObj.getModifiedDate());
	 * objCCPSigning.setCreatedBy(signObj.getCreatedBy());
	 * objCCPSigning.setCreatedDate(signObj.getCreatedDate());
	 * objCCPSigningRepsitory.save(objCCPSigning); return objCCPSigning; }
	 * 
	 * public Object acceptPolicy(CCPSigningRequest signObj) { String username =
	 * objCCPSigningRepsitory.fetchFirstandLastName ( signObj.getUserId());
	 * System.out.println(" username  "+ username); DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); Date date = new Date();
	 * 
	 * System.out.println(dateFormat.format(date));
	 * 
	 * CCPSigning objCCPSigning = new CCPSigning();
	 * objCCPSigning.setUserId(signObj.getUserId());
	 * objCCPSigning.setStatusId(signObj.getStatusId());
	 * objCCPSigning.setSigningItem("POLICY");
	 * objCCPSigning.setTokenVal(bcryptEncoder.encode(username));
	 * objCCPSigning.setDisplayDate((dateFormat.format(date)).toString()+" UTC");
	 * objCCPSigning.setFullName( username);
	 * objCCPSigning.setIsActive(signObj.getIsActive());
	 * objCCPSigning.setIsAprroved(signObj.getIsAprroved());
	 * objCCPSigning.setModifiedBy(signObj.getModifiedBy());
	 * objCCPSigning.setModifiedDate(signObj.getModifiedDate());
	 * objCCPSigning.setCreatedBy(signObj.getCreatedBy());
	 * objCCPSigning.setCreatedDate(signObj.getCreatedDate());
	 * objCCPSigningRepsitory.save(objCCPSigning); return objCCPSigning; }
	 * 
	 * 
	 * public boolean checkUndertaking ( UserIdRequest userIdObj) { int userId =
	 * userIdObj.getUserId(); boolean undertakeAccepted = false; int count =
	 * objCCPSigningRepsitory.checkUndertaking( userId); if (count > 0) {
	 * undertakeAccepted = true; } return undertakeAccepted; }
	 * 
	 * public Object checkDeclaration(UserIdRequest userIdObj) { boolean
	 * declAccepted = false; int userId = userIdObj.getUserId(); int count =
	 * objCCPSigningRepsitory.checkUndertaking( userId); if (count > 0) {
	 * declAccepted = true; } return declAccepted; }
	 * 
	 * public Object checkPolicy(UserIdRequest userIdObj) { boolean policyAccepted =
	 * false; int userId = userIdObj.getUserId(); int count =
	 * objCCPSigningRepsitory.checkUndertaking( userId); if (count > 0) {
	 * policyAccepted = true; } return policyAccepted; }
	 */
}
