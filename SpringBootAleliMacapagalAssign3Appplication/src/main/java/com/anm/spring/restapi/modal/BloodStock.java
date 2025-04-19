package com.anm.spring.restapi.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BloodStock {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String bloodGroup;
	    private Integer quantity;
	    private String bestBefore;
	    private String status;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getBloodGroup() {
			return bloodGroup;
		}
		public void setBloodGroup(String bloodGroup) {
			this.bloodGroup = bloodGroup;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getBestBefore() {
			return bestBefore;
		}
		public void setBestBefore(String bestBefore) {
			this.bestBefore = bestBefore;
		}
		/*
		 * public LocalDate getBestBefore() { return bestBefore; } public void
		 * setBestBefore(LocalDate bestBefore) { this.bestBefore = bestBefore; }
		 */
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
}
