package com.rsvp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Registrant {

	@Id
	@GeneratedValue
	private Long userId;
	private String emailId;
	private String firstName;
	private String lastName;
	private String dob;
	private String phoneNo;
	private String city;
	private String dateOfInt;
	private String lang;
	private boolean rsvpFlag = false;
	private String active = "Y";
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public boolean getRsvpFlag() {
		return rsvpFlag;
	}
	public void setRsvpFlag(boolean rsvpFlag) {
		this.rsvpFlag = rsvpFlag;
	}
	public String getDateOfInt() {
		return dateOfInt;
	}
	public void setDateOfInt(String dateOfInt) {
		this.dateOfInt = dateOfInt;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	@Override
	public String toString() {
		return "Registrant [userId=" + userId + ", emailId=" + emailId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob=" + dob + ", phoneNo=" + phoneNo + ", city=" + city + ", rsvpFlag=" + rsvpFlag
				+ ", active=" + active + "]";
	}
	
	
}
