package com.catrionbe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPCourses;
import com.catrionbe.api.entity.DAOUser;
import com.catrionbe.api.model.CourseUpdateRequest;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.repositories.CCCoursesRepository;
 
@Service
public class CCPCourseService {

	 @Autowired
	    private CCCoursesRepository coursesRepository;
	 
 public void save1( CourseUpdateRequest objCourseUpdateRequest  ) {
	 int userId =  objCourseUpdateRequest.getUserId();
			 int courseId =  objCourseUpdateRequest.getCourseId();
	 coursesRepository.updateCourse( userId ,  courseId);
 }
 
 public CCPCourses save(CourseUpdateRequest user) {
	 CCPCourses newUser = new CCPCourses();
 
     newUser.setUserId(user.getUserId());
     newUser.setScreenId(user.getCourseId());
 
     newUser.setIsActive(user.getIsActive());
     newUser.setIsAprroved(user.getIsAprroved());
     newUser.setModifiedBy(user.getModifiedBy());
     newUser.setModifiedDate(user.getModifiedDate());
     newUser.setCreatedBy(user.getCreatedBy());
     newUser.setCreatedDate(user.getCreatedDate());
     
     
     
     return coursesRepository.save(newUser);
 }
 
 public  int fetchMaxScreenId(int userId)
 {
	 return coursesRepository.findScreenIdByUserId(userId);
 }
 
}
