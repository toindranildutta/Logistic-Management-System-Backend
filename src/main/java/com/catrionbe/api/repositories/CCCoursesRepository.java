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


	@Query(value = "select count( * ) from ccpcoursecompletestatus  a , user b where  a.userId = b.userId AND a.screenId=21 AND b.emailId LIKE '%CATRION%'", nativeQuery = true)
	String  getCountcompletedWithWorkEmail();
	
	@Query(value = "select count( * ) from ccpcoursecompletestatus  a , user b where  a.userId = b.userId AND a.screenId=21", nativeQuery = true)
	String completedCCPBasic();
	
	@Query(value = "select count( * ) from ccpcoursecompletestatus  a , user b where  a.userId = b.userId AND a.screenId=21", nativeQuery = true)
	String  getCountcompletedWithMobilePhone();

		
	@Query(value = "select count( * ) from ccpcoursecompletestatus   where    screenId < 21", nativeQuery = true)
	  String  reminder();
	
	@Query(value = "select count( * ) from ccpcoursecompletestatus   where    screenId < 1", nativeQuery = true)
	  String  loginButStarted() ;
 
	@Query(value = "select count( * ) from ccpcoursecompletestatus   where    screenId < 20", nativeQuery = true)
	  String  startedButNotCompleted(); 
}
