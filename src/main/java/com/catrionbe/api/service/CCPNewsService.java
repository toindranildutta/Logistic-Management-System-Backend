package com.catrionbe.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPNews;
import com.catrionbe.api.repositories.CCPnewsRepository;
import com.catrionbe.api.repositories.UserDao;

 
@Service
public class CCPNewsService {

	
	 @Autowired
	    private CCPnewsRepository newsRepository;
	 
	 public  List viewAllNews() {
	 List<CCPNews> cppNews =  newsRepository.findAll();
	return cppNews;
	 }
}
