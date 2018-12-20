package com.rsvp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class RsvpDate {

	@Id
	@GeneratedValue
	private Long dateId;
	private String date;
	private String active;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cityId")
	@JsonBackReference
	private RsvpCity city;
	
	@JsonManagedReference
	@OneToMany(mappedBy="date")
	private List<RsvpTime> times;
	
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public RsvpCity getCity() {
		return city;
	}
	public void setCity(RsvpCity city) {
		this.city = city;
	}
	public List<RsvpTime> getTimes() {
		return times;
	}
	public void setTimes(List<RsvpTime> times) {
		this.times = times;
	}	
}
