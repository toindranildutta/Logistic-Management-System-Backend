package com.catrionbe.api.repositories;

import java.util.Date;
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
import com.catrionbe.api.entity.CCPUserActivity;

@Repository
public interface  CCPUserActRepository extends CrudRepository<CCPUserActivity, Integer> {

	@Query(value = "select count(*) from ccpcertificate where createdDate=:formattedDate", nativeQuery = true)
	int  accessbydate( String formattedDate);

	@Query(value = "select count(*) from ccpuseractivity where createdDate=:result", nativeQuery = true)
	int accessbydate1(String result);

	@Query(value = "select count(* ) FROM  user b  where    b.deptId=:deptId", nativeQuery = true)
	int getTotalStaffsDept(int deptId);

	@Query(value = "select count(* ) FROM ccpcertificate a , user b  where a.userId = b.userId AND b.deptId=:deptId", nativeQuery = true)
	int getTotalStaffsCompleted(int deptId);

	@Query(value = "select count(* ) FROM  user b  where    b.locationId=:locationId AND b.emailId LIKE '%CATRION%'", nativeQuery = true)
	int getTotalStaffloc(int locationId);

	@Query(value = "select count(* ) FROM ccpcertificate a , user b  where a.userId = b.userId AND b.emailId LIKE '%CATRION%'", nativeQuery = true)
	int getTotalStaffsCompletedloc(int locationId);

 
	
}
