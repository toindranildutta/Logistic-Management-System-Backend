package com.catrionbe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPCourses;
import com.catrionbe.api.entity.CCUQuizResult;
import com.catrionbe.api.model.CCPQuizDetailRequest;
import com.catrionbe.api.repositories.CCCoursesRepository;
import com.catrionbe.api.repositories.CCpQuizRepository;

@Service
public class CCPQuizService {
	 
	
	 @Autowired
	    private CCpQuizRepository ccpquizRepository;
	 
	 public CCUQuizResult save(CCPQuizDetailRequest user) {
		 CCUQuizResult newUser = new CCUQuizResult();
	 
	     newUser.setUserId(user.getUserId());
	     newUser.setResultId(user.getResultId());
	 
	     newUser.setIsActive(user.getIsActive());
	     newUser.setIsAprroved(user.getIsAprroved());
	     newUser.setModifiedBy(user.getModifiedBy());
	     newUser.setModifiedDate(user.getModifiedDate());
	     newUser.setCreatedBy(user.getCreatedBy());
	     newUser.setCreatedDate(user.getCreatedDate());
	     
	     
	     
	     return ccpquizRepository.save(newUser);
	 }
	 
	 public  boolean checkquizcomplete(int userId)
	 {
		 return ccpquizRepository.existsByUserId(userId);
	 }
	

}
