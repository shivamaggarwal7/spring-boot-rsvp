package com.rsvp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class RsvpCity {
	@Id
	@GeneratedValue
	private Long cityId;
	private String cityName;
	private String active;
	
	@OneToMany(mappedBy="city")
	@JsonManagedReference
	private List<RsvpDate> dates;
	
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public List<RsvpDate> getDates() {
		return dates;
	}
	public void setDates(List<RsvpDate> dates) {
		this.dates = dates;
	}
	
	
}