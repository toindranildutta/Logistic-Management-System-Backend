package com.catrion.catrionbe.controller;
 
import java.util.List;
import java.util.ArrayList;
import com.catrion.catrionbe.entity.CCPUserAcceptTrack;
import com.catrion.catrionbe.service.CCPUserAcceptTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.catrion.catrionbe.utils.ErrorResponse;
import com.catrion.catrionbe.utils.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController

public class CCPUserAcceptTrackController {
	
	@Autowired
	CCPUserAcceptTrackService CCPUserAcceptTrackService;
	final Logger logger = Logger.getLogger(CCPUserAcceptTrackController.class);
	 
 
	@RequestMapping(value = "/addCCPUserAcceptTrack", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addCCPUserAcceptTrack(@RequestBody CCPUserAcceptTrack CCPUserAcceptTrack) {
		List<ErrorResponse> errorMap = new ArrayList<>();
		String successjson = "";
		
		try {
			logger.info("Inside addCCPUserAcceptTrack ");
			
		 
			  
			
			CCPUserAcceptTrackService.addCCPUserAcceptTrack(CCPUserAcceptTrack);
			errorMap.add(new ErrorResponse("SAVE-001", "CCPUserAcceptTrack created successfully."));
			successjson = new ObjectMapper().writeValueAsString(errorMap);
			logger.info("return from addCCPUserAcceptTrack "+successjson);
		} catch (Exception e) {
			logger.error("Exception Occured addCCPUserAcceptTrack: " + e.getMessage());
			logger.error("Error occured"+e);
			errorMap.add(new ErrorResponse("EXEC-001", e.getMessage()));
			String errjson = null;
			try {
				errjson = new ObjectMapper().writeValueAsString(errorMap);
			}catch (Exception e1) { } 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( errjson);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(successjson);
		
	}

	@RequestMapping(value = "/updateCCPUserAcceptTrack", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateCCPUserAcceptTrack(@RequestBody CCPUserAcceptTrack CCPUserAcceptTrack) {
		
		List<ErrorResponse> errorMap = new ArrayList<>();
		String successjson = "";
		
		try {
			logger.info("Inside updateCCPUserAcceptTrack ");
			
			boolean flag = false;
			
		 
			CCPUserAcceptTrackService.updateCCPUserAcceptTrack(CCPUserAcceptTrack);
			errorMap.add(new ErrorResponse("SAVE-001", "CCPUserAcceptTrack updated successfully."));
			successjson = new ObjectMapper().writeValueAsString(errorMap);
			logger.info("return from updateCCPUserAcceptTrack "+successjson);
		} catch (Exception e) {
			logger.error("Exception Occured updateCCPUserAcceptTrack: " + e.getMessage());
			logger.error("Error occured"+e);
			errorMap.add(new ErrorResponse("EXEC-001", e.getMessage()));
			String errjson = null;
			try {
			errjson = new ObjectMapper().writeValueAsString(errorMap);
			}catch (Exception e1) { } 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( errjson);
		}
		return ResponseEntity.status(HttpStatus.OK).body(successjson);		
	}

	  

	 
	
}
