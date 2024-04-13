package com.catrionbe.api.service;

import java.util.ArrayList;
import com.catrionbe.api.entity.DAOUser;
import com.catrionbe.api.model.UpdateUserDTO;
import com.catrionbe.api.model.UpdateUserDTO2;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.model.UserIdRequest;
import com.catrionbe.api.repositories.CCPCitiesRepsitory;
import com.catrionbe.api.repositories.CCPDeptRepsitory;
import com.catrionbe.api.repositories.UserDao;

import utils.ccputil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private CCPCitiesRepsitory  objCCPCitiesRepsitory;
    
    
    @Autowired
    private CCPDeptRepsitory  objCCPDeptRepsitory;
    
    
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("User Name :  "+username);
        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
    public String loadUserByUsername1(String username) throws UsernameNotFoundException {
        String personalEmail = userDao.findByUsername1(username);
        String officialEmail = userDao.findByUsername2(username);        
        try {
			if (  officialEmail.equals("") || officialEmail.equals("null") || officialEmail==""  || officialEmail=="null") {
				officialEmail = personalEmail;
			}
		} catch (Exception e) {
			officialEmail = personalEmail;
		}                
      return officialEmail;
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
    	 
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        //Encrypt Username for Password 
        System.out.println(bcryptEncoder.encode(user.getPrnNumber()));
        newUser.setPassword(bcryptEncoder.encode(user.getPrnNumber()));
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
        newUser.setCreatedBy(user.getCreatedBy());
        newUser.setCreatedDate(user.getCreatedDate());
        newUser.setIsAdmin(user.getIsAdmin());
        newUser.setWorkEmail(user.getWorkEmail());
        newUser.setDeptId(user.getDeptId());
        newUser.setLocationId(user.getLocationId());
        newUser.setManagerEmail(user.getManagerEmail());        
        newUser.setMobileNumber(user.getMobileNumber());
        newUser.setIsArchived(user.getIsArchived());
        String personalEmail = user.getEmailId();
        String officialEmail =user.getWorkEmail();        
        try {
     			if (  officialEmail.equals("") || officialEmail.equals("null") || officialEmail==""  || officialEmail=="null") {     				 
     				newUser.setIsEmployee(0);
     			}
     			else {     				 
     					newUser.setIsEmployee(1);     				 
     			} 
     		} catch (Exception e) {
     			newUser.setIsEmployee(0);
     		}       
        try {
			if (  officialEmail.equals("") || officialEmail.equals("null") || officialEmail==""  || officialEmail=="null") {
				officialEmail = personalEmail;
			}
		} catch (Exception e) {
			officialEmail = personalEmail;
		}                        
        
        ccputil.sendWelcomeEmail(officialEmail, user.getPrnNumber(), user.getFirstName(), user.getUsername());
        
        return userDao.save(newUser);
      //  return null;
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
        newUser.setPassword(bcryptEncoder.encode(user.getPrnNumber()));
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
        newUser.setLocationId(user.getLocationId());
        newUser.setDeptId(user.getDeptId());
        newUser.setIsAprroved(user.getIsAprroved());
        newUser.setModifiedBy(user.getModifiedBy());
        newUser.setModifiedDate(user.getModifiedDate());        
        newUser.setManagerEmail(user.getManagerEmail());    
        newUser.setMobileNumber(user.getMobileNumber());
        newUser.setIsArchived(user.getIsArchived());
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
	public DAOUser  save2(UpdateUserDTO2 user) {
		String maxId = userDao.getMaxPrnNumber();
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
        newUser.setPrnNumber(user.getPrnNumber());
        newUser.setConfPassword(user.getConfPassword());
        newUser.setIsActive(user.getIsActive());
        newUser.setIsAprroved(user.getIsAprroved());
        newUser.setModifiedBy(user.getModifiedBy());
        newUser.setModifiedDate(user.getModifiedDate());
        newUser.setCreatedBy(user.getCreatedBy());
        newUser.setCreatedDate(user.getCreatedDate());
        newUser.setIsAdmin(user.getIsAdmin());
        newUser.setWorkEmail(user.getWorkEmail());
        System.out.println(user.getDeptName());
        int deptId = objCCPDeptRepsitory.getDeptId(user.getDeptName());
        System.out.println(deptId);
        newUser.setDeptId(deptId);
        System.out.println(user.getLocationName());
        int locationId = objCCPCitiesRepsitory.getCityId(user.getLocationName());
        newUser.setLocationId(locationId);
        newUser.setManagerEmail(user.getManagerEmail());        
        
        ccputil.sendWelcomeEmail(user.getEmailId(), incrementedPrnNumber, user.getFirstName(), user.getUsername());
        
        return userDao.save(newUser);
		
	}
 
	
	public Page<DAOUser> listallusersfromdb( int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
				  return		userDao.listallusersfromdb(pageable); 
						
					}
 
	
	public Page<DAOUser> listsearchallusersfromdb(String searchText ,int pageNumber,int size) {
		  Pageable pageable = PageRequest.of(pageNumber, size );
				  return		userDao.listsearchallusersfromdb(searchText,pageable); 
						
					}
}
