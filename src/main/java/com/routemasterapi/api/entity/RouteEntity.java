package com.routemasterapi.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "indranil_route")
public class RouteEntity {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int routeId;

	    @Column(name = "pincode", nullable = false)
	    private String pincode;

	    @Column(name = "routeName", nullable = false)
	    private String routeName;

	    @Column(name = "description", nullable = false)
	    private String description;
	    
	    @Column(name = "totalDistance", nullable = false)
	    private double totalDistance;
	    
	    
	    
		public RouteEntity() {
			super();
			
		}

	
		public RouteEntity(int routeId, String pincode, String routeName, String description, double totalDistance) {
			super();
			this.routeId = routeId;
			this.pincode = pincode;
			this.routeName = routeName;
			this.description = description;
			this.totalDistance = totalDistance;
		}


		public int getRouteId() {
			return routeId;
		}

		public void setRouteId(int routeId) {
			this.routeId = routeId;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getRouteName() {
			return routeName;
		}

		public void setRouteName(String routeName) {
			this.routeName = routeName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getTotalDistance() {
			return totalDistance;
		}

		public void setTotalDistance(double totalDistance) {
			this.totalDistance = totalDistance;
		}


		@Override
		public String toString() {
			return "RouteEntity [routeId=" + routeId + ", pincode=" + pincode + ", routeName=" + routeName
					+ ", description=" + description + ", totalDistance=" + totalDistance + "]";
		}

  

}
