package com.rsvp.service;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.rsvp.entity.Registrant;
import com.rsvp.entity.RsvpCity;
import com.rsvp.entity.RsvpDate;
import com.rsvp.entity.RsvpTime;

@Service
public class ReservationService {

	static Logger log = LogManager.getLogger();
	@Autowired
	private CityService cityservice;

	@Autowired
	private DateService dateService;

	@Autowired
	private TimeService timeService;

	@Autowired
	private RegistrantService regService;

	private static final String INACTIVE = "N";
	private static final boolean SLOT_BOOKED = true;

	public boolean makeReservation(Long userId, Long cityId, Long dateId, Long timeId, ModelAndView model) {

	try {	
		if (!timeService.getTimeById(timeId).isSlotBooked()) {
			/* Update User to Inactive */
			Registrant user = regService.getRegistrantById(userId);
			user.setActive(INACTIVE);
			user.setRsvpFlag(SLOT_BOOKED);
			regService.saveRegistrant(user);

			/* Update Selected time to Inactive */
			RsvpTime time = timeService.getTimeById(timeId);
			time.setActive(INACTIVE);
			time.setSlotBooked(SLOT_BOOKED);
			timeService.saveTime(time);

			/*
			 * Check for all times for given date,if all inactive,set date as inactive too
			 */
			if (dateService.getTimeById(dateId).isEmpty()) {
				RsvpDate date = dateService.getDateById(dateId);
				date.setActive(INACTIVE);
				dateService.saveDate(date);
			}

			/*
			 * Check for all times for given date,if all inactive,set date as inactive too
			 */
			if (cityservice.getDatesById(cityId).isEmpty()) {
				RsvpCity city = cityservice.getCityById(cityId);
				city.setActive(INACTIVE);
				cityservice.saveCity(city);
			}
		  return true;		
		}
		else
			return false;
	}
	catch (EntityNotFoundException e) {
		log.error("User/date/time not found");
		return false;
	}
	}
}