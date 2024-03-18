package com.catrionbe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.model.CCPFeedbackRequest;
import com.catrionbe.api.model.CCPIncidentRequest;
import com.catrionbe.api.model.CCPSigningRequest;
import com.catrionbe.api.model.CCPUpdateFeedbackReq;
import com.catrionbe.api.model.IncidentUpdateRequest;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.service.CCPFeedbackService;
import com.catrionbe.api.service.CCPIncidentService;
import com.catrionbe.api.service.CCPQuizService;
import com.catrionbe.api.service.CCPSigningService;

@RestController
@CrossOrigin
public class CCPIncidentController {
	
	  @Autowired
	    private CCPIncidentService  ccpIncidentService;
	  
	 @RequestMapping(value = "/saveincident", method = RequestMethod.POST)
	    public ResponseEntity<?> saveIncident(@RequestBody CCPIncidentRequest  incidentReq) throws Exception {
	        return ResponseEntity.ok(ccpIncidentService.saveIncident(incidentReq));
	    }
	
	 @RequestMapping(value = "/updateincidentstatus", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateIncident(@RequestBody IncidentUpdateRequest  incidentUpdateReq) throws Exception {
	        return ResponseEntity.ok(ccpIncidentService.updateIncident(incidentUpdateReq));
	    }
	 
	 @RequestMapping(value = "/listallactiveincidents", method = RequestMethod.GET)
	    public ResponseEntity<?> listallactiveincidents( @RequestParam(defaultValue = "0") final Integer pageNumber,
	            @RequestParam(defaultValue = "10") final Integer size) throws Exception {
	        return ResponseEntity.ok(ccpIncidentService.listallactiveincidents(pageNumber ,size));
	    }
	 
	 /*
	  *  
	 
	 @RequestMapping(value = "/listallactivefeedback", method = RequestMethod.GET)
	    public ResponseEntity<?> listallfeedback() throws Exception {
	        return ResponseEntity.ok(ccpFeedbackService.findAllElements());
	    }
	 @RequestMapping(value = "/listallarchivedfeedback", method = RequestMethod.GET)
	    public ResponseEntity<?> listallarchivedfeedback() throws Exception {
	        return ResponseEntity.ok(ccpFeedbackService.listallarchivedfeedback());
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
	 */
}
