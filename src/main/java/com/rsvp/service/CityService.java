package com.rsvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsvp.entity.RsvpCity;
import com.rsvp.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo;
	
	private static final String ACTIVE = "Y";
	
	public List<RsvpCity> getCities() {
		return cityRepo.findByActive(ACTIVE);
	}
	
	public RsvpCity getCityById(Long cityId)
	{
		return cityRepo.getOne(cityId);
	}
	
	public RsvpCity saveCity(RsvpCity city)
	{
		return cityRepo.save(city);
	}
}
