package com.rsvp.controller;

import java.util.List;

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

import com.rsvp.entity.RsvpCity;
import com.rsvp.entity.RsvpDate;
import com.rsvp.entity.RsvpTime;
import com.rsvp.service.CityService;
import com.rsvp.service.DateService;
import com.rsvp.service.RegistrantService;
import com.rsvp.service.ReservationService;

@RestController
public class RsvpController {

	static Logger log = LogManager.getLogger();

	@Autowired
	private CityService cityservice;

	@Autowired
	private DateService dateService;

	@Autowired
	private RegistrantService regService;
	
	@Autowired
	private ReservationService resvService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homeScreen(ModelMap model) {
		return new ModelAndView("view");
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminScreen(ModelAndView model) {
		model.setViewName("admin");
		model.getModelMap().addAttribute("users", regService.getRegistrantsByRsvped());
		return model;
	}

	@RequestMapping("/cities")
	public List<RsvpCity> getCities() {
		return cityservice.getCities();
	}

	@RequestMapping("/dates")
	public List<RsvpDate> getDates(@RequestParam Long cityId) {
		return cityservice.getDatesById(cityId);
	}

	@RequestMapping("/time")
	public List<RsvpTime> getTime(@RequestParam Long dateId) {
		return dateService.getTimeById(dateId);
	}

	@PostMapping("/validateEmail")
	public ModelAndView validateEmail(@RequestParam String email, ModelAndView model) {
		if (!regService.getRegistrantByEmail(email).isEmpty()) {
			model.getModelMap().addAttribute("user", regService.getRegistrantByEmail(email).get(0));
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

		boolean resvMade = resvService.makeReservation(userId, cityId, dateId, timeId, model);	
		if(resvMade)
				model.getModelMap().addAttribute("success", "RSVP Done Successfully!");
			else 
				model.getModelMap().addAttribute("failure", "The given slot is already booked.Please try again!");
		
		model.setViewName("view");
		return model;
	}
}
