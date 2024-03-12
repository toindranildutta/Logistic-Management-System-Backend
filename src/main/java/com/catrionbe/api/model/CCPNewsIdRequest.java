package com.catrionbe.api.model;
 
import java.io.Serializable;
import java.util.Date;

public class CCPNewsIdRequest  implements Serializable {

	
    private static final long serialVersionUID = 5926468583005150707L;


	private int newsId;


	public int getNewsId() {
		return newsId;
	}


	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
}