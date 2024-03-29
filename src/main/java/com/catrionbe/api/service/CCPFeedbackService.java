package com.catrionbe.api.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.catrionbe.api.entity.CCPFeedback;
import com.catrionbe.api.entity.CCPIncident;
import com.catrionbe.api.entity.CCPSigning;
import com.catrionbe.api.model.CCPFeedbackRequest;
import com.catrionbe.api.model.CCPIncidentRequest;
import com.catrionbe.api.model.CCPSigningRequest;
import com.catrionbe.api.model.CCPUpdateFeedbackReq;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.repositories.CCPFeedbackRepsitory;
import com.catrionbe.api.repositories.CCPSigningRepsitory;
import com.catrionbe.api.repositories.UserDao;

@Service
public class CCPFeedbackService {

	@Autowired
    private CCPFeedbackRepsitory objCCPFeedbackRepsitory;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	 
	
	public CCPFeedback saveFeedback(CCPFeedbackRequest feedbackReq) {		
		CCPFeedback objCCPFeedback = new CCPFeedback();
		objCCPFeedback.setUserId(feedbackReq.getUserId());
		objCCPFeedback.setFeedbackId(feedbackReq.getFeedbackId());
		objCCPFeedback.setFeedbackType(feedbackReq.getFeedbackType());
		objCCPFeedback.setFeedbackStatus(feedbackReq.getFeedbackStatus() );
		objCCPFeedback.setDescription( feedbackReq.getDescription() );
		objCCPFeedback.setUserPhone(feedbackReq.getUserPhone());
		objCCPFeedback.setRating(feedbackReq.getRating());
		objCCPFeedback.setIsActive(feedbackReq.getIsActive());
		objCCPFeedback.setIsAprroved(feedbackReq.getIsAprroved());
		objCCPFeedback.setModifiedBy(feedbackReq.getModifiedBy());
		objCCPFeedback.setModifiedDate(feedbackReq.getModifiedDate());
		objCCPFeedback.setCreatedBy(feedbackReq.getCreatedBy());
		objCCPFeedback.setCreatedDate(feedbackReq.getCreatedDate());
		objCCPFeedbackRepsitory.save(objCCPFeedback);
		return objCCPFeedback;
	}


	public String updateFeedback(CCPUpdateFeedbackReq feedbackReq) throws Exception {
		String message = "Feedback Status Updated";
		
		try {
			objCCPFeedbackRepsitory.updateStatus(feedbackReq.getFeedbackId() , feedbackReq.getFeedbackStatus());
		} catch (Exception e) {
 			 throw new  Exception("Unable to Update" );
		}
		 
		return message;
	}
	
	public Page<CCPFeedback> findAllElements(int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
		  return ( objCCPFeedbackRepsitory.findAllActiveFeedbacks(pageable));
		 }

	public Page<CCPFeedback> listallarchivedfeedback(int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
		  return (  objCCPFeedbackRepsitory.listallarchivedfeedback(pageable));
		 }

 
	public Page<CCPFeedback> listsearchfeedback(String searchText, Integer pageNumber, Integer size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, size );
		  return		objCCPFeedbackRepsitory.listsearchincident(searchText,pageable); 
	}
 
  
 
	 
	public Page<CCPFeedback> searchallactivefeedback(String searchText, Integer pageNumber, Integer size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, size );
		  return		objCCPFeedbackRepsitory.searchallactivefeedback(searchText,pageable); 
	}
	
	public Page<CCPFeedback> searchallarchivedfeedback(String searchText, Integer pageNumber, Integer size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, size );
		  return		objCCPFeedbackRepsitory.searchallarchivedfeedback(searchText,pageable); 
	}
	
	
	 
}
