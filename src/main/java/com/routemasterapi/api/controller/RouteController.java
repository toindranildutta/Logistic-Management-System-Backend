package com.routemasterapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routemasterapi.api.model.RouteIdRequest;
import com.routemasterapi.api.model.RouteRequestBody;
import com.routemasterapi.api.service.RouteService;
  
@RestController
@CrossOrigin
public class RouteController {
	
	@Autowired
	private RouteService routeService;	
	
	@RequestMapping(value = "/createRoute", method = RequestMethod.POST)
	public ResponseEntity<?> createRoute(@RequestBody RouteRequestBody routeReqBody) throws Exception {
		return ResponseEntity.ok(routeService.createRoute(routeReqBody));
	}
	
	@RequestMapping(value = "/updateRoute", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRoute(@RequestBody RouteRequestBody routeReqBody) throws Exception {
		return ResponseEntity.ok(routeService.updateRoute(routeReqBody));
	}		
	
	@RequestMapping(value = "/listAllRoutes", method = RequestMethod.GET)
	public ResponseEntity<?> listAllRoutes(@RequestParam(defaultValue = "0") final Integer pageNumber,
			@RequestParam(defaultValue = "10") final Integer size) throws Exception {
		return ResponseEntity.ok(routeService.listallroutesfromdb(pageNumber, size));
	}		
	
	@RequestMapping(value = "/deleteRoute", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRoute(@RequestBody RouteIdRequest routeIdRequest) throws Exception {
		return ResponseEntity.ok(routeService.deleteRoute(routeIdRequest));
	}		
	
}
