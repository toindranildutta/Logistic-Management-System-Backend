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
import java.util.ArrayList;
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
import com.catrionbe.api.entity.DAOUser;
import com.catrionbe.api.model.CCPFeedbackRequest;
import com.catrionbe.api.model.CCPIncidentRequest;
import com.catrionbe.api.model.CCPSigningRequest;
import com.catrionbe.api.model.CCPUpdateFeedbackReq;
import com.catrionbe.api.model.ImageIdResponse;
import com.catrionbe.api.model.IncidentUpdateRequest;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.model.allstaffsmap;
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
		objCCPIncident.setAttachementUrl(incidentReq.getImage());
		String randomImageName = this.generateFileNames();
	 System.out.println("Step 1 ");
	
	 /*if ( incidentReq.getImage()==null   ) {
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
		}*/

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
                .containerName("portal-image")
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
	 
 
	public List<ImageIdResponse> uploadImage(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		  String constr="DefaultEndpointsProtocol=https;AccountName=cyberportal;AccountKey=9fKUci4CZQEhTL8eISLWZgwWpq+wqEg93XGtu+DcHTqT7/O7cfmqooeeuTrDh/uAWsw/xQzuRVkD+AStwk+iOg==;EndpointSuffix=core.windows.net";


	        BlobContainerClient container = new BlobContainerClientBuilder()
	                .connectionString(constr)
	                .containerName("portal-image")
	                .buildClient();


	        BlobClient blob=container.getBlobClient(file.getOriginalFilename());
 	        blob.upload(file.getInputStream(),file.getSize(),true);

	        List<ImageIdResponse> imagemap = new ArrayList<ImageIdResponse> () ;
	        ImageIdResponse ImageIdResponseobj= new ImageIdResponse();
	        String imageName=url+container+"/"+file.getOriginalFilename();
	        ImageIdResponseobj.setImageName(imageName);
	        //List<ImageIdResponse> response=url+container+"/"+file.getOriginalFilename();
	        imagemap.add(0,ImageIdResponseobj);
	        return imagemap;
	}

 
	public Page<CCPIncident> listsearchincident(String searchText, Integer pageNumber, Integer size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, size );
		  return		objCCPIncidentRepsitory.listsearchincident(searchText,pageable); 
	}
 
	public Page<CCPIncident> searchallactiveincidents(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return (objCCPIncidentRepsitory.searchallactiveincidents(pageable));

	}
	
	public Page<CCPIncident> searchallarchivedincidents(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return (objCCPIncidentRepsitory.searchallarchivedincidents(pageable));

	}
	
	
}
