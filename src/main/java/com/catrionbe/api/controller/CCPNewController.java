package com.catrionbe.api.controller;

import com.catrionbe.api.model.CCPNewsIdRequest;
import com.catrionbe.api.model.CCPNewsRequest;
import com.catrionbe.api.service.CCPNewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
@CrossOrigin
public class CCPNewController {

 

    @Autowired
    private CCPNewsService objCCPNewsService;

    @RequestMapping(value = "/fetchnews", method = RequestMethod.GET)
    public ResponseEntity<?> fetchnews( ) throws Exception {    	
        return ResponseEntity.ok(objCCPNewsService.viewAllNews());
    }
    
    @RequestMapping(value = "/savenews", method = RequestMethod.POST)
    public ResponseEntity<?> saveCourse(@RequestBody CCPNewsRequest  courseUpdateRequest) throws Exception {	
    	 return ResponseEntity.ok(objCCPNewsService.save(courseUpdateRequest));     
    }
  
    @RequestMapping(value = "/updatenews", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCourse(@RequestBody CCPNewsRequest  courseUpdateRequest) throws Exception {	
    	 return ResponseEntity.ok(objCCPNewsService.update(courseUpdateRequest));     
    }
    
    
    @RequestMapping(value = "/deletenews", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNews(@RequestBody CCPNewsIdRequest  courseUpdateRequest) throws Exception {	
    	 return ResponseEntity.ok(objCCPNewsService.deleteNews(courseUpdateRequest.getNewsId()));     
    }
    
}