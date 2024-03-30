package com.catrionbe.api.repositories;

import com.catrionbe.api.entity.DAOUser;

import java.util.List;

 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DAOUser findByUsername(String username);
    
    @Query(value = "SELECT emailId FROM user WHERE  username=:username", nativeQuery = true)
   String findByUsername1(String username);
    
    @Query(value = "SELECT workEmail FROM user WHERE  username=:username AND workEmail  LIKE '%CATRION%' ", nativeQuery = true)
	String findByUsername2(String username);

	
    
    @Modifying
    @Transactional
    @Query(value = "update user set otpGenerated=:otpGenerated where   username=:username", nativeQuery = true)
    void updateUserByUsername(String username , int otpGenerated);
    
    @Query(value = "SELECT *  FROM user WHERE  emailId=:emailId AND otpGenerated=:otpGenerated ", nativeQuery = true)
    DAOUser autheticateOtp (String emailId , int otpGenerated);
    
    @Query(value = "SELECT *  FROM user WHERE  emailId=:emailId ", nativeQuery = true)
    DAOUser fetuserdetails (String emailId );
    
    @Query(value = "SELECT count(*) from user", nativeQuery = true)
   	String  totalCatrionUsers();
    
    @Query(value = "select count(  distinct  userId ) from ccpcoursecompletestatus ", nativeQuery = true)
    String loginUsers() ;
    
    @Query(value = "select count(  distinct  userId ) from ccpcoursecompletestatus ", nativeQuery = true)
    String commencedCyberBasic();

    @Modifying
    @Transactional
    @Query(value = "update user set isAprroved=0  where   userId=:userId", nativeQuery = true)
	int updateuserasarchived(int userId);

    @Query(value = "SELECT *  FROM user WHERE  isAprroved='Y' ", nativeQuery = true)
    Page<DAOUser> listallactiveusers( final Pageable pageable);

    @Query(value = "SELECT *  FROM user    WHERE createdDate >= (NOW() - INTERVAL 1 MONTH) ", nativeQuery = true)
    Page<DAOUser> listallnewusers( final Pageable pageable);

    @Query(value = "SELECT *  FROM user WHERE  isAprroved='N' ", nativeQuery = true)
    Page<DAOUser> listallarchivedusers( final Pageable pageable);

    @Query(value = "SELECT max(prnNumber)  FROM user", nativeQuery = true)
	String getMaxPrnNumber();
    
    @Query(value = "SELECT *  FROM user WHERE  lower(firstName) LIKE  %:searchText% OR lower(lastName) LIKE  %:searchText% OR prnNumber LIKE %:searchText%", nativeQuery = true)
    Page<DAOUser> listsearchresult( String searchText,  final Pageable pageable);

    
    }