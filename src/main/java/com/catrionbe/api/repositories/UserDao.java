package com.catrionbe.api.repositories;

import com.catrionbe.api.entity.DAOUser;

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
    
    @Query(value = "SELECT *  FROM user WHERE  emailId=:emailId ", nativeQuery = true)
    String loginUsers() ;
    
    @Query(value = "SELECT *  FROM user WHERE  emailId=:emailId ", nativeQuery = true)
    String commencedCyberBasic();
    
    
    
}