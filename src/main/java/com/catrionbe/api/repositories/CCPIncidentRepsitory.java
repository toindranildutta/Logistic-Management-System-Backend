package com.catrionbe.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select * from  ccpincident   where   statusId=0", nativeQuery = true)
	  Page<CCPIncident> listallactiveincidents(final Pageable pageable);

    @Query(value = "select * from  ccpincident   where   statusId=0", nativeQuery = true)
	  Page<CCPIncident> listallarchivedfeedback(final Pageable pageable);

    @Query(value = "select * from  ccpincident   where   statusId=1", nativeQuery = true)
	  Page<CCPIncident> listallarchivedincidents( final Pageable pageable);

    @Query(value = "SELECT *  FROM ccpincident  WHERE  lower(description) LIKE  %:searchText% OR lower(incidentSubject) LIKE  %:searchText% ", nativeQuery = true)
	public Page<CCPIncident> listsearchincident(String searchText, Pageable pageable);

 

	@Query(value = "select * from  ccpincident   where   statusId=0 AND  lower(description) LIKE  %:searchText% OR lower(incidentSubject) LIKE  %:searchText%", nativeQuery = true)
	public Page<CCPIncident> searchallarchivedincidents(String searchText, Pageable pageable);
   
	@Query(value = "select * from  ccpincident   where   statusId=1 AND  lower(description) LIKE  %:searchText% OR lower(incidentSubject) LIKE  %:searchText%", nativeQuery = true)
	public Page<CCPIncident> searchallactiveincidents(String searchText, Pageable pageable);

 
	
}
