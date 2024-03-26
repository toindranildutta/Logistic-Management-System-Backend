package com.catrionbe.api.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
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
	
 
	int deptId = 1;float allStaffCompletePer = 1.0f;
 
 
 	float satffCountDeptId0 =  objCCPUserActRepository.getTotalStaffsDept(deptId );
	System.out.println(satffCountDeptId0);
	float staffCompleted0 = objCCPUserActRepository.getTotalStaffsCompleted(deptId );	
	System.out.println(staffCompleted0);
 	if (staffCompleted0==0) {
		 allStaffCompletePer = (staffCompleted0 /  1) *100;
		 
	}else {
		  allStaffCompletePer = (staffCompleted0 /  satffCountDeptId0) *100;
	}
 
  
	System.out.println(allStaffCompletePer);
	allstaffsmapobj.setDeptId(1);
	allstaffsmapobj.setStaffCount(Math.round(satffCountDeptId0));
	allstaffsmapobj.setStaffCompleted(Math.round(staffCompleted0));
	DecimalFormat decimalFormat = new DecimalFormat("#.#");
	allstaffsmapobj.setCompletedPer(Float.valueOf(decimalFormat.format(allStaffCompletePer)));
	allstaffsegprogressmap.add(0,allstaffsmapobj);
	
	
	allstaffsmap allstaffsmapobj1 = new allstaffsmap();
	int deptId1 = 2;
	float allStaffCompletePer1 = 1.0f;
	float satffCountDeptId1 =  objCCPUserActRepository.getTotalStaffsDept(deptId1);
	System.out.println(satffCountDeptId1);
	float staffCompleted1 = objCCPUserActRepository.getTotalStaffsCompleted(deptId1);	
	System.out.println(staffCompleted1);
	if (staffCompleted1==0) {
		 allStaffCompletePer1 = (staffCompleted1 /  1) *100;
		 
	}else {
		  allStaffCompletePer1 = (staffCompleted1 /  satffCountDeptId1) *100;
	}
 	System.out.println(allStaffCompletePer1);
 	allstaffsmapobj1.setDeptId(1);
 	allstaffsmapobj1.setStaffCount(Math.round(satffCountDeptId1));
 	allstaffsmapobj1.setStaffCompleted(Math.round(staffCompleted1));
	DecimalFormat decimalFormat1 = new DecimalFormat("#.#");
	allstaffsmapobj1.setCompletedPer(Float.valueOf(decimalFormat1.format(allStaffCompletePer1)));
     allstaffsegprogressmap.add(1,allstaffsmapobj1);
	
 
    allstaffsmap allstaffsmapobj2 = new allstaffsmap();
 	int deptId2 = 3;
 	float allStaffCompletePer2 = 1.0f;
 	float satffCountDeptId2 =  objCCPUserActRepository.getTotalStaffsDept(deptId2);
 	System.out.println(satffCountDeptId2);
 	float staffCompleted2 = objCCPUserActRepository.getTotalStaffsCompleted(deptId2);	
 	System.out.println(staffCompleted2);
 	if (staffCompleted2==0) {
 		 allStaffCompletePer2 = (staffCompleted2 /  1) *100;
 		 
 	}else {
 		  allStaffCompletePer2 = (staffCompleted2 /  satffCountDeptId2) *100;
 	}
  	System.out.println(allStaffCompletePer2);
  	allstaffsmapobj2.setDeptId(2);
  	allstaffsmapobj2.setStaffCount(Math.round(satffCountDeptId2));
  	allstaffsmapobj2.setStaffCompleted(Math.round(staffCompleted2));
 	DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
 	allstaffsmapobj2.setCompletedPer(Float.valueOf(decimalFormat2.format(allStaffCompletePer2)));
      allstaffsegprogressmap.add(2,allstaffsmapobj2);
     
     
      allstaffsmap allstaffsmapobj3 = new allstaffsmap();
   	int deptId3 = 4;
   	float allStaffCompletePer3 = 1.0f;
   	float satffCountDeptId3 =  objCCPUserActRepository.getTotalStaffsDept(deptId3);
   	System.out.println(satffCountDeptId3);
   	float staffCompleted3 = objCCPUserActRepository.getTotalStaffsCompleted(deptId3);	
   	System.out.println(staffCompleted3);
   	if (staffCompleted3==0) {
   		 allStaffCompletePer3 = (staffCompleted3 /  1) *100;
   		 
   	}else {
   		  allStaffCompletePer3 = (staffCompleted3 /  satffCountDeptId3) *100;
   	}
    	System.out.println(allStaffCompletePer3);
    	allstaffsmapobj3.setDeptId(3);
    	allstaffsmapobj3.setStaffCount(Math.round(satffCountDeptId3));
    	allstaffsmapobj3.setStaffCompleted(Math.round(staffCompleted3));
   	DecimalFormat decimalFormat3 = new DecimalFormat("#.#");
   	allstaffsmapobj3.setCompletedPer(Float.valueOf(decimalFormat3.format(allStaffCompletePer3)));
        allstaffsegprogressmap.add(3,allstaffsmapobj3);
     
     
        allstaffsmap allstaffsmapobj4 = new allstaffsmap();
     	int deptId4 = 5;
     	float allStaffCompletePer4 = 1.0f;
     	float satffCountDeptId4 =  objCCPUserActRepository.getTotalStaffsDept(deptId4);
     	System.out.println(satffCountDeptId4);
     	float staffCompleted4 = objCCPUserActRepository.getTotalStaffsCompleted(deptId4);	
     	System.out.println(staffCompleted4);
     	if (staffCompleted4==0) {
     		 allStaffCompletePer4 = (staffCompleted4 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer4 = (staffCompleted4 /  satffCountDeptId4) *100;
     	}
      	System.out.println(allStaffCompletePer4);
      	allstaffsmapobj4.setDeptId(4);
      	allstaffsmapobj4.setStaffCount(Math.round(satffCountDeptId4));
      	allstaffsmapobj4.setStaffCompleted(Math.round(staffCompleted4));
     	DecimalFormat decimalFormat4 = new DecimalFormat("#.#");
     	allstaffsmapobj4.setCompletedPer(Float.valueOf(decimalFormat4.format(allStaffCompletePer4)));
        allstaffsegprogressmap.add(4,allstaffsmapobj4);
        
        allstaffsmap allstaffsmapobj5 = new allstaffsmap();
     	int deptId5 = 6;
     	float allStaffCompletePer5 = 1.0f;
     	float satffCountDeptId5 =  objCCPUserActRepository.getTotalStaffsDept(deptId5);
     	System.out.println(satffCountDeptId5);
     	float staffCompleted5 = objCCPUserActRepository.getTotalStaffsCompleted(deptId5);	
     	System.out.println(staffCompleted5);
     	if (staffCompleted5==0) {
     		 allStaffCompletePer5 = (staffCompleted5 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer5 = (staffCompleted5 /  satffCountDeptId5) *100;
     	}
      	System.out.println(allStaffCompletePer5);
      	allstaffsmapobj5.setDeptId(5);
      	allstaffsmapobj5.setStaffCount(Math.round(satffCountDeptId5));
      	allstaffsmapobj5.setStaffCompleted(Math.round(staffCompleted5));
     	DecimalFormat decimalFormat5 = new DecimalFormat("#.#");
     	allstaffsmapobj5.setCompletedPer(Float.valueOf(decimalFormat5.format(allStaffCompletePer5)));
        allstaffsegprogressmap.add(5,allstaffsmapobj5);
        
        allstaffsmap allstaffsmapobj6 = new allstaffsmap();
     	int deptId6 = 7;
     	float allStaffCompletePer6 = 1.0f;
     	float satffCountDeptId6 =  objCCPUserActRepository.getTotalStaffsDept(deptId6);
     	System.out.println(satffCountDeptId6);
     	float staffCompleted6 = objCCPUserActRepository.getTotalStaffsCompleted(deptId6);	
     	System.out.println(staffCompleted6);
     	if (staffCompleted6==0) {
     		 allStaffCompletePer6 = (staffCompleted6 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer6 = (staffCompleted6 /  satffCountDeptId6) *100;
     	}
      	System.out.println(allStaffCompletePer6);
      	allstaffsmapobj6.setDeptId(6);
      	allstaffsmapobj6.setStaffCount(Math.round(satffCountDeptId6));
      	allstaffsmapobj6.setStaffCompleted(Math.round(staffCompleted6));
     	DecimalFormat decimalFormat6 = new DecimalFormat("#.#");
     	allstaffsmapobj6.setCompletedPer(Float.valueOf(decimalFormat6.format(allStaffCompletePer6)));
        allstaffsegprogressmap.add(6,allstaffsmapobj6);
        
        allstaffsmap allstaffsmapobj7 = new allstaffsmap();
     	int deptId7 = 8;
     	float allStaffCompletePer7 = 1.0f;
     	float satffCountDeptId7 =  objCCPUserActRepository.getTotalStaffsDept(deptId7);
     	System.out.println(satffCountDeptId7);
     	float staffCompleted7 = objCCPUserActRepository.getTotalStaffsCompleted(deptId7);	
     	System.out.println(staffCompleted7);
     	if (staffCompleted7==0) {
     		 allStaffCompletePer7 = (staffCompleted7 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer7 = (staffCompleted7 /  satffCountDeptId7) *100;
     	}
      	System.out.println(allStaffCompletePer7);
      	allstaffsmapobj7.setDeptId(7);
      	allstaffsmapobj7.setStaffCount(Math.round(satffCountDeptId7));
      	allstaffsmapobj7.setStaffCompleted(Math.round(staffCompleted7));
     	DecimalFormat decimalFormat7 = new DecimalFormat("#.#");
     	allstaffsmapobj7.setCompletedPer(Float.valueOf(decimalFormat7.format(allStaffCompletePer7)));
        allstaffsegprogressmap.add(7,allstaffsmapobj7);
        
        allstaffsmap allstaffsmapobj8 = new allstaffsmap();
     	int deptId8 = 9;
     	float allStaffCompletePer8 = 1.0f;
     	float satffCountDeptId8 =  objCCPUserActRepository.getTotalStaffsDept(deptId8);
     	System.out.println(satffCountDeptId8);
     	float staffCompleted8 = objCCPUserActRepository.getTotalStaffsCompleted(deptId8);	
     	System.out.println(staffCompleted8);
     	if (staffCompleted8==0) {
     		 allStaffCompletePer8 = (staffCompleted8 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer8 = (staffCompleted8 /  satffCountDeptId8) *100;
     	}
      	System.out.println(allStaffCompletePer8);
      	allstaffsmapobj8.setDeptId(8);
      	allstaffsmapobj8.setStaffCount(Math.round(satffCountDeptId8));
      	allstaffsmapobj8.setStaffCompleted(Math.round(staffCompleted8));
     	DecimalFormat decimalFormat8 = new DecimalFormat("#.#");
     	allstaffsmapobj8.setCompletedPer(Float.valueOf(decimalFormat8.format(allStaffCompletePer8)));
        allstaffsegprogressmap.add(8,allstaffsmapobj8);
        
        
        allstaffsmap allstaffsmapobj9 = new allstaffsmap();
     	int deptId9 = 10;
     	float allStaffCompletePer9 = 1.0f;
     	float satffCountDeptId9 =  objCCPUserActRepository.getTotalStaffsDept(deptId9);
     	System.out.println(satffCountDeptId9);
     	float staffCompleted9 = objCCPUserActRepository.getTotalStaffsCompleted(deptId9);	
     	System.out.println(staffCompleted9);
     	if (staffCompleted9==0) {
     		 allStaffCompletePer9 = (staffCompleted9 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer9 = (staffCompleted9 /  satffCountDeptId9) *100;
     	}
      	System.out.println(allStaffCompletePer9);
      	allstaffsmapobj9.setDeptId(9);
      	allstaffsmapobj9.setStaffCount(Math.round(satffCountDeptId9));
      	allstaffsmapobj9.setStaffCompleted(Math.round(staffCompleted9));
     	DecimalFormat decimalFormat9 = new DecimalFormat("#.#");
     	allstaffsmapobj9.setCompletedPer(Float.valueOf(decimalFormat9.format(allStaffCompletePer9)));
        allstaffsegprogressmap.add(9,allstaffsmapobj9);
        
        allstaffsmap allstaffsmapobj10 = new allstaffsmap();
     	int deptId10 = 11;
     	float allStaffCompletePer10 = 1.0f;
     	float satffCountDeptId10 =  objCCPUserActRepository.getTotalStaffsDept(deptId10);
     	System.out.println(satffCountDeptId10);
     	float staffCompleted10 = objCCPUserActRepository.getTotalStaffsCompleted(deptId10);	
     	System.out.println(staffCompleted10);
     	if (staffCompleted10==0) {
     		 allStaffCompletePer10 = (staffCompleted10 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer10 = (staffCompleted10 /  satffCountDeptId10) *100;
     	}
      	System.out.println(allStaffCompletePer10);
      	allstaffsmapobj10.setDeptId(10);
      	allstaffsmapobj10.setStaffCount(Math.round(satffCountDeptId10));
      	allstaffsmapobj10.setStaffCompleted(Math.round(staffCompleted10));
     	DecimalFormat decimalFormat10 = new DecimalFormat("#.#");
     	allstaffsmapobj10.setCompletedPer(Float.valueOf(decimalFormat10.format(allStaffCompletePer10)));
        allstaffsegprogressmap.add(10,allstaffsmapobj10);
     
        
        allstaffsmap allstaffsmapobj11 = new allstaffsmap();
     	int deptId11 = 12;
     	float allStaffCompletePer11 = 1.0f;
     	float satffCountDeptId11 =  objCCPUserActRepository.getTotalStaffsDept(deptId11);
     	System.out.println(satffCountDeptId11);
     	float staffCompleted11 = objCCPUserActRepository.getTotalStaffsCompleted(deptId11);	
     	System.out.println(staffCompleted11);
     	if (staffCompleted11==0) {
     		 allStaffCompletePer11 = (staffCompleted11 /  1) *110;
     		 
     	}else {
     		  allStaffCompletePer11 = (staffCompleted11 /  satffCountDeptId11) *110;
     	}
      	System.out.println(allStaffCompletePer11);
      	allstaffsmapobj11.setDeptId(11);
      	allstaffsmapobj11.setStaffCount(Math.round(satffCountDeptId11));
      	allstaffsmapobj11.setStaffCompleted(Math.round(staffCompleted11));
     	DecimalFormat decimalFormat11 = new DecimalFormat("#.#");
     	allstaffsmapobj11.setCompletedPer(Float.valueOf(decimalFormat11.format(allStaffCompletePer11)));
        allstaffsegprogressmap.add(11,allstaffsmapobj11);
        

        allstaffsmap allstaffsmapobj12 = new allstaffsmap();
       	int deptId12 = 13;
       	float allStaffCompletePer12 = 1.0f;
       	float satffCountDeptId12 =  objCCPUserActRepository.getTotalStaffsDept(deptId12);
       	System.out.println(satffCountDeptId12);
       	float staffCompleted12 = objCCPUserActRepository.getTotalStaffsCompleted(deptId12);	
       	System.out.println(staffCompleted12);
       	if (staffCompleted12==0) {
       		 allStaffCompletePer12 = (staffCompleted12 /  1) *120;
       		 
       	}else {
       		  allStaffCompletePer12 = (staffCompleted12 /  satffCountDeptId12) *120;
       	}
        	System.out.println(allStaffCompletePer12);
        	allstaffsmapobj12.setDeptId(12);
        	allstaffsmapobj12.setStaffCount(Math.round(satffCountDeptId12));
        	allstaffsmapobj12.setStaffCompleted(Math.round(staffCompleted12));
       	DecimalFormat decimalFormat12 = new DecimalFormat("#.#");
       	allstaffsmapobj12.setCompletedPer(Float.valueOf(decimalFormat12.format(allStaffCompletePer12)));
          allstaffsegprogressmap.add(12,allstaffsmapobj12);
          
 
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
