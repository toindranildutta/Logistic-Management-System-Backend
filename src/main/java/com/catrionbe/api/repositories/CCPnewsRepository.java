package com.catrionbe.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.catrionbe.api.entity.CCPNews;

 
 
@Repository
public interface CCPnewsRepository extends CrudRepository<CCPNews, Integer> {
 
	  
	  List<CCPNews> findAll();
	  
	  

}
