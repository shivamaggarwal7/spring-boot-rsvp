package com.rsvp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsvp.entity.Registrant;

@Repository
public interface RegistrantRepository extends JpaRepository<Registrant, Long> {
	
	List<Registrant> findByEmailIdIgnoreCaseAndActive(String emailId,String active);
	
	List<Registrant> findByRsvpFlag(boolean rsvpd);
}
