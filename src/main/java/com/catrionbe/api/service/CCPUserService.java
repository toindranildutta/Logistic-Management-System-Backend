package  com.catrionbe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catrionbe.api.entity.LoginRepository;
import com.catrionbe.api.model.CCPUserDetailsResponse;
import com.catrionbe.api.repositories.*;
import lombok.RequiredArgsConstructor;
 
@Service("CCPUserService")
@RequiredArgsConstructor
public class CCPUserService {

	  
    private  LoginRepository loginRepo;
     
    private       UserRepository userRepository  ;
	
	@Transactional
	public String ValidateUser(String userName, String password) throws Exception {
		System.out.println("Inside Service ");
		
		String ss = userRepository.findByUsername(userName , password , "Y" );
		//return loginRepo.ValidateUser(userName, password);
		return ss;
		
		
	}
	
	@Transactional
	public void updateUserDetails(Object LoginUserDetails) throws Exception {
		System.out.println("  Inside updateLoginUserDetails 2" );
		loginRepo.updateUserDetails(LoginUserDetails);
	}
	
	
	@Transactional
	public String validateOTP(String otpNumber, String phoneNumber) throws Exception {
		System.out.println("  Inside validateOTP Service " );
		return loginRepo.validateOTP(otpNumber,phoneNumber);
	} 
	
	/*
	 * @Transactional public CCPUserDetailsResponse loadCCPUserDetails(String
	 * userName) throws Exception { return loginRepo.loadCCPUserDetails(userName); }
	 */
	@Transactional
	public String getUserDetailsById(int id) throws Exception {
		return loginRepo.getUserDetailsById(id);
	}
	
	
}
