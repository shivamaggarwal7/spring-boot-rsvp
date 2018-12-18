package com.rsvp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RsvpTime {
	
	@Id
	@GeneratedValue
	private Long timeId;
	private String time;
	private Long dateId;
	private String active;
	private boolean slotBooked=false;
	
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
	public Long getDateId() {
		return dateId;
	}
	public void setDateId(Long dateId) {
		this.dateId = dateId;
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

}
