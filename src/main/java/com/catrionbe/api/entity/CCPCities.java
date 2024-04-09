package com.catrionbe.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 
	
	@Entity
	@Table(name = "ccpcities")
	public class CCPCities {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "cityId", nullable = false)
		private int cityId;
		
		public int getCityId() {
			return cityId;
		}

		public void setCityId(int cityId) {
			this.cityId = cityId;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		@Column(name = "cityName", nullable = false)
		private String  cityName;
		
}
