package com.rsvp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsvp.entity.RsvpCity;

@Repository
public interface CityRepository extends JpaRepository<RsvpCity, Long> {
	
	List<RsvpCity> findByActive(String active);
}
