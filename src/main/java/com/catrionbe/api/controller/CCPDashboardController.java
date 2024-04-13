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
	public ResponseEntity<?> saveuseractivity(@RequestBody CCPUserActivityReq objCCPUserActivityReq) throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.saveuseractivity(objCCPUserActivityReq));
	}

	@RequestMapping(value = "/accessbydate", method = RequestMethod.GET)
	public ResponseEntity<?> accessbydate() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.accessbydate());
	}

	@RequestMapping(value = "/accessbycertificate", method = RequestMethod.GET)
	public ResponseEntity<?> accessbycertificate() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.accessbycertificate());
	}

	@RequestMapping(value = "/allstaffsegmentprogresschart", method = RequestMethod.GET)
	public ResponseEntity<?> allstaffsegmentprogress() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.allstaffsegmentprogress());
	}

	@RequestMapping(value = "/allstaffsegmentprogresstable", method = RequestMethod.GET)
	public ResponseEntity<?> allstaffsegmentprogresstable() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.allstaffsegmentprogresstable());
	}

	@RequestMapping(value = "/onlystaffsegmentprogresschart", method = RequestMethod.GET)
	public ResponseEntity<?> onlystaffsegmentprogresschart() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.onlystaffsegmentprogresschart());
	}

	@RequestMapping(value = "/onlystaffsegmentprogresstable", method = RequestMethod.GET)
	public ResponseEntity<?> onlystaffsegmentprogresstable() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.onlystaffsegmentprogresstable());
	}

	@RequestMapping(value = "/nonstaffsegmentprogresschart", method = RequestMethod.GET)
	public ResponseEntity<?> nonstaffsegmentprogresschart() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.nonstaffsegmentprogresschart());
	}

	@RequestMapping(value = "/nonstaffsegmentprogresstable", method = RequestMethod.GET)
	public ResponseEntity<?> nonstaffsegmentprogresstable() throws Exception {
		return ResponseEntity.ok(objCCPDashboardService.nonstaffsegmentprogresstable());
	}

}
