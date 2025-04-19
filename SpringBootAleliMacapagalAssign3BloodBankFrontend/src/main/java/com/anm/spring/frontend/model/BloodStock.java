package com.anm.spring.frontend.model;

import java.time.LocalDate;

public class BloodStock {
    private Long id;
    private String bloodGroup;
    private Integer quantity;
    private LocalDate bestBefore;
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
	public LocalDate getBestBefore() {
		return bestBefore;
	}
	public void setBestBefore(LocalDate bestBefore) {
		this.bestBefore = bestBefore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
