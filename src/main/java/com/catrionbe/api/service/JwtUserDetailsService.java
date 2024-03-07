package com.catrionbe.api.service;

import java.util.ArrayList;

import com.catrionbe.api.entity.DAOUser;
import com.catrionbe.api.model.UserDTO;
import com.catrionbe.api.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
JWTUserDetailsService implements the Spring Security UserDetailsService interface.
It overrides the loadUserByUsername for fetching user details from the database using the username.
The Spring Security Authentication Manager calls this method for getting the user details from the database
when authenticating the user details provided by the user. Here we are getting the user details from a hardcoded
User List. In the next tutorial we will be adding the DAO implementation for fetching User Details from the Database.
Also the password for a user is stored in encrypted format using BCrypt.
Previously we have seen Spring Boot Security - Password Encoding Using Bcrypt.
Here using the Online Bcrypt Generator you can generate the Bcrypt for a password.
 */
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
        
        
        
        return userDao.save(newUser);
    }
}
