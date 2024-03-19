package com.catrionbe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPUserActivity;
import com.catrionbe.api.model.CCPUserActivityReq;
import com.catrionbe.api.repositories.CCPUserActRepository;

@Service
public class CCPDashboardService {

    @Autowired
    CCPUserActRepository    objCCPUserActRepository;

public CCPUserActivity saveuseractivity(CCPUserActivityReq objCCPUserActivityReq) {
		
		CCPUserActivity objCCPUserActivity = new CCPUserActivity();
		objCCPUserActivity.setUserId(objCCPUserActivityReq.getUserId());
		objCCPUserActivity.setActivity(objCCPUserActivityReq.getActivity());
		objCCPUserActivity.setIsActive(objCCPUserActivityReq.getIsActive());
		objCCPUserActivity.setIsAprroved(objCCPUserActivityReq.getIsAprroved());
		objCCPUserActivity.setModifiedBy(objCCPUserActivityReq.getModifiedBy());
		objCCPUserActivity.setModifiedDate(objCCPUserActivityReq.getModifiedDate());
		objCCPUserActivity.setCreatedBy(objCCPUserActivityReq.getCreatedBy());
		objCCPUserActivity.setCreatedDate(objCCPUserActivityReq.getCreatedDate());
		 objCCPUserActRepository.save(objCCPUserActivity);
		return objCCPUserActivity;
		 
	}  
}
