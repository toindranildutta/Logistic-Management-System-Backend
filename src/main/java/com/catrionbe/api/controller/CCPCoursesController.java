package com.catrionbe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.model.CourseUpdateRequest;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.service.CCPCourseService;
import com.catrionbe.api.service.CCPNewsService;
 
@RestController
@CrossOrigin
public class CCPCoursesController {
	
	
    @Autowired
    private CCPCourseService objCCPCourseService;
    
    
    @RequestMapping(value = "/markcoursecomplete", method = RequestMethod.POST)
    public ResponseEntity<?> saveCourse(@RequestBody CourseUpdateRequest  courseUpdateRequest) throws Exception {
	
    	 return ResponseEntity.ok(objCCPCourseService.save(courseUpdateRequest));
    	
     
    }
    @RequestMapping(value = "/fetchmaxscreenId", method = RequestMethod.POST)
    public ResponseEntity<?> fetchMaxScreenId(@RequestBody UserIdRequest  userIdRequest) throws Exception {
	
    	 return ResponseEntity.ok( String.valueOf(objCCPCourseService.fetchMaxScreenId(userIdRequest.getUserId())));
    	
     
    }
    
     
}
