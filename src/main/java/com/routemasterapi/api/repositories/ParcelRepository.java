package com.routemasterapi.api.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.routemasterapi.api.entity.ParcelEntity;
 
@Repository
public interface  ParcelRepository extends CrudRepository<ParcelEntity,Integer> {

	@Query(value = "select * from  indranil_Parcel ", nativeQuery = true)
	Page<ParcelEntity> listallparcelsfromdb(Pageable pageable);
	
	
	@Query(value = "SELECT * FROM indranil_parcel WHERE customerId = :customerId", nativeQuery = true)
	Page<ParcelEntity> listcustomerparcelstatusfromdb(@Param("customerId") int customerId, Pageable pageable);

	@Query(value = "SELECT * FROM indranil_parcel WHERE addedOn >= :oneMonthAgo ", nativeQuery = true)
	Page<ParcelEntity> listonemonthparcelsfromdb(@Param("oneMonthAgo") LocalDate oneMonthAgo, Pageable pageable);
	
	@Query(value = "SELECT * FROM indranil_parcel WHERE addedOn >= :oneMonthAgo AND parcelStatus = 'Delayed' ", nativeQuery = true)
	Page<ParcelEntity> listonemonthdelayedparcelsfromdb(@Param("oneMonthAgo") LocalDate oneMonthAgo, Pageable pageable);

}
