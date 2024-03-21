package com.catrionbe.api.service;

import java.util.ArrayList;
import java.util.List;

import com.catrionbe.api.entity.CCPUserActivity;
import com.catrionbe.api.entity.DAOUser;
import com.catrionbe.api.model.CCPUserActivityReq;
import com.catrionbe.api.model.UpdateUserDTO;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.repositories.CCPUserActRepository;
import com.catrionbe.api.repositories.UserDao;

import utils.ccputil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
    public String loadUserByUsername1(String username) throws UsernameNotFoundException {
        String email = userDao.findByUsername1(username);
      return email;
    }
    
    
    public DAOUser autheticateOtp(String emailId , int otpGenerated) throws UsernameNotFoundException {
        DAOUser user = userDao.autheticateOtp(emailId , otpGenerated);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + emailId);
        }
    //    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
      //          new ArrayList<>());
        return user;
    }
    
    public DAOUser fetchUserDetails(String emailId ) throws UsernameNotFoundException {
    	System.out.println(" Hit the Controller  0 00  21  "+emailId);
        DAOUser user = userDao.fetuserdetails(emailId );
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + emailId);
        }
    //    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
      //          new ArrayList<>());
        return user;
    }
    
    
    public void updateUserByUsername(String username , int OtpGenerated) throws UsernameNotFoundException {
     userDao.updateUserByUsername(username,OtpGenerated);
       
    }
    
    public DAOUser save(UserDTO user) {
    	String maxId = userDao.getMaxPrnNumber();
    	System.out.println(maxId);
    	long maxIdnum  = Long.parseLong(maxId);
    	long maxidnextnum = maxIdnum+1;
    	String incrementedPrnNumber= String.valueOf(maxidnextnum);
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        //Encrypt Username for Password 
        newUser.setPassword(bcryptEncoder.encode(user.getUsername()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmailId(user.getEmailId());
        newUser.setConfPassword(user.getConfPassword());
        newUser.setFatherName(user.getFatherName());
        newUser.setGrandFatherName(user.getGrandFatherName());
        newUser.setOtpEntered(user.getOtpEntered());
        newUser.setOtpGenerated(user.getOtpGenerated());
        newUser.setUsername(user.getUsername());
        newUser.setNationalId(user.getNationalId());
        newUser.setPrnNumber(incrementedPrnNumber);
        newUser.setConfPassword(user.getConfPassword());
        newUser.setIsActive(user.getIsActive());
        newUser.setIsAprroved(user.getIsAprroved());
        newUser.setModifiedBy(user.getModifiedBy());
        newUser.setModifiedDate(user.getModifiedDate());
        newUser.setCreatedBy(user.getCreatedBy());
        newUser.setCreatedDate(user.getCreatedDate());
        
        ccputil.sendWelcomeEmail(user.getEmailId(), incrementedPrnNumber);
        
        return userDao.save(newUser);
    }
    
    
   	public String  totalCatrionUsers() {
   		
   		return  userDao.totalCatrionUsers() ;
   	}

   	public String  loginUsers() {
   		
   		return  userDao.loginUsers() ;
   	}
   	public Object commencedCyberBasic() {
   
   		return userDao.commencedCyberBasic() ;
   	}
    
   	
    public DAOUser update(UpdateUserDTO user) {
        DAOUser newUser = new DAOUser();
        newUser.setUserId(user.getUserId());
        newUser.setUsername(user.getUsername());
        //Encrypt Username for Password 
        newUser.setPassword(bcryptEncoder.encode(user.getUsername()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmailId(user.getEmailId());
        newUser.setConfPassword(user.getConfPassword());
        newUser.setFatherName(user.getFatherName());
        newUser.setGrandFatherName(user.getGrandFatherName());
        newUser.setOtpEntered(user.getOtpEntered());
        newUser.setOtpGenerated(user.getOtpGenerated());
        newUser.setUsername(user.getUsername());
        newUser.setNationalId(user.getNationalId());
        newUser.setPrnNumber(user.getPrnNumber());
        newUser.setConfPassword(user.getConfPassword());
        newUser.setIsActive(user.getIsActive());
        newUser.setIsAprroved(user.getIsAprroved());
        newUser.setModifiedBy(user.getModifiedBy());
        newUser.setModifiedDate(user.getModifiedDate());        
        return userDao.save(newUser);
    }
	public int updateuserasarchived(UserIdRequest user) {
 		userDao.updateuserasarchived(user.getUserId());
		return  1;
	}
	public Page<DAOUser> listallactiveusers( int pageNumber,int size) {
	 
        Pageable pageable = PageRequest.of(pageNumber, size );
		
  return		userDao.listallactiveusers(pageable); 
		
	} 
	public Page<DAOUser> listallnewusers( int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
		  return		userDao.listallnewusers(pageable); 
				
			} 
	public Page<DAOUser> listallarchivedusers(int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
				  return		userDao.listallarchivedusers(pageable); 
						
					}
	public Page<DAOUser> listsearchresult(String searchText ,int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
				  return		userDao.listsearchresult(searchText,pageable); 
						
					}
	
	
	
	
}
