package com.rsvp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsvp.entity.Registrant;
import com.rsvp.entity.RsvpTime;

@Repository
public interface RegistrantRepository extends JpaRepository<Registrant, Long> {
	
	List<Registrant> findByEmailIdAndActive(String emailId,String active);
}
