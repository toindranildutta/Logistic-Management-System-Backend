package com.catrionbe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.model.CCPUserActivityReq;
import com.catrionbe.api.service.CCPDashboardService;
 
@RestController
@CrossOrigin
public class CCPDashboardController {

    @Autowired
    private CCPDashboardService objCCPDashboardService;
    
    
	@RequestMapping(value = "/saveuseractivity", method = RequestMethod.POST)
    public ResponseEntity<?> saveuseractivity(@RequestBody  CCPUserActivityReq objCCPUserActivityReq ) throws Exception {
        return ResponseEntity.ok(objCCPDashboardService.saveuseractivity(objCCPUserActivityReq));
    }
	
}
