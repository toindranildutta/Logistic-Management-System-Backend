package com.catrion.catrionbe.repository;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.catrion.catrionbe.entity.CCPUserAcceptTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CCPUserAcceptTrackRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	final Logger logger = Logger.getLogger(CCPUserAcceptTrackRepository.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
 

	public void addCCPUserAcceptTrack(Object CCPUserAcceptTrack) throws Exception {
		logger.info("Inside addCCPUserAcceptTrack");
		try {
			Session session = this.sessionFactory.getCurrentSession();
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			CCPUserAcceptTrack pojo = mapper.convertValue(CCPUserAcceptTrack, CCPUserAcceptTrack.class);
			Date createdOn = new Date();
			//pojo.setCreated_date(createdOn);
			session.save(pojo);
		} catch (Exception e) {
			System.out.println("Exception  "+e);
			e.printStackTrace();
			logger.error("Exception in  addCCPUserAcceptTrack " + e.getMessage());
			throw e;
		}
		logger.info("returning addCCPUserAcceptTrack");		
	}

	public void updateCCPUserAcceptTrack(Object CCPUserAcceptTrack) throws Exception {
		logger.info("Inside updateCCPUserAcceptTrack");
		try {
			Session session = this.sessionFactory.getCurrentSession();
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			CCPUserAcceptTrack pojo = mapper.convertValue(CCPUserAcceptTrack, CCPUserAcceptTrack.class);
			Date lastModifiedOn = new Date();
			//pojo.setModified_date(lastModifiedOn);
			session.update(pojo);
		} catch (Exception e) {
			System.out.println("Exception  "+e);
			e.printStackTrace();
			logger.error("Exception in updateCCPUserAcceptTrack " + e.getMessage());
			throw e;
		}
		logger.info("returning updateCCPUserAcceptTrack");
	}

	 
	
	 
 
}
