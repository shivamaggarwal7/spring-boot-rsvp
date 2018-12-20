package com.rsvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsvp.entity.RsvpTime;
import com.rsvp.repository.TimeRepository;

@Service
public class TimeService{
	
	@Autowired
	private TimeRepository timeRepo;
	
	private static final String ACTIVE = "Y";
	
	public List<RsvpTime> getTime(Long dateId) {
		return timeRepo.findByDateAndActive(dateId, ACTIVE);
	}
	
	public RsvpTime getTimeById(Long timeId) {
		return timeRepo.getOne(timeId);
	}
	
	public RsvpTime saveTime(RsvpTime time) {
		return timeRepo.save(time);
	}
}
