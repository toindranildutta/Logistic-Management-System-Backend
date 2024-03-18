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
import com.catrionbe.api.entity.CCPSigning;

@Repository
public interface  CCPFeedbackRepsitory extends CrudRepository<CCPFeedback, Integer> {

	@Query(value = "select   CONCAT( firstName ,' ', lastName ) from user  where   userId =:userId", nativeQuery = true)
	public String fetchFirstandLastName (int userId) ;

	@Query(value = "select   count( *  ) from ccpsigning  where   userId =:userId AND statusId=1 AND signingItem='UNDERTAKING' ", nativeQuery = true)
	  public int  checkUndertaking( int userId);

	@Query(value = "select   count( *  ) from ccpsigning  where   userId =:userId AND statusId=1 AND signingItem='DECLARATION' ", nativeQuery = true)
	public int  checkDeclaration(int userId);

	@Query(value = "select   count( *  ) from ccpsigning  where   userId =:userId AND  statusId=1 AND signingItem='POLICY' ", nativeQuery = true)
	public int checkPolicy(int userId);

	@Modifying
    @Transactional
    @Query(value = "update ccpfeedback set feedbackStatus=:feedbackStatus where   feedbackId=:feedbackId", nativeQuery = true)
	public void updateStatus(int feedbackId, int feedbackStatus);

    @Query(value = "select * from  ccpfeedback   where   feedbackStatus=0", nativeQuery = true)
	  Page<CCPFeedback> findAllActiveFeedbacks( final Pageable pageable);

    @Query(value = "select * from  ccpfeedback   where   feedbackStatus=1", nativeQuery = true)
	  Page<CCPFeedback> listallarchivedfeedback(final Pageable pageable);

	
}
