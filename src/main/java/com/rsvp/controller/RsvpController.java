package com.rsvp.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rsvp.entity.Registrant;
import com.rsvp.entity.RsvpCity;
import com.rsvp.entity.RsvpDate;
import com.rsvp.entity.RsvpTime;
import com.rsvp.repository.CityRepository;
import com.rsvp.repository.DateRepository;
import com.rsvp.repository.RegistrantRepository;
import com.rsvp.repository.TimeRepository;
import com.rsvp.service.CityService;
import com.rsvp.service.DateService;
import com.rsvp.service.TimeService;

@RestController
public class RsvpController {

	private static final String ACTIVE = "Y";
	private static final String INACTIVE = "N";
	private static final boolean SLOT_BOOKED = true;

	static Logger log = LogManager.getLogger();

	@Autowired
	private CityService cityservice;

	@Autowired
	private DateService dateService;

	@Autowired
	private TimeService timeService;

	@Autowired
	private RegistrantRepository regRepo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homeScreen(ModelMap model) {
		return new ModelAndView("view");
	}

	@RequestMapping("/cities")
	public List<RsvpCity> getCities() {
		return cityservice.getCities();
	}

	@RequestMapping("/dates")
	public List<RsvpDate> getDates(@RequestParam Long cityId) {
		return dateService.getDates(cityId);
	}

	@RequestMapping("/time")
	public List<RsvpTime> getTime(@RequestParam Long dateId) {
		return timeService.getTime(dateId);
	}

	@PostMapping("/validateEmail")
	public ModelAndView validateEmail(@RequestParam String email, ModelAndView model) {
		if (!regRepo.findByEmailIdAndActive(email, ACTIVE).isEmpty()) {
			model.getModelMap().addAttribute("user", regRepo.findByEmailIdAndActive(email, ACTIVE).get(0));
			model.getModelMap().addAttribute("cities", getCities());
			model.setViewName("rsvp");
		} else {
			model.getModelMap().addAttribute("error", "Unregistered email id or RSVP Done");
			model.setViewName("view");
		}

		return model;
	}

	@PostMapping("/reserveRSVP")
	public ModelAndView resvRSVP(@RequestParam Long userId, Long cityId, Long dateId, Long timeId, ModelAndView model) {

		try {
			if (!timeService.getTimeById(timeId).isSlotBooked()) {
				/* Update User to Inactive */
				Registrant user = regRepo.getOne(userId);
				user.setActive(INACTIVE);
				user.setRsvpFlag(SLOT_BOOKED);
				regRepo.save(user);

				/* Update Selected time to Inactive */
				RsvpTime time = timeService.getTimeById(timeId);
				time.setActive(INACTIVE);
				time.setSlotBooked(SLOT_BOOKED);
				timeService.saveTime(time);

				/*
				 * Check for all times for given date,if all inactive,set date as inactive too
				 */
				if (getTime(dateId).isEmpty()) {
					RsvpDate date = dateService.getDateById(dateId);
					date.setActive(INACTIVE);
					dateService.saveDate(date);
				}

				/*
				 * Check for all times for given date,if all inactive,set date as inactive too
				 */
				if (getDates(cityId).isEmpty()) {
					RsvpCity city = cityservice.getCityById(cityId);
					city.setActive(INACTIVE);
					cityservice.saveCity(city);
				}

				model.getModelMap().addAttribute("success", "RSVP Done Successfully!");
			} else {
				model.getModelMap().addAttribute("failure", "The given slot is already booked.Please try again!");
			}
		} catch (EntityNotFoundException e) {
			model.getModelMap().addAttribute("failure", "User/Date/Time not found!");
		}
		model.setViewName("view");
		return model;
	}
}
