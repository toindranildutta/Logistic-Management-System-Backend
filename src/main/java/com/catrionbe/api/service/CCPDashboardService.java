package com.catrionbe.api.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.CCPUserActivity;
import com.catrionbe.api.model.CCPUserActivityReq;
import com.catrionbe.api.model.allstaffsmap;
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


public HashMap accessbydate() {

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
 

public List allstaffsegmentprogress() {
	List<allstaffsmap> allstaffsegprogressmap = new ArrayList<allstaffsmap> () ;
	//For Dept Id 0
	allstaffsmap allstaffsmapobj = new allstaffsmap();
	int deptId = 0;
	int satffCountDeptId0 =  objCCPUserActRepository.getTotalStaffsDept(deptId );
	int staffCompleted0 = objCCPUserActRepository.getTotalStaffsCompleted(deptId );	
	float allStaffCompletePer = (staffCompleted0 /  satffCountDeptId0) *100;
	allstaffsmapobj.setDeptId(0);
	allstaffsmapobj.setStaffCount(satffCountDeptId0);
	allstaffsmapobj.setStaffCompleted(staffCompleted0);
	allstaffsmapobj.setCompletePer(allStaffCompletePer);
	allstaffsegprogressmap.add(allstaffsmapobj);
/*
	float allStaffCompletePer1 = 1.0f;
	int deptId1 = 1;
	int satffCountDeptId1 =  objCCPUserActRepository.getTotalStaffsDept(deptId1 );
	int staffCompleted1 = objCCPUserActRepository.getTotalStaffsCompleted(deptId1 );	
	if (staffCompleted1==0) {
		 allStaffCompletePer1 = (staffCompleted1 /  1) *100;
		 
	}else {
		  allStaffCompletePer1 = (staffCompleted1 /  satffCountDeptId1) *100;
	}
	allstaffsegprogressmap.add(allstaffsmapobj);

	
	int deptId2 = 2;
	int satffCountDeptId2 =  objCCPUserActRepository.getTotalStaffsDept(deptId2 );
	int staffCompleted2 = objCCPUserActRepository.getTotalStaffsCompleted(deptId2 );	
	float allStaffCompletePer2 = (staffCompleted2 /  satffCountDeptId2) *100;
	
	int deptId3 = 3;
	int satffCountDeptId3 =  objCCPUserActRepository.getTotalStaffsDept(deptId3 );
	int staffCompleted3 = objCCPUserActRepository.getTotalStaffsCompleted(deptId3 );	
	float allStaffCompletePer3 = (staffCompleted3 /  satffCountDeptId3) *100;
	
	int deptId4 = 4;
	int satffCountDeptId4 =  objCCPUserActRepository.getTotalStaffsDept(deptId4 );
	int staffCompleted4 = objCCPUserActRepository.getTotalStaffsCompleted(deptId4 );	
	float allStaffCompletePer4 = (staffCompleted4 /  satffCountDeptId4) *100;
	
	int deptId5 = 5;
	int satffCountDeptId5 =  objCCPUserActRepository.getTotalStaffsDept(deptId5 );
	int staffCompleted5 = objCCPUserActRepository.getTotalStaffsCompleted(deptId5 );	
	float allStaffCompletePer5 = (staffCompleted5 /  satffCountDeptId5) *100;
	
	int deptId6 = 6;
	int satffCountDeptId6 =  objCCPUserActRepository.getTotalStaffsDept(deptId6 );
	int staffCompleted6 = objCCPUserActRepository.getTotalStaffsCompleted(deptId6 );	
	float allStaffCompletePer6 = (staffCompleted6 /  satffCountDeptId6) *100;
	
	int deptId7 = 7;
	int satffCountDeptId7 =  objCCPUserActRepository.getTotalStaffsDept(deptId7);
	int staffCompleted7 = objCCPUserActRepository.getTotalStaffsCompleted(deptId7 );	
	float allStaffCompletePer7 = (staffCompleted7 /  satffCountDeptId7) *100;
	
	int deptId8 = 8;
	int satffCountDeptId8  =  objCCPUserActRepository.getTotalStaffsDept(deptId8 );
	int staffCompleted8  = objCCPUserActRepository.getTotalStaffsCompleted(deptId8 );	
	float allStaffCompletePer8 = (staffCompleted8 /  satffCountDeptId8) *100;
	
	int deptId9 = 9;
	int satffCountDeptId9 =  objCCPUserActRepository.getTotalStaffsDept(deptId9 );
	int staffCompleted9 = objCCPUserActRepository.getTotalStaffsCompleted(deptId9 );	
	float allStaffCompletePer9 = (staffCompleted9 /  satffCountDeptId9) *100;
	
	int deptId10 = 10;
	int satffCountDeptId10 =  objCCPUserActRepository.getTotalStaffsDept(deptId10 );
	int staffCompleted10 = objCCPUserActRepository.getTotalStaffsCompleted(deptId10 );	
	float allStaffCompletePer10 = (staffCompleted10 /  satffCountDeptId10) *100;
	
	int deptId11 = 11;
	int satffCountDeptId11 =  objCCPUserActRepository.getTotalStaffsDept(deptId11 );
	int staffCompleted11 = objCCPUserActRepository.getTotalStaffsCompleted(deptId11 );	
	float allStaffCompletePer11 = (staffCompleted11 /  satffCountDeptId11) *100;
	
	int deptId12 = 12;
	int satffCountDeptId12 =  objCCPUserActRepository.getTotalStaffsDept(deptId12 );
	int staffCompleted12 = objCCPUserActRepository.getTotalStaffsCompleted(deptId12 );	
	float allStaffCompletePer12 = (staffCompleted12 /  satffCountDeptId12) *100;
	
	*/
	return allstaffsegprogressmap;
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
