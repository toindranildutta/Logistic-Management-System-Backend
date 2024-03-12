package com.catrionbe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.model.CCPQuizDetailRequest;
import com.catrionbe.api.model.CourseUpdateRequest;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.service.CCPCourseService;
import com.catrionbe.api.service.CCPQuizService;

@RestController
@CrossOrigin
public class CCPQuizController {

	   @Autowired
	    private CCPQuizService objCCPQuizService;
	    
	    
	    @RequestMapping(value = "/markquizcomplete", method = RequestMethod.POST)
	    public ResponseEntity<?> saveCourse(@RequestBody CCPQuizDetailRequest  courseUpdateRequest) throws Exception {
		
	    	 return ResponseEntity.ok(objCCPQuizService.save(courseUpdateRequest));
	    	
	     
	    }
	    @RequestMapping(value = "/checkquizcomplete", method = RequestMethod.POST)
	    public ResponseEntity<?> checkquizcomplete(@RequestBody UserIdRequest  userIdRequest) throws Exception {
		
	    	 return ResponseEntity.ok( String.valueOf(objCCPQuizService.checkquizcomplete(userIdRequest.getUserId())));
	    	
	     
	    }
	    
}
