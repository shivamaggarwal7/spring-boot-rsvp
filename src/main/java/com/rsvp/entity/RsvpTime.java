package com.rsvp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class RsvpTime {
	
	@Id
	@GeneratedValue
	private Long timeId;
	private String time;
	private String active;
	private boolean slotBooked=false;
	
	@ManyToOne
	@JoinColumn(name="dateId")
	@JsonBackReference
	private RsvpDate date;
	
	public Long getTimeId() {
		return timeId;
	}
	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public boolean isSlotBooked() {
		return slotBooked;
	}
	public void setSlotBooked(boolean slotBooked) {
		this.slotBooked = slotBooked;
	}
	public RsvpDate getDate() {
		return date;
	}
	public void setDate(RsvpDate date) {
		this.date = date;
	}

}
