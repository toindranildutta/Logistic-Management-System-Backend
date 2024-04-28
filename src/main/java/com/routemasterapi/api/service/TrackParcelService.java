package com.routemasterapi.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.routemasterapi.api.entity.ParcelEntity;
import com.routemasterapi.api.entity.TrackParcelEntity;
import com.routemasterapi.api.model.TrackParcelIdRequest;
import com.routemasterapi.api.model.TrackParcelRequestBody;
import com.routemasterapi.api.repositories.ParcelRepository;
import com.routemasterapi.api.repositories.TrackParcelRepository;

@Service
public class TrackParcelService  {

	@Autowired
	private TrackParcelRepository trackParcelRepository;
	@Autowired
	private ParcelRepository parcelRepository; 

	

	public TrackParcelEntity createTrackParcel(TrackParcelRequestBody trackParcelReqBody) {
		
		Optional<ParcelEntity> optionalParcel = parcelRepository.findById(trackParcelReqBody.getParcelId());
		ParcelEntity parcel = optionalParcel.orElseThrow(() -> new Error("No parcel with this id") );

		TrackParcelEntity newTrackParcel = new TrackParcelEntity();
		newTrackParcel.setParcel(parcel);
		newTrackParcel.setEmployeeId(trackParcelReqBody.getEmployeeId());
		newTrackParcel.setApproveReject(trackParcelReqBody.getApproveReject());
		
		return trackParcelRepository.save(newTrackParcel);	 
	}

	public TrackParcelEntity updateTrackParcel(TrackParcelRequestBody trackParcelReqBody) {
		
		Optional<ParcelEntity> optionalParcel = parcelRepository.findById(trackParcelReqBody.getParcelId());
		ParcelEntity parcel = optionalParcel.orElseThrow(() -> new Error("No parcel with this id") );

		TrackParcelEntity newTrackParcel = new TrackParcelEntity();
		newTrackParcel.setTrackParcelId(trackParcelReqBody.getTrackParcelId());
		newTrackParcel.setParcel(parcel);
		newTrackParcel.setEmployeeId(trackParcelReqBody.getEmployeeId());
		newTrackParcel.setApproveReject(trackParcelReqBody.getApproveReject());
		
		return trackParcelRepository.save(newTrackParcel);		 
	}

	public Page<TrackParcelEntity> listalltrackparcelsfromdb(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber, size);
		return trackParcelRepository.listalltrackparcelsfromdb(pageable);
	}
 
	public String deleteTrackParcel(TrackParcelIdRequest trackParcelIdReq) {
		int trackParcelId = trackParcelIdReq.getTrackParcelId();
		trackParcelRepository.deleteById(trackParcelId);
		return "Record Deleted";
	}

}
