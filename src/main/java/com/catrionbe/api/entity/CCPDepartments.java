package com.catrionbe.api.entity;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 
	
	@Entity
	@Table(name = "ccpdepartments")
	public class CCPDepartments {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "deptId", nullable = false)
		private int deptId;
		
		public int getDeptId() {
			return deptId;
		}

		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		@Column(name = "deptName", nullable = false)
		private String  deptName;
		
}
