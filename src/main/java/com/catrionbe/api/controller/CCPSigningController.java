package com.catrionbe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.model.CCPSigningRequest;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.service.CCPQuizService;
import com.catrionbe.api.service.CCPSigningService;

@RestController
@CrossOrigin
public class CCPSigningController {
	
	  @Autowired
	    private CCPSigningService  objCCPSigningService;
	  
	 @RequestMapping(value = "/acceptundertaking", method = RequestMethod.POST)
	    public ResponseEntity<?> acceptUndertaking(@RequestBody CCPSigningRequest signObj) throws Exception {
	        return ResponseEntity.ok(objCCPSigningService.acceptUndertaking(signObj));
	    }
	 
	 @RequestMapping(value = "/acceptdeclaration", method = RequestMethod.POST)
	    public ResponseEntity<?> acceptDeclaration(@RequestBody CCPSigningRequest signObj) throws Exception {
	        return ResponseEntity.ok(objCCPSigningService.acceptDeclaration(signObj));
	    }

	 @RequestMapping(value = "/acceptpolicy", method = RequestMethod.POST)
	    public ResponseEntity<?> acceptPolicy(@RequestBody CCPSigningRequest signObj) throws Exception {
	        return ResponseEntity.ok(objCCPSigningService.acceptPolicy(signObj));
	    }
	 
	 @RequestMapping(value = "/checkundertaking", method = RequestMethod.POST)
	    public ResponseEntity<?> checkUndertaking(@RequestBody UserIdRequest userIdObj) throws Exception {
	        return ResponseEntity.ok(objCCPSigningService.checkUndertaking(userIdObj));
	    }
	 
	 @RequestMapping(value = "/checkdeclaration", method = RequestMethod.POST)
	    public ResponseEntity<?> checkDeclaration(@RequestBody UserIdRequest userIdObj) throws Exception {
	        return ResponseEntity.ok(objCCPSigningService.checkDeclaration(userIdObj));
	    }
	 
	 @RequestMapping(value = "/checkpolicy", method = RequestMethod.POST)
	    public ResponseEntity<?> checkPolicy(@RequestBody UserIdRequest userIdObj) throws Exception {
	        return ResponseEntity.ok(objCCPSigningService.checkPolicy(userIdObj));
	    }
	 
	 @RequestMapping(value = "/generatecertificatedata", method = RequestMethod.POST)
	    public ResponseEntity<?> generatecertificatedata(@RequestBody UserIdRequest userIdObj) throws Exception {
	        return ResponseEntity.ok(objCCPSigningService.generatecertificatedata(userIdObj));
	    }
	 
	 
	 
}
