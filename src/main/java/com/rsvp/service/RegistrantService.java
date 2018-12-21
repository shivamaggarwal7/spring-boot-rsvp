package com.rsvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsvp.entity.Registrant;
import com.rsvp.repository.RegistrantRepository;

@Service
public class RegistrantService {
	
	@Autowired
	private RegistrantRepository regRepo;
	
	private static final String ACTIVE = "Y";
	private static final boolean RSVPED = true;
	
	public List<Registrant> getRegistrantByEmail(String emailId) {
		return regRepo.findByEmailIdIgnoreCaseAndActive(emailId, ACTIVE);
	}
	
	public List<Registrant> getRegistrantsByRsvped() {
		return regRepo.findByRsvpFlag(RSVPED);
	}
	
	public Registrant getRegistrantById(Long userId) {
		return regRepo.getOne(userId);
	}
	
	public Registrant saveRegistrant(Registrant user) {
		return regRepo.save(user);
	}
}
