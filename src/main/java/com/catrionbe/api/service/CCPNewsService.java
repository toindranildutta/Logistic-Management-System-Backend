package com.catrionbe.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPCourses;
import com.catrionbe.api.entity.CCPNews;
import com.catrionbe.api.model.CCPNewsRequest;
import com.catrionbe.api.model.CourseUpdateRequest;
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
	 
	 
	 public CCPNews save(CCPNewsRequest news) {
		 CCPNews newsObj = new CCPNews();
	 
		 newsObj.setNewsTitle(news.getNewsTitle());
		 newsObj.setNewsContent(news.getNewsContent());
		 newsObj.setImageUrl(news.getImageUrl());
		 newsObj.setReferenceUrl(news.getReferenceUrl());
		 newsObj.setRating(news.getRating());
		 newsObj.setIsActive(news.getIsActive());
		 newsObj.setIsAprroved(news.getIsAprroved());
		 newsObj.setModifiedBy(news.getModifiedBy());
		 newsObj.setModifiedDate(news.getModifiedDate());
		 newsObj.setCreatedBy(news.getCreatedBy());
		 newsObj.setCreatedDate(news.getCreatedDate());
	      return newsRepository.save(newsObj);
	 }
	 
	 public CCPNews update(CCPNewsRequest news) {
		 CCPNews newsObj = newsRepository.findById(news.getNewsId());	 
		 newsObj.setNewsTitle(news.getNewsTitle());
		 newsObj.setNewsContent(news.getNewsContent());
		 newsObj.setImageUrl(news.getImageUrl());
		 newsObj.setReferenceUrl(news.getReferenceUrl());
		 newsObj.setRating(news.getRating());
		 newsObj.setIsActive(news.getIsActive());
		 newsObj.setIsAprroved(news.getIsAprroved());
		 newsObj.setModifiedBy(news.getModifiedBy());
		 newsObj.setModifiedDate(news.getModifiedDate());
		 newsObj.setCreatedBy(news.getCreatedBy());
		 newsObj.setCreatedDate(news.getCreatedDate());
	      return newsRepository.save(newsObj);
	 }
	 
	 public String deleteNews( int newsId) {
		 String messageString="deleted successfully";
		 
		   try {
			newsRepository.deleteNewsById(newsId);
		} catch (Exception e) {
			messageString ="unable to delete";   			
		}
		   return messageString;
	 }
}
