package com.routemasterapi.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.routemasterapi.api.entity.TrackParcelEntity;
 
@Repository
public interface  TrackParcelRepository extends CrudRepository<TrackParcelEntity,Integer> {

	@Query(value = "select * from  indranil_trackparcel ", nativeQuery = true)
	Page<TrackParcelEntity> listalltrackparcelsfromdb(Pageable pageable);



}
