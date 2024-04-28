package com.routemasterapi.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "indranil_customer")
public class CustomerEntity {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int customerId;

	    @Column(name = "firstName", nullable = false)
	    private String firstName;

	    @Column(name = "lastName", nullable = false)
	    private String lastName;

	    @Column(name = "phone", nullable = false)
	    private String phone;
	    
	    @Column(name = "email", nullable = false)
	    private String email;
	    
	    @Column(name = "address", nullable = false)
	    private String address;

		/**
		 * 
		 */
		public CustomerEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param customerId
		 * @param firstName
		 * @param lastName
		 * @param phone
		 * @param email
		 * @param address
		 */
		public CustomerEntity(int customerId, String firstName, String lastName, String phone, String email,
				String address) {
			super();
			this.customerId = customerId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
			this.email = email;
			this.address = address;
		}

		/**
		 * @return the customerId
		 */
		public int getCustomerId() {
			return customerId;
		}

		/**
		 * @param i the customerId to set
		 */
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param firstName the firstName to set
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param lastName the lastName to set
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * @return the phone
		 */
		public String getPhone() {
			return phone;
		}

		/**
		 * @param phone the phone to set
		 */
		public void setPhone(String phone) {
			this.phone = phone;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * @param address the address to set
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "CustomerEntity [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", phone=" + phone + ", email=" + email + ", address=" + address + "]";
		}

	
	  

	   

}
