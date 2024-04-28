package com.routemasterapi.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.routemasterapi.api.entity.RouteEntity;
import com.routemasterapi.api.model.RouteIdRequest;
import com.routemasterapi.api.model.RouteRequestBody;
import com.routemasterapi.api.repositories.RouteRepository;

@Service
public class RouteService {

	@Autowired
	private RouteRepository routeRepository;
	

	public RouteEntity createRoute(RouteRequestBody routeReqBody) {

		RouteEntity newRoute = new RouteEntity();
		newRoute.setPincode(routeReqBody.getPincode());
		newRoute.setRouteName(routeReqBody.getRouteName());
		newRoute.setDescription(routeReqBody.getDescription());
		newRoute.setTotalDistance(routeReqBody.getTotalDistance());
		return routeRepository.save(newRoute);		 
	}

	public RouteEntity updateRoute(RouteRequestBody routeReqBody) {
		RouteEntity newRoute = new RouteEntity();
		newRoute.setRouteId(routeReqBody.getRouteId());
		newRoute.setPincode(routeReqBody.getPincode());
		newRoute.setRouteName(routeReqBody.getRouteName());
		newRoute.setDescription(routeReqBody.getDescription());
		newRoute.setTotalDistance(routeReqBody.getTotalDistance());
		return routeRepository.save(newRoute);		 
	}

	public Page<RouteEntity> listallroutesfromdb(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return routeRepository.listallroutesfromdb(pageable);
	}
 
	public String deleteRoute(RouteIdRequest routeIdReq) {
		int routeId= routeIdReq.getRouteId();
		routeRepository.deleteById(routeId);
		return "Record Deleted";
	}
}
