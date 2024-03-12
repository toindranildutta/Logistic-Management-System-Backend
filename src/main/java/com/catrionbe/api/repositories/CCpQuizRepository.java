package com.catrionbe.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.catrionbe.api.entity.CCUQuizResult;

 
@Repository
public interface CCpQuizRepository extends CrudRepository<CCUQuizResult, Integer> {

	@Query(value = "inset into ccpcoursecompletestatus values  userId=:userId ,  courseId=:courseId", nativeQuery = true)
	void updateQuiz( int userId, int courseId);
	
	//@Query(value = "select *  from ccpquizresulttracker where  userId=:userId ", nativeQuery = true)
	boolean  existsByUserId( int userId);

 

 	
	
	
}
