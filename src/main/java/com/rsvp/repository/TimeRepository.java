package com.rsvp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsvp.entity.RsvpTime;

@Repository
public interface TimeRepository extends JpaRepository<RsvpTime, Long> {
	
	List<RsvpTime> findByDateAndActive(Long dateId,String active);
}
