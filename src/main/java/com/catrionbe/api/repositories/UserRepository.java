package com.catrionbe.api.repositories;

 
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.catrionbe.api.entity.DAOUser;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<DAOUser , Long> {

	
	 @Query(value = "SELECT * from user as o where o.mobileNumber= :mobileNumber  and o.prnNumber=:prnNumber and o.isAprroved=:isAprroved", nativeQuery = true)
	    String findByUsername(@Param("mobileNumber") String mobileNumber, @Param("prnNumber") String prnNumber ,  @Param("isAprroved") String isAprroved);
		
	 
     }
