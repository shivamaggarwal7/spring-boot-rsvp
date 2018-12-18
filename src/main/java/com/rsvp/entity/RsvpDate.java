package com.rsvp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RsvpDate {

	@Id
	@GeneratedValue
	private Long dateId;
	private String date;
	private Long cityId;
	private String active;
	
	public Long getDateId() {
		return dateId;
	}
	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
}
