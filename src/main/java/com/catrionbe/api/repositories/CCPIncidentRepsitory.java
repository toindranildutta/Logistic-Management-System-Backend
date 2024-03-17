package com.catrionbe.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.catrionbe.api.entity.CCPCourses;
import com.catrionbe.api.entity.CCPFeedback;
import com.catrionbe.api.entity.CCPIncident;
import com.catrionbe.api.entity.CCPSigning;

@Repository
public interface  CCPIncidentRepsitory extends CrudRepository<CCPIncident, Integer> {

	 
	@Modifying
    @Transactional
    @Query(value = "update ccpincident set statusId=:statusId where   incidentId=:incidentId", nativeQuery = true)
	public void updateIncidentStatus(int incidentId, int statusId);

    @Query(value = "select * from  ccpincident   where   statusId=1", nativeQuery = true)
	public List<CCPIncident> findAllActiveIncidents();

    @Query(value = "select * from  ccpfeedback   where   feedbackStatus=1", nativeQuery = true)
	public List<CCPIncident> listallarchivedfeedback();

 
	
}
