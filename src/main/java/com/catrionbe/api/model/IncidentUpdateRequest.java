package com.catrionbe.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.Type;

import com.catrionbe.api.service.DateStringDeserializer;
import com.catrionbe.api.service.DateStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IncidentUpdateRequest  implements Serializable {

	
    private static final long serialVersionUID = 5926468583005150707L;
    
    //need default constructor for JSON Parsing
    public IncidentUpdateRequest()
    {

    }
    public int getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	private int incidentId;
    private int  statusId;
	  
	   
    

}
