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
import com.catrionbe.api.model.allstaffsmaploc;
import com.catrionbe.api.model.allstaffsmapprogtable;
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
 	allstaffsmapobj1.setDeptId(2);
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
  	allstaffsmapobj2.setDeptId(3);
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
    	allstaffsmapobj3.setDeptId(4);
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
      	allstaffsmapobj4.setDeptId(5);
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
      	allstaffsmapobj5.setDeptId(6);
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
      	allstaffsmapobj6.setDeptId(7);
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
      	allstaffsmapobj7.setDeptId(8);
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
      	allstaffsmapobj8.setDeptId(9);
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
      	allstaffsmapobj9.setDeptId(10);
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
      	allstaffsmapobj10.setDeptId(11);
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
      	allstaffsmapobj11.setDeptId(12);
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
        	allstaffsmapobj12.setDeptId(13);
        	allstaffsmapobj12.setStaffCount(Math.round(satffCountDeptId12));
        	allstaffsmapobj12.setStaffCompleted(Math.round(staffCompleted12));
       	DecimalFormat decimalFormat12 = new DecimalFormat("#.#");
       	allstaffsmapobj12.setCompletedPer(Float.valueOf(decimalFormat12.format(allStaffCompletePer12)));
          allstaffsegprogressmap.add(12,allstaffsmapobj12);
          
          
          allstaffsmap allstaffsmapobj13 = new allstaffsmap();
         	int deptId13 = 14;
         	float allStaffCompletePer13 = 1.0f;
         	float satffCountDeptId13 =  objCCPUserActRepository.getTotalStaffsDept(deptId13);
         	System.out.println(satffCountDeptId13);
         	float staffCompleted13 = objCCPUserActRepository.getTotalStaffsCompleted(deptId13);	
         	System.out.println(staffCompleted13);
         	if (staffCompleted13==0) {
         		 allStaffCompletePer13 = (staffCompleted13 /  1) *130;
         		 
         	}else {
         		  allStaffCompletePer13 = (staffCompleted13 /  satffCountDeptId13) *130;
         	}
          	System.out.println(allStaffCompletePer13);
          	allstaffsmapobj13.setDeptId(13);
          	allstaffsmapobj13.setStaffCount(Math.round(satffCountDeptId13));
          	allstaffsmapobj13.setStaffCompleted(Math.round(staffCompleted13));
         	DecimalFormat decimalFormat13 = new DecimalFormat("#.#");
         	allstaffsmapobj13.setCompletedPer(Float.valueOf(decimalFormat13.format(allStaffCompletePer13)));
            allstaffsegprogressmap.add(13,allstaffsmapobj13);
            
            allstaffsmap allstaffsmapobj14 = new allstaffsmap();
           	int deptId14 = 15;
           	float allStaffCompletePer14 = 1.0f;
           	float satffCountDeptId14 =  objCCPUserActRepository.getTotalStaffsDept(deptId14);
           	System.out.println(satffCountDeptId14);
           	float staffCompleted14 = objCCPUserActRepository.getTotalStaffsCompleted(deptId14);	
           	System.out.println(staffCompleted14);
           	if (staffCompleted14==0) {
           		 allStaffCompletePer14 = (staffCompleted14 /  1) *140;
           		 
           	}else {
           		  allStaffCompletePer14 = (staffCompleted14 /  satffCountDeptId14) *140;
           	}
            	System.out.println(allStaffCompletePer14);
            	allstaffsmapobj14.setDeptId(14);
            	allstaffsmapobj14.setStaffCount(Math.round(satffCountDeptId14));
            	allstaffsmapobj14.setStaffCompleted(Math.round(staffCompleted14));
           	DecimalFormat decimalFormat14 = new DecimalFormat("#.#");
           	allstaffsmapobj14.setCompletedPer(Float.valueOf(decimalFormat14.format(allStaffCompletePer14)));
              allstaffsegprogressmap.add(14,allstaffsmapobj14);
 
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
	List<allstaffsmapprogtable> allstaffsegprogresstable = new ArrayList<allstaffsmapprogtable> () ;
	
	allstaffsmapprogtable allstaffsmapobj1 = new allstaffsmapprogtable();	 
	int deptId1 = 1;float allStaffCompletePer = 1.0f; 
 	int  satffCountDeptId1=  objCCPUserActRepository.getTotalStaffsDept(deptId1 );
	System.out.println(satffCountDeptId1);
	int  staffCompleted1 = objCCPUserActRepository.getTotalStaffsCompleted(deptId1 );	
	//System.out.println(staffCompleted1); 
   int curWeekProg1 =  objCCPUserActRepository.getCurrentWeekPer(deptId1 );
   //System.out.println(" curWeekProg  "+curWeekProg1);
   allstaffsmapobj1.setCurWeekProgress(curWeekProg1);
   int prevWeekProg1 =  objCCPUserActRepository.getPrevWeekPer(deptId1 );
   //System.out.println( " prevWeekProg  "+prevWeekProg1);
   allstaffsmapobj1.setImprovement(curWeekProg1 - prevWeekProg1);
   allstaffsmapobj1.setPrevWeekProgress(prevWeekProg1);
	//System.out.println(allStaffCompletePer1);
	allstaffsmapobj1.setDeptId(1);
	allstaffsmapobj1.setStaffCount(satffCountDeptId1);
	allstaffsmapobj1.setStaffCompleted  (staffCompleted1);
	allstaffsmapobj1.setRemainingStaffs(  satffCountDeptId1- staffCompleted1);
	allstaffsegprogresstable.add(0,allstaffsmapobj1);
	
	 
	 allstaffsmapprogtable allstaffsmapobj2 = new allstaffsmapprogtable();	 
		int deptId2 = 2;float allStaffCompletePer2 = 2.0f; 
	 	int  satffCountDeptId2=  objCCPUserActRepository.getTotalStaffsDept(deptId2 );
		System.out.println(satffCountDeptId2);
		int  staffCompleted2 = objCCPUserActRepository.getTotalStaffsCompleted(deptId2 );	
		//System.out.println(staffCompleted2); 
	   int curWeekProg2 =  objCCPUserActRepository.getCurrentWeekPer(deptId2 );
	   //System.out.println(" curWeekProg  "+curWeekProg2);
	   allstaffsmapobj2.setCurWeekProgress(curWeekProg2);
	   int prevWeekProg2 =  objCCPUserActRepository.getPrevWeekPer(deptId2 );
	   //System.out.println( " prevWeekProg  "+prevWeekProg2);
	   allstaffsmapobj2.setImprovement(curWeekProg2 - prevWeekProg2);
	   allstaffsmapobj2.setPrevWeekProgress(prevWeekProg2);
		//System.out.println(allStaffCompletePer2);
		allstaffsmapobj2.setDeptId(2);
		allstaffsmapobj2.setStaffCount(satffCountDeptId2);
		allstaffsmapobj2.setStaffCompleted  (staffCompleted2);
		allstaffsmapobj2.setRemainingStaffs(  satffCountDeptId2- staffCompleted2);
		allstaffsegprogresstable.add(1,allstaffsmapobj2);
	
		 allstaffsmapprogtable allstaffsmapobj3 = new allstaffsmapprogtable();	 
			int deptId3 = 3;float allStaffCompletePer3 = 3.0f; 
		 	int  satffCountDeptId3=  objCCPUserActRepository.getTotalStaffsDept(deptId3 );
			System.out.println(satffCountDeptId3);
			int  staffCompleted3 = objCCPUserActRepository.getTotalStaffsCompleted(deptId3 );	
			//System.out.println(staffCompleted3); 
		   int curWeekProg3 =  objCCPUserActRepository.getCurrentWeekPer(deptId3 );
		   //System.out.println(" curWeekProg  "+curWeekProg3);
		   allstaffsmapobj3.setCurWeekProgress(curWeekProg3);
		   int prevWeekProg3 =  objCCPUserActRepository.getPrevWeekPer(deptId3 );
		   //System.out.println( " prevWeekProg  "+prevWeekProg3);
		   allstaffsmapobj3.setImprovement(curWeekProg3 - prevWeekProg3);
		   allstaffsmapobj3.setPrevWeekProgress(prevWeekProg3);
			//System.out.println(allStaffCompletePer3);
			allstaffsmapobj3.setDeptId(3);
			allstaffsmapobj3.setStaffCount(satffCountDeptId3);
			allstaffsmapobj3.setStaffCompleted  (staffCompleted3);
			allstaffsmapobj3.setRemainingStaffs(  satffCountDeptId3- staffCompleted3);
			allstaffsegprogresstable.add(2,allstaffsmapobj3);
			
			 allstaffsmapprogtable allstaffsmapobj4 = new allstaffsmapprogtable();	 
				int deptId4 = 4;float allStaffCompletePer4 = 4.0f; 
			 	int  satffCountDeptId4=  objCCPUserActRepository.getTotalStaffsDept(deptId4 );
				System.out.println(satffCountDeptId4);
				int  staffCompleted4 = objCCPUserActRepository.getTotalStaffsCompleted(deptId4 );	
				//System.out.println(staffCompleted4); 
			   int curWeekProg4 =  objCCPUserActRepository.getCurrentWeekPer(deptId4 );
			   //System.out.println(" curWeekProg  "+curWeekProg4);
			   allstaffsmapobj4.setCurWeekProgress(curWeekProg4);
			   int prevWeekProg4 =  objCCPUserActRepository.getPrevWeekPer(deptId4 );
			   //System.out.println( " prevWeekProg  "+prevWeekProg4);
			   allstaffsmapobj4.setImprovement(curWeekProg4 - prevWeekProg4);
			   allstaffsmapobj4.setPrevWeekProgress(prevWeekProg4);
				//System.out.println(allStaffCompletePer4);
				allstaffsmapobj4.setDeptId(4);
				allstaffsmapobj4.setStaffCount(satffCountDeptId4);
				allstaffsmapobj4.setStaffCompleted  (staffCompleted4);
				allstaffsmapobj4.setRemainingStaffs(  satffCountDeptId4- staffCompleted4);
				allstaffsegprogresstable.add(3,allstaffsmapobj4);
				
				 allstaffsmapprogtable allstaffsmapobj5 = new allstaffsmapprogtable();	 
					int deptId5 = 5;float allStaffCompletePer5 = 5.0f; 
				 	int  satffCountDeptId5=  objCCPUserActRepository.getTotalStaffsDept(deptId5 );
					System.out.println(satffCountDeptId5);
					int  staffCompleted5 = objCCPUserActRepository.getTotalStaffsCompleted(deptId5 );	
					//System.out.println(staffCompleted5); 
				   int curWeekProg5 =  objCCPUserActRepository.getCurrentWeekPer(deptId5 );
				   //System.out.println(" curWeekProg  "+curWeekProg5);
				   allstaffsmapobj5.setCurWeekProgress(curWeekProg5);
				   int prevWeekProg5 =  objCCPUserActRepository.getPrevWeekPer(deptId5 );
				   //System.out.println( " prevWeekProg  "+prevWeekProg5);
				   allstaffsmapobj5.setImprovement(curWeekProg5 - prevWeekProg5);
				   allstaffsmapobj5.setPrevWeekProgress(prevWeekProg5);
					//System.out.println(allStaffCompletePer5);
					allstaffsmapobj5.setDeptId(5);
					allstaffsmapobj5.setStaffCount(satffCountDeptId5);
					allstaffsmapobj5.setStaffCompleted  (staffCompleted5);
					allstaffsmapobj5.setRemainingStaffs(  satffCountDeptId5- staffCompleted5);
					allstaffsegprogresstable.add(4,allstaffsmapobj5);
					
					 allstaffsmapprogtable allstaffsmapobj6 = new allstaffsmapprogtable();	 
						int deptId6 = 6;float allStaffCompletePer6 = 6.0f; 
					 	int  satffCountDeptId6=  objCCPUserActRepository.getTotalStaffsDept(deptId6 );
						System.out.println(satffCountDeptId6);
						int  staffCompleted6 = objCCPUserActRepository.getTotalStaffsCompleted(deptId6 );	
						//System.out.println(staffCompleted6); 
					   int curWeekProg6 =  objCCPUserActRepository.getCurrentWeekPer(deptId6 );
					   //System.out.println(" curWeekProg  "+curWeekProg6);
					   allstaffsmapobj6.setCurWeekProgress(curWeekProg6);
					   int prevWeekProg6 =  objCCPUserActRepository.getPrevWeekPer(deptId6 );
					   //System.out.println( " prevWeekProg  "+prevWeekProg6);
					   allstaffsmapobj6.setImprovement(curWeekProg6 - prevWeekProg6);
					   allstaffsmapobj6.setPrevWeekProgress(prevWeekProg6);
						//System.out.println(allStaffCompletePer6);
						allstaffsmapobj6.setDeptId(6);
						allstaffsmapobj6.setStaffCount(satffCountDeptId6);
						allstaffsmapobj6.setStaffCompleted  (staffCompleted6);
						allstaffsmapobj6.setRemainingStaffs(  satffCountDeptId6- staffCompleted6);
						allstaffsegprogresstable.add(5,allstaffsmapobj6);

						
						 allstaffsmapprogtable allstaffsmapobj7 = new allstaffsmapprogtable();	 
							int deptId7 = 7;float allStaffCompletePer7 = 7.0f; 
						 	int  satffCountDeptId7=  objCCPUserActRepository.getTotalStaffsDept(deptId7 );
							System.out.println(satffCountDeptId7);
							int  staffCompleted7 = objCCPUserActRepository.getTotalStaffsCompleted(deptId7 );	
							//System.out.println(staffCompleted7); 
						   int curWeekProg7 =  objCCPUserActRepository.getCurrentWeekPer(deptId7 );
						   //System.out.println(" curWeekProg  "+curWeekProg7);
						   allstaffsmapobj7.setCurWeekProgress(curWeekProg7);
						   int prevWeekProg7 =  objCCPUserActRepository.getPrevWeekPer(deptId7 );
						   //System.out.println( " prevWeekProg  "+prevWeekProg7);
						   allstaffsmapobj7.setImprovement(curWeekProg7 - prevWeekProg7);
						   allstaffsmapobj7.setPrevWeekProgress(prevWeekProg7);
							//System.out.println(allStaffCompletePer7);
							allstaffsmapobj7.setDeptId(7);
							allstaffsmapobj7.setStaffCount(satffCountDeptId7);
							allstaffsmapobj7.setStaffCompleted  (staffCompleted7);
							allstaffsmapobj7.setRemainingStaffs(  satffCountDeptId7- staffCompleted7);
							allstaffsegprogresstable.add(6,allstaffsmapobj7);
							
							 allstaffsmapprogtable allstaffsmapobj8 = new allstaffsmapprogtable();	 
								int deptId8 = 8;float allStaffCompletePer8 = 8.0f; 
							 	int  satffCountDeptId8=  objCCPUserActRepository.getTotalStaffsDept(deptId8 );
								System.out.println(satffCountDeptId8);
								int  staffCompleted8 = objCCPUserActRepository.getTotalStaffsCompleted(deptId8 );	
								//System.out.println(staffCompleted8); 
							   int curWeekProg8 =  objCCPUserActRepository.getCurrentWeekPer(deptId8 );
							   //System.out.println(" curWeekProg  "+curWeekProg8);
							   allstaffsmapobj8.setCurWeekProgress(curWeekProg8);
							   int prevWeekProg8 =  objCCPUserActRepository.getPrevWeekPer(deptId8 );
							   //System.out.println( " prevWeekProg  "+prevWeekProg8);
							   allstaffsmapobj8.setImprovement(curWeekProg8 - prevWeekProg8);
							   allstaffsmapobj8.setPrevWeekProgress(prevWeekProg8);
								//System.out.println(allStaffCompletePer8);
								allstaffsmapobj8.setDeptId(8);
								allstaffsmapobj8.setStaffCount(satffCountDeptId8);
								allstaffsmapobj8.setStaffCompleted  (staffCompleted8);
								allstaffsmapobj8.setRemainingStaffs(  satffCountDeptId8- staffCompleted8);
								allstaffsegprogresstable.add(7,allstaffsmapobj8);

								 allstaffsmapprogtable allstaffsmapobj9 = new allstaffsmapprogtable();	 
									int deptId9 = 9;float allStaffCompletePer9 = 9.0f; 
								 	int  satffCountDeptId9=  objCCPUserActRepository.getTotalStaffsDept(deptId9 );
									System.out.println(satffCountDeptId9);
									int  staffCompleted9 = objCCPUserActRepository.getTotalStaffsCompleted(deptId9 );	
									//System.out.println(staffCompleted9); 
								   int curWeekProg9 =  objCCPUserActRepository.getCurrentWeekPer(deptId9 );
								   //System.out.println(" curWeekProg  "+curWeekProg9);
								   allstaffsmapobj9.setCurWeekProgress(curWeekProg9);
								   int prevWeekProg9 =  objCCPUserActRepository.getPrevWeekPer(deptId9 );
								   //System.out.println( " prevWeekProg  "+prevWeekProg9);
								   allstaffsmapobj9.setImprovement(curWeekProg9 - prevWeekProg9);
								   allstaffsmapobj9.setPrevWeekProgress(prevWeekProg9);
									//System.out.println(allStaffCompletePer9);
									allstaffsmapobj9.setDeptId(9);
									allstaffsmapobj9.setStaffCount(satffCountDeptId9);
									allstaffsmapobj9.setStaffCompleted  (staffCompleted9);
									allstaffsmapobj9.setRemainingStaffs(  satffCountDeptId9- staffCompleted9);
									allstaffsegprogresstable.add(8,allstaffsmapobj9);
									
									 allstaffsmapprogtable allstaffsmapobj10 = new allstaffsmapprogtable();	 
										int deptId10 = 10;float allStaffCompletePer10 = 10.0f; 
									 	int  satffCountDeptId10=  objCCPUserActRepository.getTotalStaffsDept(deptId10 );
										System.out.println(satffCountDeptId10);
										int  staffCompleted10 = objCCPUserActRepository.getTotalStaffsCompleted(deptId10 );	
										//System.out.println(staffCompleted10); 
									   int curWeekProg10 =  objCCPUserActRepository.getCurrentWeekPer(deptId10 );
									   //System.out.println(" curWeekProg  "+curWeekProg10);
									   allstaffsmapobj10.setCurWeekProgress(curWeekProg10);
									   int prevWeekProg10 =  objCCPUserActRepository.getPrevWeekPer(deptId10 );
									   //System.out.println( " prevWeekProg  "+prevWeekProg10);
									   allstaffsmapobj10.setImprovement(curWeekProg10 - prevWeekProg10);
									   allstaffsmapobj10.setPrevWeekProgress(prevWeekProg10);
										//System.out.println(allStaffCompletePer10);
										allstaffsmapobj10.setDeptId(10);
										allstaffsmapobj10.setStaffCount(satffCountDeptId10);
										allstaffsmapobj10.setStaffCompleted  (staffCompleted10);
										allstaffsmapobj10.setRemainingStaffs(  satffCountDeptId10- staffCompleted10);
										allstaffsegprogresstable.add(9,allstaffsmapobj10);

										
										 allstaffsmapprogtable allstaffsmapobj11 = new allstaffsmapprogtable();	 
											int deptId11 = 11;float allStaffCompletePer11 = 11.0f; 
										 	int  satffCountDeptId11=  objCCPUserActRepository.getTotalStaffsDept(deptId11 );
											System.out.println(satffCountDeptId11);
											int  staffCompleted11 = objCCPUserActRepository.getTotalStaffsCompleted(deptId11 );	
											//System.out.println(staffCompleted11); 
										   int curWeekProg11 =  objCCPUserActRepository.getCurrentWeekPer(deptId11 );
										   //System.out.println(" curWeekProg  "+curWeekProg11);
										   allstaffsmapobj11.setCurWeekProgress(curWeekProg11);
										   int prevWeekProg11 =  objCCPUserActRepository.getPrevWeekPer(deptId11 );
										   //System.out.println( " prevWeekProg  "+prevWeekProg11);
										   allstaffsmapobj11.setImprovement(curWeekProg11 - prevWeekProg11);
										   allstaffsmapobj11.setPrevWeekProgress(prevWeekProg11);
											//System.out.println(allStaffCompletePer11);
											allstaffsmapobj11.setDeptId(11);
											allstaffsmapobj11.setStaffCount(satffCountDeptId11);
											allstaffsmapobj11.setStaffCompleted  (staffCompleted11);
											allstaffsmapobj11.setRemainingStaffs(  satffCountDeptId11- staffCompleted11);
											allstaffsegprogresstable.add(10,allstaffsmapobj11);

											
											 allstaffsmapprogtable allstaffsmapobj12 = new allstaffsmapprogtable();	 
												int deptId12 = 12;float allStaffCompletePer12 = 12.0f; 
											 	int  satffCountDeptId12=  objCCPUserActRepository.getTotalStaffsDept(deptId12 );
												System.out.println(satffCountDeptId12);
												int  staffCompleted12 = objCCPUserActRepository.getTotalStaffsCompleted(deptId12 );	
												//System.out.println(staffCompleted12); 
											   int curWeekProg12 =  objCCPUserActRepository.getCurrentWeekPer(deptId12 );
											   //System.out.println(" curWeekProg  "+curWeekProg12);
											   allstaffsmapobj12.setCurWeekProgress(curWeekProg12);
											   int prevWeekProg12 =  objCCPUserActRepository.getPrevWeekPer(deptId12 );
											   //System.out.println( " prevWeekProg  "+prevWeekProg12);
											   allstaffsmapobj12.setImprovement(curWeekProg12 - prevWeekProg12);
											   allstaffsmapobj12.setPrevWeekProgress(prevWeekProg12);
												//System.out.println(allStaffCompletePer12);
												allstaffsmapobj12.setDeptId(12);
												allstaffsmapobj12.setStaffCount(satffCountDeptId12);
												allstaffsmapobj12.setStaffCompleted  (staffCompleted12);
												allstaffsmapobj12.setRemainingStaffs(  satffCountDeptId12- staffCompleted12);
												allstaffsegprogresstable.add(11,allstaffsmapobj12);

												 allstaffsmapprogtable allstaffsmapobj13 = new allstaffsmapprogtable();	 
													int deptId13 = 13;float allStaffCompletePer13 = 13.0f; 
												 	int  satffCountDeptId13=  objCCPUserActRepository.getTotalStaffsDept(deptId13 );
													System.out.println(satffCountDeptId13);
													int  staffCompleted13 = objCCPUserActRepository.getTotalStaffsCompleted(deptId13 );	
													//System.out.println(staffCompleted13); 
												   int curWeekProg13 =  objCCPUserActRepository.getCurrentWeekPer(deptId13 );
												   //System.out.println(" curWeekProg  "+curWeekProg13);
												   allstaffsmapobj13.setCurWeekProgress(curWeekProg13);
												   int prevWeekProg13 =  objCCPUserActRepository.getPrevWeekPer(deptId13 );
												   //System.out.println( " prevWeekProg  "+prevWeekProg13);
												   allstaffsmapobj13.setImprovement(curWeekProg13 - prevWeekProg13);
												   allstaffsmapobj13.setPrevWeekProgress(prevWeekProg13);
													//System.out.println(allStaffCompletePer13);
													allstaffsmapobj13.setDeptId(13);
													allstaffsmapobj13.setStaffCount(satffCountDeptId13);
													allstaffsmapobj13.setStaffCompleted  (staffCompleted13);
													allstaffsmapobj13.setRemainingStaffs(  satffCountDeptId13- staffCompleted13);
													allstaffsegprogresstable.add(12,allstaffsmapobj13);
												
													allstaffsmapprogtable allstaffsmapobj14 = new allstaffsmapprogtable();	 
													int deptId14 = 14;float allStaffCompletePer14 = 14.0f; 
												 	int  satffCountDeptId14=  objCCPUserActRepository.getTotalStaffsDept(deptId14 );
													System.out.println(satffCountDeptId14);
													int  staffCompleted14 = objCCPUserActRepository.getTotalStaffsCompleted(deptId14 );	
													//System.out.println(staffCompleted14); 
												   int curWeekProg14 =  objCCPUserActRepository.getCurrentWeekPer(deptId14 );
												   //System.out.println(" curWeekProg  "+curWeekProg14);
												   allstaffsmapobj14.setCurWeekProgress(curWeekProg14);
												   int prevWeekProg14 =  objCCPUserActRepository.getPrevWeekPer(deptId14 );
												   //System.out.println( " prevWeekProg  "+prevWeekProg14);
												   allstaffsmapobj14.setImprovement(curWeekProg14 - prevWeekProg14);
												   allstaffsmapobj14.setPrevWeekProgress(prevWeekProg14);
													//System.out.println(allStaffCompletePer14);
													allstaffsmapobj14.setDeptId(14);
													allstaffsmapobj14.setStaffCount(satffCountDeptId14);
													allstaffsmapobj14.setStaffCompleted  (staffCompleted14);
													allstaffsmapobj14.setRemainingStaffs(  satffCountDeptId14- staffCompleted14);
													allstaffsegprogresstable.add(12,allstaffsmapobj14);
													
													
													allstaffsmapprogtable allstaffsmapobj15 = new allstaffsmapprogtable();	 
													int deptId15 = 15;float allStaffCompletePer15 = 15.0f; 
												 	int  satffCountDeptId15=  objCCPUserActRepository.getTotalStaffsDept(deptId15 );
													System.out.println(satffCountDeptId15);
													int  staffCompleted15 = objCCPUserActRepository.getTotalStaffsCompleted(deptId15 );	
													//System.out.println(staffCompleted15); 
												   int curWeekProg15 =  objCCPUserActRepository.getCurrentWeekPer(deptId15 );
												   //System.out.println(" curWeekProg  "+curWeekProg15);
												   allstaffsmapobj15.setCurWeekProgress(curWeekProg15);
												   int prevWeekProg15 =  objCCPUserActRepository.getPrevWeekPer(deptId15 );
												   //System.out.println( " prevWeekProg  "+prevWeekProg15);
												   allstaffsmapobj15.setImprovement(curWeekProg15 - prevWeekProg15);
												   allstaffsmapobj15.setPrevWeekProgress(prevWeekProg15);
													//System.out.println(allStaffCompletePer15);
													allstaffsmapobj15.setDeptId(15);
													allstaffsmapobj15.setStaffCount(satffCountDeptId15);
													allstaffsmapobj15.setStaffCompleted  (staffCompleted15);
													allstaffsmapobj15.setRemainingStaffs(  satffCountDeptId15- staffCompleted15);
													allstaffsegprogresstable.add(12,allstaffsmapobj15);
	
	
	
	// TODO Auto-generated method stub
	return allstaffsegprogresstable;
}


