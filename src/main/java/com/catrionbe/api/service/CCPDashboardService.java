package com.catrionbe.api.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPUserActivity;
import com.catrionbe.api.model.CCPUserActivityReq;
import com.catrionbe.api.repositories.CCPUserActRepository;

@Service
public class CCPDashboardService {

    @Autowired
    CCPUserActRepository    objCCPUserActRepository;
    

public CCPUserActivity saveuseractivity(CCPUserActivityReq objCCPUserActivityReq) {
		
		CCPUserActivity objCCPUserActivity = new CCPUserActivity();
		objCCPUserActivity.setUserId(objCCPUserActivityReq.getUserId());
		objCCPUserActivity.setActivity(objCCPUserActivityReq.getActivity());
		objCCPUserActivity.setIsActive(objCCPUserActivityReq.getIsActive());
		objCCPUserActivity.setIsAprroved(objCCPUserActivityReq.getIsAprroved());
		objCCPUserActivity.setModifiedBy(objCCPUserActivityReq.getModifiedBy());
		objCCPUserActivity.setModifiedDate(objCCPUserActivityReq.getModifiedDate());
		objCCPUserActivity.setCreatedBy(objCCPUserActivityReq.getCreatedBy());
		objCCPUserActivity.setCreatedDate(objCCPUserActivityReq.getCreatedDate());
		 objCCPUserActRepository.save(objCCPUserActivity);
		return objCCPUserActivity;
		 
	}


public HashMap accessbycertificate(   ) throws Exception {
	
    HashMap<String, Integer> MapResult = new HashMap<>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate currentDate = LocalDate.now();
    String formattedDate = currentDate.format(formatter);    
	int count1 = objCCPUserActRepository.accessbydate(  formattedDate  );
	MapResult.put(formattedDate, count1);
	
	Calendar cal  = Calendar.getInstance();
    //subtracting a day
    cal.add(Calendar.DATE, -1);
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    String result = s.format(new Date(cal.getTimeInMillis()));
	System.out.println(result);
	int count2 = objCCPUserActRepository.accessbydate(  result  );
	MapResult.put(result, count2);
	
	
	Calendar cal3  = Calendar.getInstance();
    //subtracting a day
    cal3.add(Calendar.DATE, -2);
    SimpleDateFormat s3 = new SimpleDateFormat("yyyy-MM-dd");
    String result3 = s3.format(new Date(cal3.getTimeInMillis()));
	System.out.println(result3);
	int count3 = objCCPUserActRepository.accessbydate(  result3  );
	MapResult.put(result3, count3);
	
	Calendar cal4  = Calendar.getInstance();
    //subtracting a day
    cal4.add(Calendar.DATE, -3);
    SimpleDateFormat s4 = new SimpleDateFormat("yyyy-MM-dd");
    String result4 = s4.format(new Date(cal4.getTimeInMillis()));
	System.out.println(result4);
	int count4= objCCPUserActRepository.accessbydate(  result4  );
	MapResult.put(result4, count4);
	
	return MapResult;
}


public Object accessbydate() {

	 HashMap<String, Integer> MapResult = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate currentDate = LocalDate.now();
	    String formattedDate = currentDate.format(formatter);    
		int count1 = objCCPUserActRepository.accessbydate1(  formattedDate  );
		MapResult.put(formattedDate, count1);
		
		Calendar cal  = Calendar.getInstance();
	    //subtracting a day
	    cal.add(Calendar.DATE, -1);
	    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	    String result = s.format(new Date(cal.getTimeInMillis()));
		System.out.println(result);
		int count2 = objCCPUserActRepository.accessbydate1(  result  );
		MapResult.put(result, count2);
		
		
		Calendar cal3  = Calendar.getInstance();
	    //subtracting a day
	    cal3.add(Calendar.DATE, -2);
	    SimpleDateFormat s3 = new SimpleDateFormat("yyyy-MM-dd");
	    String result3 = s3.format(new Date(cal3.getTimeInMillis()));
		System.out.println(result3);
		int count3 = objCCPUserActRepository.accessbydate1(  result3  );
		MapResult.put(result3, count3);
		
		Calendar cal4  = Calendar.getInstance();
	    //subtracting a day
	    cal4.add(Calendar.DATE, -3);
	    SimpleDateFormat s4 = new SimpleDateFormat("yyyy-MM-dd");
	    String result4 = s4.format(new Date(cal4.getTimeInMillis()));
		System.out.println(result4);
		int count4= objCCPUserActRepository.accessbydate1(  result4  );
		MapResult.put(result4, count4);
		
		return MapResult;
	
	
	
	
	
}
 

public Object allstaffsegmentprogress() {
	 
	//For Dept Id 0 
	int deptId = 0;
	int satffCountDeptId0 =  objCCPUserActRepository.getTotalStaffsDept(deptId );
	int staffCompleted0 = objCCPUserActRepository.getTotalStaffsCompleted(deptId );	
	float allStaffCompletePer = (staffCompleted0 /  satffCountDeptId0) *100;
	
	
	return null;
}


public Object allstaffsegmentprogresstable() {
	// TODO Auto-generated method stub
	return null;
}


public Object onlystaffsegmentprogresschart() {
	// TODO Auto-generated method stub
	return null;
}  

 
 

}
