package com.catrionbe.api.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPCertificate;
import com.catrionbe.api.entity.CCPSigning;
import com.catrionbe.api.model.CCPSigningRequest;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.repositories.CCPCertificateRepsitory;
import com.catrionbe.api.repositories.CCPSigningRepsitory;
import com.catrionbe.api.repositories.UserDao;

@Service
public class CCPSigningService {

	@Autowired
    private CCPSigningRepsitory objCCPSigningRepsitory;
	@Autowired
	private CCPCertificateRepsitory objCCPCertificateRepsitory;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	 
	
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
		
		CCPCertificate objCCPCertificate = new CCPCertificate();
		objCCPCertificate.setUserId(signObj.getUserId());
		objCCPCertificate.setFullName(username);
		int certum =  Integer.parseInt(this.generateCertificateNumber());
		objCCPCertificate.setCertificateNumber(  certum );
		objCCPCertificate.setIsActive(signObj.getIsActive());
		objCCPCertificate.setIsAprroved(signObj.getIsAprroved());
		objCCPCertificate.setModifiedBy(signObj.getModifiedBy());
		objCCPCertificate.setModifiedDate(signObj.getModifiedDate());
		objCCPCertificate.setCreatedBy(signObj.getCreatedBy());
		objCCPCertificate.setCreatedDate(signObj.getCreatedDate());
		objCCPCertificateRepsitory.save(objCCPCertificate);
		return objCCPSigning;
	}

	public String generateCertificateNumber() {
		String random = RandomStringUtils.randomNumeric(10);
		return random;
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

	public String generatecertificatedata(UserIdRequest userIdObj  ) {
		
		
		return objCCPSigningRepsitory.generatecertificatedata (userIdObj.getUserId()) ;
		 
	}

	
}
