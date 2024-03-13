package com.catrionbe.api.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.catrionbe.api.entity.CCPCourses;
 
@Repository
public interface CCCoursesRepository extends CrudRepository<CCPCourses, Integer> {
 
	@Query(value = "inset into ccpcoursecompletestatus values  userId=:userId ,  courseId=:courseId", nativeQuery = true)
	void updateCourse( int userId, int courseId);
	
	@Query(value = "select max(screenId) from ccpcoursecompletestatus where  userId=:userId ", nativeQuery = true)
	String  getMaxId( int userId);
	
}
