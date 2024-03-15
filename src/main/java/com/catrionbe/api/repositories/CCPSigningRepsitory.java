package com.catrionbe.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.catrionbe.api.entity.CCPCourses;
import com.catrionbe.api.entity.CCPSigning;

@Repository
public interface  CCPSigningRepsitory extends CrudRepository<CCPSigning, Integer> {

	@Query(value = "select   CONCAT( firstName ,' ', lastName ) from user  where   userId =:userId", nativeQuery = true)
	public String fetchFirstandLastName (int userId) ;

}