public List onlystaffsegmentprogresschart() {
	List<allstaffsmaploc> allstaffsegprogressmap = new ArrayList<allstaffsmaploc> () ;
	//For Dept Id 0
	allstaffsmaploc allstaffsmapobj = new allstaffsmaploc();
	
 
	int deptId = 1;float allStaffCompletePer = 1.0f;
 
 
 	float satffCountDeptId0 =  objCCPUserActRepository.getTotalStaffloc(deptId );
	System.out.println(satffCountDeptId0);
	float staffCompleted0 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId );	
	System.out.println(staffCompleted0);
 	if (staffCompleted0==0) {
		 allStaffCompletePer = (staffCompleted0 /  1) *100;
		 
	}else {
		  allStaffCompletePer = (staffCompleted0 /  satffCountDeptId0) *100;
	}
 
  
	System.out.println(allStaffCompletePer);
	allstaffsmapobj.setLocationId(1);
	allstaffsmapobj.setStaffCount(Math.round(satffCountDeptId0));
	allstaffsmapobj.setStaffCompleted(Math.round(staffCompleted0));
	DecimalFormat decimalFormat = new DecimalFormat("#.#");
	allstaffsmapobj.setCompletedPer(Float.valueOf(decimalFormat.format(allStaffCompletePer)));
	allstaffsegprogressmap.add(0,allstaffsmapobj);
	
	
	allstaffsmaploc allstaffsmapobj1 = new allstaffsmaploc();
	int deptId1 = 2;
	float allStaffCompletePer1 = 1.0f;
	float satffCountDeptId1 =  objCCPUserActRepository.getTotalStaffloc(deptId1);
	System.out.println(satffCountDeptId1);
	float staffCompleted1 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId1);	
	System.out.println(staffCompleted1);
	if (staffCompleted1==0) {
		 allStaffCompletePer1 = (staffCompleted1 /  1) *100;
		 
	}else {
		  allStaffCompletePer1 = (staffCompleted1 /  satffCountDeptId1) *100;
	}
 	System.out.println(allStaffCompletePer1);
 	allstaffsmapobj1.setLocationId(2);
 	allstaffsmapobj1.setStaffCount(Math.round(satffCountDeptId1));
 	allstaffsmapobj1.setStaffCompleted(Math.round(staffCompleted1));
	DecimalFormat decimalFormat1 = new DecimalFormat("#.#");
	allstaffsmapobj1.setCompletedPer(Float.valueOf(decimalFormat1.format(allStaffCompletePer1)));
     allstaffsegprogressmap.add(1,allstaffsmapobj1);
	
 
     allstaffsmaploc allstaffsmapobj2 = new allstaffsmaploc();
 	int deptId2 = 3;
 	float allStaffCompletePer2 = 1.0f;
 	float satffCountDeptId2 =  objCCPUserActRepository.getTotalStaffloc(deptId2);
 	System.out.println(satffCountDeptId2);
 	float staffCompleted2 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId2);	
 	System.out.println(staffCompleted2);
 	if (staffCompleted2==0) {
 		 allStaffCompletePer2 = (staffCompleted2 /  1) *100;
 		 
 	}else {
 		  allStaffCompletePer2 = (staffCompleted2 /  satffCountDeptId2) *100;
 	}
  	System.out.println(allStaffCompletePer2);
  	allstaffsmapobj2.setLocationId(3);
  	allstaffsmapobj2.setStaffCount(Math.round(satffCountDeptId2));
  	allstaffsmapobj2.setStaffCompleted(Math.round(staffCompleted2));
 	DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
 	allstaffsmapobj2.setCompletedPer(Float.valueOf(decimalFormat2.format(allStaffCompletePer2)));
      allstaffsegprogressmap.add(2,allstaffsmapobj2);
     
     
      allstaffsmaploc allstaffsmapobj3 = new allstaffsmaploc();
   	int deptId3 = 4;
   	float allStaffCompletePer3 = 1.0f;
   	float satffCountDeptId3 =  objCCPUserActRepository.getTotalStaffloc(deptId3);
   	System.out.println(satffCountDeptId3);
   	float staffCompleted3 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId3);	
   	System.out.println(staffCompleted3);
   	if (staffCompleted3==0) {
   		 allStaffCompletePer3 = (staffCompleted3 /  1) *100;
   		 
   	}else {
   		  allStaffCompletePer3 = (staffCompleted3 /  satffCountDeptId3) *100;
   	}
    	System.out.println(allStaffCompletePer3);
    	allstaffsmapobj3.setLocationId(4);
    	allstaffsmapobj3.setStaffCount(Math.round(satffCountDeptId3));
    	allstaffsmapobj3.setStaffCompleted(Math.round(staffCompleted3));
   	DecimalFormat decimalFormat3 = new DecimalFormat("#.#");
   	allstaffsmapobj3.setCompletedPer(Float.valueOf(decimalFormat3.format(allStaffCompletePer3)));
        allstaffsegprogressmap.add(3,allstaffsmapobj3);
     
     
        allstaffsmaploc allstaffsmapobj4 = new allstaffsmaploc();
     	int deptId4 = 5;
     	float allStaffCompletePer4 = 1.0f;
     	float satffCountDeptId4 =  objCCPUserActRepository.getTotalStaffloc(deptId4);
     	System.out.println(satffCountDeptId4);
     	float staffCompleted4 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId4);	
     	System.out.println(staffCompleted4);
     	if (staffCompleted4==0) {
     		 allStaffCompletePer4 = (staffCompleted4 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer4 = (staffCompleted4 /  satffCountDeptId4) *100;
     	}
      	System.out.println(allStaffCompletePer4);
      	allstaffsmapobj4.setLocationId(5);
      	allstaffsmapobj4.setStaffCount(Math.round(satffCountDeptId4));
      	allstaffsmapobj4.setStaffCompleted(Math.round(staffCompleted4));
     	DecimalFormat decimalFormat4 = new DecimalFormat("#.#");
     	allstaffsmapobj4.setCompletedPer(Float.valueOf(decimalFormat4.format(allStaffCompletePer4)));
        allstaffsegprogressmap.add(4,allstaffsmapobj4);
        
        allstaffsmaploc allstaffsmapobj5 = new allstaffsmaploc();
     	int deptId5 = 6;
     	float allStaffCompletePer5 = 1.0f;
     	float satffCountDeptId5 =  objCCPUserActRepository.getTotalStaffloc(deptId5);
     	System.out.println(satffCountDeptId5);
     	float staffCompleted5 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId5);	
     	System.out.println(staffCompleted5);
     	if (staffCompleted5==0) {
     		 allStaffCompletePer5 = (staffCompleted5 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer5 = (staffCompleted5 /  satffCountDeptId5) *100;
     	}
      	System.out.println(allStaffCompletePer5);
      	allstaffsmapobj5.setLocationId(6);
      	allstaffsmapobj5.setStaffCount(Math.round(satffCountDeptId5));
      	allstaffsmapobj5.setStaffCompleted(Math.round(staffCompleted5));
     	DecimalFormat decimalFormat5 = new DecimalFormat("#.#");
     	allstaffsmapobj5.setCompletedPer(Float.valueOf(decimalFormat5.format(allStaffCompletePer5)));
        allstaffsegprogressmap.add(5,allstaffsmapobj5);
        
        allstaffsmaploc allstaffsmapobj6 = new allstaffsmaploc();
     	int deptId6 = 7;
     	float allStaffCompletePer6 = 1.0f;
     	float satffCountDeptId6 =  objCCPUserActRepository.getTotalStaffloc(deptId6);
     	System.out.println(satffCountDeptId6);
     	float staffCompleted6 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId6);	
     	System.out.println(staffCompleted6);
     	if (staffCompleted6==0) {
     		 allStaffCompletePer6 = (staffCompleted6 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer6 = (staffCompleted6 /  satffCountDeptId6) *100;
     	}
      	System.out.println(allStaffCompletePer6);
      	allstaffsmapobj6.setLocationId(7);
      	allstaffsmapobj6.setStaffCount(Math.round(satffCountDeptId6));
      	allstaffsmapobj6.setStaffCompleted(Math.round(staffCompleted6));
     	DecimalFormat decimalFormat6 = new DecimalFormat("#.#");
     	allstaffsmapobj6.setCompletedPer(Float.valueOf(decimalFormat6.format(allStaffCompletePer6)));
        allstaffsegprogressmap.add(6,allstaffsmapobj6);
        
        allstaffsmaploc allstaffsmapobj7 = new allstaffsmaploc();
     	int deptId7 = 8;
     	float allStaffCompletePer7 = 1.0f;
     	float satffCountDeptId7 =  objCCPUserActRepository.getTotalStaffloc(deptId7);
     	System.out.println(satffCountDeptId7);
     	float staffCompleted7 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId7);	
     	System.out.println(staffCompleted7);
     	if (staffCompleted7==0) {
     		 allStaffCompletePer7 = (staffCompleted7 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer7 = (staffCompleted7 /  satffCountDeptId7) *100;
     	}
      	System.out.println(allStaffCompletePer7);
      	allstaffsmapobj7.setLocationId(8);
      	allstaffsmapobj7.setStaffCount(Math.round(satffCountDeptId7));
      	allstaffsmapobj7.setStaffCompleted(Math.round(staffCompleted7));
     	DecimalFormat decimalFormat7 = new DecimalFormat("#.#");
     	allstaffsmapobj7.setCompletedPer(Float.valueOf(decimalFormat7.format(allStaffCompletePer7)));
        allstaffsegprogressmap.add(7,allstaffsmapobj7);
        
        allstaffsmaploc allstaffsmapobj8 = new allstaffsmaploc();
     	int deptId8 = 9;
     	float allStaffCompletePer8 = 1.0f;
     	float satffCountDeptId8 =  objCCPUserActRepository.getTotalStaffloc(deptId8);
     	System.out.println(satffCountDeptId8);
     	float staffCompleted8 = objCCPUserActRepository.getTotalStaffsCompletedloc(deptId8);	
     	System.out.println(staffCompleted8);
     	if (staffCompleted8==0) {
     		 allStaffCompletePer8 = (staffCompleted8 /  1) *100;
     		 
     	}else {
     		  allStaffCompletePer8 = (staffCompleted8 /  satffCountDeptId8) *100;
     	}
      	System.out.println(allStaffCompletePer8);
      	allstaffsmapobj8.setLocationId(9);
      	allstaffsmapobj8.setStaffCount(Math.round(satffCountDeptId8));
      	allstaffsmapobj8.setStaffCompleted(Math.round(staffCompleted8));
     	DecimalFormat decimalFormat8 = new DecimalFormat("#.#");
     	allstaffsmapobj8.setCompletedPer(Float.valueOf(decimalFormat8.format(allStaffCompletePer8)));
        allstaffsegprogressmap.add(8,allstaffsmapobj8);
	return allstaffsegprogressmap;
}


