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

	@Query(value = "select   * from ccpsigning  where   userId =:userId AND statusId=1 AND signingItem='UNDERTAKING' ORDER BY userId DESC LIMIT 1 ", nativeQuery = true)
	  public String  checkUndertaking( int userId);

	@Query(value = "select   * from ccpsigning  where   userId =:userId AND statusId=1 AND signingItem='DECLARATION' ORDER BY userId DESC LIMIT 1 ", nativeQuery = true)
	public String  checkDeclaration(int userId);

	@Query(value = "select  *  from ccpsigning  where   userId =:userId AND  statusId=1 AND signingItem='POLICY'  ORDER BY userId DESC LIMIT 1", nativeQuery = true)
	public String checkPolicy(int userId);

	@Query(value = "select     *   from ccpcertificate   where   userId =:userId ", nativeQuery = true)
	public String generatecertificatedata(int userId);

}
