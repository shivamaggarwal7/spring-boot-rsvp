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
	
	public List<Registrant> getRegistrantByEmail(String emailId) {
		return regRepo.findByEmailIdAndActive(emailId, ACTIVE);
	}
	
	public Registrant getRegistrantById(Long userId) {
		return regRepo.getOne(userId);
	}
	
	public Registrant saveRegistrant(Registrant user) {
		return regRepo.save(user);
	}
}
