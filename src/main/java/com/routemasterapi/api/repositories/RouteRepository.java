package com.routemasterapi.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.routemasterapi.api.entity.RouteEntity;

@Repository
public interface RouteRepository extends CrudRepository <RouteEntity, Integer>  {

	@Query(value = "select * from  indranil_route ", nativeQuery = true)
	Page<RouteEntity> listallroutesfromdb(Pageable pageable);



	
}
