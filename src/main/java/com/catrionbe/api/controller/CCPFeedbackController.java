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
import com.catrionbe.api.model.CCPSigningRequest;
import com.catrionbe.api.model.CCPUpdateFeedbackReq;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.service.CCPFeedbackService;
import com.catrionbe.api.service.CCPQuizService;
import com.catrionbe.api.service.CCPSigningService;

@RestController
@CrossOrigin
public class CCPFeedbackController {
	
	  @Autowired
	    private CCPFeedbackService  ccpFeedbackService;
	  
	 @RequestMapping(value = "/savefeedback", method = RequestMethod.POST)
	    public ResponseEntity<?> saveFeedback(@RequestBody CCPFeedbackRequest  feedbackReq) throws Exception {
	        return ResponseEntity.ok(ccpFeedbackService.saveFeedback(feedbackReq));
	    }
	 @RequestMapping(value = "/updatefeedback", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateFeedback(@RequestBody CCPUpdateFeedbackReq  feedbackReq) throws Exception {
	        return ResponseEntity.ok(ccpFeedbackService.updateFeedback(feedbackReq));
	    }
	 
	 @RequestMapping(value = "/listallactivefeedback", method = RequestMethod.GET)
	    public ResponseEntity<?> listallfeedback( @RequestParam(defaultValue = "0") final Integer pageNumber,
	            @RequestParam(defaultValue = "10") final Integer size) throws Exception {
	        return ResponseEntity.ok(ccpFeedbackService.findAllElements(pageNumber ,size));
	    }
	 @RequestMapping(value = "/listallarchivedfeedback", method = RequestMethod.GET)
	    public ResponseEntity<?> listallarchivedfeedback(@RequestParam(defaultValue = "0") final Integer pageNumber,
	            @RequestParam(defaultValue = "10") final Integer size) throws Exception {
	        return ResponseEntity.ok(ccpFeedbackService.listallarchivedfeedback(pageNumber ,size));
	    }
	 
	 @RequestMapping(value = "/listsearchfeedback", method = RequestMethod.GET)
	    public ResponseEntity<?> listsearchincident(
	    		@RequestParam(defaultValue = "test") final String searchText,
	            @RequestParam(defaultValue = "0") final Integer pageNumber,
	            @RequestParam(defaultValue = "10") final Integer size) throws Exception {
	        return ResponseEntity.ok(ccpFeedbackService.listsearchfeedback(searchText, pageNumber ,size));
	    }
	 
	 /*
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
