package com.catrionbe.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.catrionbe.api.entity.CCPCertificate;
import com.catrionbe.api.entity.CCPCourses;
import com.catrionbe.api.entity.CCPSigning;

@Repository
public interface  CCPCertificateRepsitory extends CrudRepository<CCPCertificate, Integer> {

	@Query(value = "select     *   from ccpcertificate   where   userId =:userId ORDER BY userId DESC LIMIT 1", nativeQuery = true)
	public CCPCertificate  generatecertificatedata(int userId);

	@Query(value = "  select     *   from ccpcertificate  a , user b  where   a.userId=b.userId AND b.prnNumber=:prNumber", nativeQuery = true)
	public CCPCertificate displaycertificate(String prNumber);
	
}
