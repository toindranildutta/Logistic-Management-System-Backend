package com.catrion.catrionbe.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  com.catrion.catrionbe.repository.CCPUserAcceptTrackRepository;
import com.catrion.catrionbe.entity.CCPUserAcceptTrack;

@Service("CCPUserAcceptTrackService")
public class CCPUserAcceptTrackService {
	
	@Autowired
	CCPUserAcceptTrackRepository objCCPUserAcceptTrackRepository;
	
 

	@Transactional
	public void addCCPUserAcceptTrack(Object CCPUserAcceptTrack) throws Exception {
		objCCPUserAcceptTrackRepository.addCCPUserAcceptTrack(CCPUserAcceptTrack);
	}

	@Transactional
	public void updateCCPUserAcceptTrack(Object CCPUserAcceptTrack) throws Exception {
		objCCPUserAcceptTrackRepository.updateCCPUserAcceptTrack(CCPUserAcceptTrack);
	}

 

}
