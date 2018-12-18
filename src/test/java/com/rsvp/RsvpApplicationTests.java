package com.rsvp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RsvpApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHome() throws Exception {

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/jsp/view.jsp"));
	}
	
	@Test
	public void testGetCities() throws Exception {

		mockMvc.perform(get("/cities").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]").exists());
	}
	
	@Test
	public void testGetDates() throws Exception {

		mockMvc.perform(get("/dates").param("cityId","1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]").exists());
	}
	
	@Test
	public void testGetTime() throws Exception {

		mockMvc.perform(get("/time").param("dateId","1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]").exists());
	}
	
	@Test
	public void testValidateEmail() throws Exception {

		mockMvc.perform(post("/validateEmail").param("email","s@g.in"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testReserveRSVP() throws Exception {

		mockMvc.perform(post("/reserveRSVP")
				.param("userId","1")
				.param("cityId","1")
				.param("dateId","1")
				.param("timeId","1"))
				.andExpect(status().isOk());
	}
}