public Object onlystaffsegmentprogresstable() {
 
List<allstaffsmapprogtable> allstaffsegprogresstable = new ArrayList<allstaffsmapprogtable> () ;
	
	allstaffsmapprogtable allstaffsmapobj1 = new allstaffsmapprogtable();	 
	int deptId1 = 1;float allStaffCompletePer = 1.0f; 
 	int  satffCountDeptId1=  objCCPUserActRepository.getTotalStaffsDept(deptId1 );
	System.out.println(satffCountDeptId1);
	int  staffCompleted1 = objCCPUserActRepository.getTotalStaffsCompleted(deptId1 );	
	//System.out.println(staffCompleted1); 
   int curWeekProg1 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId1 );
   //System.out.println(" curWeekProg  "+curWeekProg1);
   allstaffsmapobj1.setCurWeekProgress(curWeekProg1);
   int prevWeekProg1 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId1 );
   //System.out.println( " prevWeekProg  "+prevWeekProg1);
   allstaffsmapobj1.setImprovement(curWeekProg1 - prevWeekProg1);
   allstaffsmapobj1.setPrevWeekProgress(prevWeekProg1);
	//System.out.println(allStaffCompletePer1);
	allstaffsmapobj1.setDeptId(1);
	allstaffsmapobj1.setStaffCount(satffCountDeptId1);
	allstaffsmapobj1.setStaffCompleted  (staffCompleted1);
	allstaffsmapobj1.setRemainingStaffs(  satffCountDeptId1- staffCompleted1);
	allstaffsegprogresstable.add(0,allstaffsmapobj1);
	
	 
	 allstaffsmapprogtable allstaffsmapobj2 = new allstaffsmapprogtable();	 
		int deptId2 = 2;float allStaffCompletePer2 = 2.0f; 
	 	int  satffCountDeptId2=  objCCPUserActRepository.getTotalStaffsDept(deptId2 );
		System.out.println(satffCountDeptId2);
		int  staffCompleted2 = objCCPUserActRepository.getTotalStaffsCompleted(deptId2 );	
		//System.out.println(staffCompleted2); 
	   int curWeekProg2 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId2 );
	   //System.out.println(" curWeekProg  "+curWeekProg2);
	   allstaffsmapobj2.setCurWeekProgress(curWeekProg2);
	   int prevWeekProg2 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId2 );
	   //System.out.println( " prevWeekProg  "+prevWeekProg2);
	   allstaffsmapobj2.setImprovement(curWeekProg2 - prevWeekProg2);
	   allstaffsmapobj2.setPrevWeekProgress(prevWeekProg2);
		//System.out.println(allStaffCompletePer2);
		allstaffsmapobj2.setDeptId(2);
		allstaffsmapobj2.setStaffCount(satffCountDeptId2);
		allstaffsmapobj2.setStaffCompleted  (staffCompleted2);
		allstaffsmapobj2.setRemainingStaffs(  satffCountDeptId2- staffCompleted2);
		allstaffsegprogresstable.add(1,allstaffsmapobj2);
	
		 allstaffsmapprogtable allstaffsmapobj3 = new allstaffsmapprogtable();	 
			int deptId3 = 3;float allStaffCompletePer3 = 3.0f; 
		 	int  satffCountDeptId3=  objCCPUserActRepository.getTotalStaffsDept(deptId3 );
			System.out.println(satffCountDeptId3);
			int  staffCompleted3 = objCCPUserActRepository.getTotalStaffsCompleted(deptId3 );	
			//System.out.println(staffCompleted3); 
		   int curWeekProg3 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId3 );
		   //System.out.println(" curWeekProg  "+curWeekProg3);
		   allstaffsmapobj3.setCurWeekProgress(curWeekProg3);
		   int prevWeekProg3 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId3 );
		   //System.out.println( " prevWeekProg  "+prevWeekProg3);
		   allstaffsmapobj3.setImprovement(curWeekProg3 - prevWeekProg3);
		   allstaffsmapobj3.setPrevWeekProgress(prevWeekProg3);
			//System.out.println(allStaffCompletePer3);
			allstaffsmapobj3.setDeptId(3);
			allstaffsmapobj3.setStaffCount(satffCountDeptId3);
			allstaffsmapobj3.setStaffCompleted  (staffCompleted3);
			allstaffsmapobj3.setRemainingStaffs(  satffCountDeptId3- staffCompleted3);
			allstaffsegprogresstable.add(2,allstaffsmapobj3);
			
			 allstaffsmapprogtable allstaffsmapobj4 = new allstaffsmapprogtable();	 
				int deptId4 = 4;float allStaffCompletePer4 = 4.0f; 
			 	int  satffCountDeptId4=  objCCPUserActRepository.getTotalStaffsDept(deptId4 );
				System.out.println(satffCountDeptId4);
				int  staffCompleted4 = objCCPUserActRepository.getTotalStaffsCompleted(deptId4 );	
				//System.out.println(staffCompleted4); 
			   int curWeekProg4 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId4 );
			   //System.out.println(" curWeekProg  "+curWeekProg4);
			   allstaffsmapobj4.setCurWeekProgress(curWeekProg4);
			   int prevWeekProg4 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId4 );
			   //System.out.println( " prevWeekProg  "+prevWeekProg4);
			   allstaffsmapobj4.setImprovement(curWeekProg4 - prevWeekProg4);
			   allstaffsmapobj4.setPrevWeekProgress(prevWeekProg4);
				//System.out.println(allStaffCompletePer4);
				allstaffsmapobj4.setDeptId(4);
				allstaffsmapobj4.setStaffCount(satffCountDeptId4);
				allstaffsmapobj4.setStaffCompleted  (staffCompleted4);
				allstaffsmapobj4.setRemainingStaffs(  satffCountDeptId4- staffCompleted4);
				allstaffsegprogresstable.add(3,allstaffsmapobj4);
				
				 allstaffsmapprogtable allstaffsmapobj5 = new allstaffsmapprogtable();	 
					int deptId5 = 5;float allStaffCompletePer5 = 5.0f; 
				 	int  satffCountDeptId5=  objCCPUserActRepository.getTotalStaffsDept(deptId5 );
					System.out.println(satffCountDeptId5);
					int  staffCompleted5 = objCCPUserActRepository.getTotalStaffsCompleted(deptId5 );	
					//System.out.println(staffCompleted5); 
				   int curWeekProg5 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId5 );
				   //System.out.println(" curWeekProg  "+curWeekProg5);
				   allstaffsmapobj5.setCurWeekProgress(curWeekProg5);
				   int prevWeekProg5 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId5 );
				   //System.out.println( " prevWeekProg  "+prevWeekProg5);
				   allstaffsmapobj5.setImprovement(curWeekProg5 - prevWeekProg5);
				   allstaffsmapobj5.setPrevWeekProgress(prevWeekProg5);
					//System.out.println(allStaffCompletePer5);
					allstaffsmapobj5.setDeptId(5);
					allstaffsmapobj5.setStaffCount(satffCountDeptId5);
					allstaffsmapobj5.setStaffCompleted  (staffCompleted5);
					allstaffsmapobj5.setRemainingStaffs(  satffCountDeptId5- staffCompleted5);
					allstaffsegprogresstable.add(4,allstaffsmapobj5);
					
					 allstaffsmapprogtable allstaffsmapobj6 = new allstaffsmapprogtable();	 
						int deptId6 = 6;float allStaffCompletePer6 = 6.0f; 
					 	int  satffCountDeptId6=  objCCPUserActRepository.getTotalStaffsDept(deptId6 );
						System.out.println(satffCountDeptId6);
						int  staffCompleted6 = objCCPUserActRepository.getTotalStaffsCompleted(deptId6 );	
						//System.out.println(staffCompleted6); 
					   int curWeekProg6 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId6 );
					   //System.out.println(" curWeekProg  "+curWeekProg6);
					   allstaffsmapobj6.setCurWeekProgress(curWeekProg6);
					   int prevWeekProg6 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId6 );
					   //System.out.println( " prevWeekProg  "+prevWeekProg6);
					   allstaffsmapobj6.setImprovement(curWeekProg6 - prevWeekProg6);
					   allstaffsmapobj6.setPrevWeekProgress(prevWeekProg6);
						//System.out.println(allStaffCompletePer6);
						allstaffsmapobj6.setDeptId(6);
						allstaffsmapobj6.setStaffCount(satffCountDeptId6);
						allstaffsmapobj6.setStaffCompleted  (staffCompleted6);
						allstaffsmapobj6.setRemainingStaffs(  satffCountDeptId6- staffCompleted6);
						allstaffsegprogresstable.add(5,allstaffsmapobj6);

						
						 allstaffsmapprogtable allstaffsmapobj7 = new allstaffsmapprogtable();	 
							int deptId7 = 7;float allStaffCompletePer7 = 7.0f; 
						 	int  satffCountDeptId7=  objCCPUserActRepository.getTotalStaffsDept(deptId7 );
							System.out.println(satffCountDeptId7);
							int  staffCompleted7 = objCCPUserActRepository.getTotalStaffsCompleted(deptId7 );	
							//System.out.println(staffCompleted7); 
						   int curWeekProg7 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId7 );
						   //System.out.println(" curWeekProg  "+curWeekProg7);
						   allstaffsmapobj7.setCurWeekProgress(curWeekProg7);
						   int prevWeekProg7 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId7 );
						   //System.out.println( " prevWeekProg  "+prevWeekProg7);
						   allstaffsmapobj7.setImprovement(curWeekProg7 - prevWeekProg7);
						   allstaffsmapobj7.setPrevWeekProgress(prevWeekProg7);
							//System.out.println(allStaffCompletePer7);
							allstaffsmapobj7.setDeptId(7);
							allstaffsmapobj7.setStaffCount(satffCountDeptId7);
							allstaffsmapobj7.setStaffCompleted  (staffCompleted7);
							allstaffsmapobj7.setRemainingStaffs(  satffCountDeptId7- staffCompleted7);
							allstaffsegprogresstable.add(6,allstaffsmapobj7);
							
							 allstaffsmapprogtable allstaffsmapobj8 = new allstaffsmapprogtable();	 
								int deptId8 = 8;float allStaffCompletePer8 = 8.0f; 
							 	int  satffCountDeptId8=  objCCPUserActRepository.getTotalStaffsDept(deptId8 );
								System.out.println(satffCountDeptId8);
								int  staffCompleted8 = objCCPUserActRepository.getTotalStaffsCompleted(deptId8 );	
								//System.out.println(staffCompleted8); 
							   int curWeekProg8 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId8 );
							   //System.out.println(" curWeekProg  "+curWeekProg8);
							   allstaffsmapobj8.setCurWeekProgress(curWeekProg8);
							   int prevWeekProg8 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId8 );
							   //System.out.println( " prevWeekProg  "+prevWeekProg8);
							   allstaffsmapobj8.setImprovement(curWeekProg8 - prevWeekProg8);
							   allstaffsmapobj8.setPrevWeekProgress(prevWeekProg8);
								//System.out.println(allStaffCompletePer8);
								allstaffsmapobj8.setDeptId(8);
								allstaffsmapobj8.setStaffCount(satffCountDeptId8);
								allstaffsmapobj8.setStaffCompleted  (staffCompleted8);
								allstaffsmapobj8.setRemainingStaffs(  satffCountDeptId8- staffCompleted8);
								allstaffsegprogresstable.add(7,allstaffsmapobj8);

								 allstaffsmapprogtable allstaffsmapobj9 = new allstaffsmapprogtable();	 
									int deptId9 = 9;float allStaffCompletePer9 = 9.0f; 
								 	int  satffCountDeptId9=  objCCPUserActRepository.getTotalStaffsDept(deptId9 );
									System.out.println(satffCountDeptId9);
									int  staffCompleted9 = objCCPUserActRepository.getTotalStaffsCompleted(deptId9 );	
									//System.out.println(staffCompleted9); 
								   int curWeekProg9 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId9 );
								   //System.out.println(" curWeekProg  "+curWeekProg9);
								   allstaffsmapobj9.setCurWeekProgress(curWeekProg9);
								   int prevWeekProg9 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId9 );
								   //System.out.println( " prevWeekProg  "+prevWeekProg9);
								   allstaffsmapobj9.setImprovement(curWeekProg9 - prevWeekProg9);
								   allstaffsmapobj9.setPrevWeekProgress(prevWeekProg9);
									//System.out.println(allStaffCompletePer9);
									allstaffsmapobj9.setDeptId(9);
									allstaffsmapobj9.setStaffCount(satffCountDeptId9);
									allstaffsmapobj9.setStaffCompleted  (staffCompleted9);
									allstaffsmapobj9.setRemainingStaffs(  satffCountDeptId9- staffCompleted9);
									allstaffsegprogresstable.add(8,allstaffsmapobj9);
									
									 allstaffsmapprogtable allstaffsmapobj10 = new allstaffsmapprogtable();	 
										int deptId10 = 10;float allStaffCompletePer10 = 10.0f; 
									 	int  satffCountDeptId10=  objCCPUserActRepository.getTotalStaffsDept(deptId10 );
										System.out.println(satffCountDeptId10);
										int  staffCompleted10 = objCCPUserActRepository.getTotalStaffsCompleted(deptId10 );	
										//System.out.println(staffCompleted10); 
									   int curWeekProg10 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId10 );
									   //System.out.println(" curWeekProg  "+curWeekProg10);
									   allstaffsmapobj10.setCurWeekProgress(curWeekProg10);
									   int prevWeekProg10 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId10 );
									   //System.out.println( " prevWeekProg  "+prevWeekProg10);
									   allstaffsmapobj10.setImprovement(curWeekProg10 - prevWeekProg10);
									   allstaffsmapobj10.setPrevWeekProgress(prevWeekProg10);
										//System.out.println(allStaffCompletePer10);
										allstaffsmapobj10.setDeptId(10);
										allstaffsmapobj10.setStaffCount(satffCountDeptId10);
										allstaffsmapobj10.setStaffCompleted  (staffCompleted10);
										allstaffsmapobj10.setRemainingStaffs(  satffCountDeptId10- staffCompleted10);
										allstaffsegprogresstable.add(9,allstaffsmapobj10);

										
										 allstaffsmapprogtable allstaffsmapobj11 = new allstaffsmapprogtable();	 
											int deptId11 = 11;float allStaffCompletePer11 = 11.0f; 
										 	int  satffCountDeptId11=  objCCPUserActRepository.getTotalStaffsDept(deptId11 );
											System.out.println(satffCountDeptId11);
											int  staffCompleted11 = objCCPUserActRepository.getTotalStaffsCompleted(deptId11 );	
											//System.out.println(staffCompleted11); 
										   int curWeekProg11 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId11 );
										   //System.out.println(" curWeekProg  "+curWeekProg11);
										   allstaffsmapobj11.setCurWeekProgress(curWeekProg11);
										   int prevWeekProg11 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId11 );
										   //System.out.println( " prevWeekProg  "+prevWeekProg11);
										   allstaffsmapobj11.setImprovement(curWeekProg11 - prevWeekProg11);
										   allstaffsmapobj11.setPrevWeekProgress(prevWeekProg11);
											//System.out.println(allStaffCompletePer11);
											allstaffsmapobj11.setDeptId(11);
											allstaffsmapobj11.setStaffCount(satffCountDeptId11);
											allstaffsmapobj11.setStaffCompleted  (staffCompleted11);
											allstaffsmapobj11.setRemainingStaffs(  satffCountDeptId11- staffCompleted11);
											allstaffsegprogresstable.add(10,allstaffsmapobj11);

											
											 allstaffsmapprogtable allstaffsmapobj12 = new allstaffsmapprogtable();	 
												int deptId12 = 12;float allStaffCompletePer12 = 12.0f; 
											 	int  satffCountDeptId12=  objCCPUserActRepository.getTotalStaffsDept(deptId12 );
												System.out.println(satffCountDeptId12);
												int  staffCompleted12 = objCCPUserActRepository.getTotalStaffsCompleted(deptId12 );	
												//System.out.println(staffCompleted12); 
											   int curWeekProg12 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId12 );
											   //System.out.println(" curWeekProg  "+curWeekProg12);
											   allstaffsmapobj12.setCurWeekProgress(curWeekProg12);
											   int prevWeekProg12 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId12 );
											   //System.out.println( " prevWeekProg  "+prevWeekProg12);
											   allstaffsmapobj12.setImprovement(curWeekProg12 - prevWeekProg12);
											   allstaffsmapobj12.setPrevWeekProgress(prevWeekProg12);
												//System.out.println(allStaffCompletePer12);
												allstaffsmapobj12.setDeptId(12);
												allstaffsmapobj12.setStaffCount(satffCountDeptId12);
												allstaffsmapobj12.setStaffCompleted  (staffCompleted12);
												allstaffsmapobj12.setRemainingStaffs(  satffCountDeptId12- staffCompleted12);
												allstaffsegprogresstable.add(11,allstaffsmapobj12);

												 allstaffsmapprogtable allstaffsmapobj13 = new allstaffsmapprogtable();	 
													int deptId13 = 13;float allStaffCompletePer13 = 13.0f; 
												 	int  satffCountDeptId13=  objCCPUserActRepository.getTotalStaffsDept(deptId13 );
													System.out.println(satffCountDeptId13);
													int  staffCompleted13 = objCCPUserActRepository.getTotalStaffsCompleted(deptId13 );	
													//System.out.println(staffCompleted13); 
												   int curWeekProg13 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId13 );
												   //System.out.println(" curWeekProg  "+curWeekProg13);
												   allstaffsmapobj13.setCurWeekProgress(curWeekProg13);
												   int prevWeekProg13 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId13 );
												   //System.out.println( " prevWeekProg  "+prevWeekProg13);
												   allstaffsmapobj13.setImprovement(curWeekProg13 - prevWeekProg13);
												   allstaffsmapobj13.setPrevWeekProgress(prevWeekProg13);
													//System.out.println(allStaffCompletePer13);
													allstaffsmapobj13.setDeptId(13);
													allstaffsmapobj13.setStaffCount(satffCountDeptId13);
													allstaffsmapobj13.setStaffCompleted  (staffCompleted13);
													allstaffsmapobj13.setRemainingStaffs(  satffCountDeptId13- staffCompleted13);
													allstaffsegprogresstable.add(12,allstaffsmapobj13);
												
													 allstaffsmapprogtable allstaffsmapobj14 = new allstaffsmapprogtable();	 
														int deptId14 = 14;float allStaffCompletePer14 = 14.0f; 
													 	int  satffCountDeptId14=  objCCPUserActRepository.getTotalStaffsDept(deptId14 );
														System.out.println(satffCountDeptId14);
														int  staffCompleted14 = objCCPUserActRepository.getTotalStaffsCompleted(deptId14 );	
														//System.out.println(staffCompleted14); 
													   int curWeekProg14 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId14 );
													   //System.out.println(" curWeekProg  "+curWeekProg14);
													   allstaffsmapobj14.setCurWeekProgress(curWeekProg14);
													   int prevWeekProg14 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId14 );
													   //System.out.println( " prevWeekProg  "+prevWeekProg14);
													   allstaffsmapobj14.setImprovement(curWeekProg14 - prevWeekProg14);
													   allstaffsmapobj14.setPrevWeekProgress(prevWeekProg14);
														//System.out.println(allStaffCompletePer14);
														allstaffsmapobj14.setDeptId(14);
														allstaffsmapobj14.setStaffCount(satffCountDeptId14);
														allstaffsmapobj14.setStaffCompleted  (staffCompleted14);
														allstaffsmapobj14.setRemainingStaffs(  satffCountDeptId14- staffCompleted14);
														allstaffsegprogresstable.add(13,allstaffsmapobj14);
														 allstaffsmapprogtable allstaffsmapobj15 = new allstaffsmapprogtable();	 
															int deptId15 = 15;float allStaffCompletePer15 = 15.0f; 
														 	int  satffCountDeptId15=  objCCPUserActRepository.getTotalStaffsDept(deptId15 );
															System.out.println(satffCountDeptId15);
															int  staffCompleted15 = objCCPUserActRepository.getTotalStaffsCompleted(deptId15 );	
															//System.out.println(staffCompleted15); 
														   int curWeekProg15 =  objCCPUserActRepository.getCurrentWeekPerEmp(deptId15 );
														   //System.out.println(" curWeekProg  "+curWeekProg15);
														   allstaffsmapobj15.setCurWeekProgress(curWeekProg15);
														   int prevWeekProg15 =  objCCPUserActRepository.getPrevWeekPerEmp(deptId15 );
														   //System.out.println( " prevWeekProg  "+prevWeekProg15);
														   allstaffsmapobj15.setImprovement(curWeekProg15 - prevWeekProg15);
														   allstaffsmapobj15.setPrevWeekProgress(prevWeekProg15);
															//System.out.println(allStaffCompletePer15);
															allstaffsmapobj15.setDeptId(15);
															allstaffsmapobj15.setStaffCount(satffCountDeptId15);
															allstaffsmapobj15.setStaffCompleted  (staffCompleted15);
															allstaffsmapobj15.setRemainingStaffs(  satffCountDeptId15- staffCompleted15);
															allstaffsegprogresstable.add(14,allstaffsmapobj15);
	
	
	// TODO Auto-generated method stub
	return allstaffsegprogresstable;
	
}  

 
 

}