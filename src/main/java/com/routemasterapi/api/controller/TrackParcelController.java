package com.routemasterapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routemasterapi.api.model.TrackParcelRequestBody;
import com.routemasterapi.api.model.TrackParcelIdRequest;
import com.routemasterapi.api.service.TrackParcelService;
  
@RestController
@CrossOrigin
public class TrackParcelController {
	
	@Autowired
	private TrackParcelService trackParcelService;	
	
	@RequestMapping(value = "/createTrackParcel", method = RequestMethod.POST)
	public ResponseEntity<?> createTrackParcel(@RequestBody TrackParcelRequestBody trackParcelReqBody) throws Exception {
		return ResponseEntity.ok(trackParcelService.createTrackParcel(trackParcelReqBody));
	}
	
	@RequestMapping(value = "/updateTrackParcel", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTrackParcel(@RequestBody TrackParcelRequestBody trackParcelReqBody) throws Exception {
		return ResponseEntity.ok(trackParcelService.updateTrackParcel(trackParcelReqBody));
	}		
	
	@RequestMapping(value = "/listAllTrackParcels", method = RequestMethod.GET)
	public ResponseEntity<?> listAllTrackParcels(@RequestParam(defaultValue = "0") final Integer pageNumber,
			@RequestParam(defaultValue = "10") final Integer size) throws Exception {
		return ResponseEntity.ok(trackParcelService.listalltrackparcelsfromdb(pageNumber, size));
	}		
	
	@RequestMapping(value = "/deleteTrackParcel", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrackParcel(@RequestBody TrackParcelIdRequest trackParcelIdRequest) throws Exception {
		return ResponseEntity.ok(trackParcelService.deleteTrackParcel(trackParcelIdRequest));
	}		
	
}
