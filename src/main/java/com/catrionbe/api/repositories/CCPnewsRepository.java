package com.catrionbe.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.catrionbe.api.entity.CCPNews;

 
 
@Repository
public interface CCPnewsRepository extends CrudRepository<CCPNews, Integer> {
 
	  
	  List<CCPNews> findAll();
	  
	  CCPNews findById(int id);

	  @Modifying
	   @Transactional
	   @Query(value = " delete from ccpnewsupdatedetails where  newsId=:newsId ", nativeQuery = true)
	   void  deleteNewsById(int newsId);
	  

}
