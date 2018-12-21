/*package com.rsvp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rsvp.entity.Registrant;
import com.rsvp.repository.RegistrantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrantServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private RegistrantRepository regRepo;
	
	private static final String ACTIVE = "Y";
	
	@Test
	public List<Registrant> testGetRegistrantByEmail(String emailId) {		
		return regRepo.findByEmailIdIgnoreCaseAndActive(emailId, ACTIVE);
	}
	
	public List<Registrant> getRegistrants() {
		return regRepo.findAll();
	}
	
	public Registrant getRegistrantById(Long userId) {
		return regRepo.getOne(userId);
	}
	
	public Registrant saveRegistrant(Registrant user) {
		return regRepo.save(user);
	}
}
*/