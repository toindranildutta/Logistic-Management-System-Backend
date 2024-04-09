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
public interface  CCPDeptRepsitory extends CrudRepository<CCPIncident, Integer> {

	@Query(value = "select  deptId from ccpdepartments where  deptName=:deptName ", nativeQuery = true)
	int   getDeptId( String  deptName);
  
}
