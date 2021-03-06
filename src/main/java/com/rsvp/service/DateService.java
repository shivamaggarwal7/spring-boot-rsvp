package com.rsvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsvp.entity.RsvpCity;
import com.rsvp.entity.RsvpDate;
import com.rsvp.entity.RsvpTime;
import com.rsvp.repository.DateRepository;

@Service
public class DateService{
	
	@Autowired
	private DateRepository dateRepo;
	
	private static final String ACTIVE = "Y";

	public List<RsvpDate> getDates(Long cityId) {
		return dateRepo.findByCityAndActive(cityId, ACTIVE);
	}
	
	public RsvpDate getDateById(Long dateId) {
		return dateRepo.getOne(dateId);
	}
	
	public List<RsvpTime> getTimeById(Long dateId)
	{
		RsvpDate date = dateRepo.getOne(dateId);
		return date.getTimes();
	}
	
	public RsvpDate saveDate(RsvpDate date) {
		return dateRepo.save(date);
	}
}
