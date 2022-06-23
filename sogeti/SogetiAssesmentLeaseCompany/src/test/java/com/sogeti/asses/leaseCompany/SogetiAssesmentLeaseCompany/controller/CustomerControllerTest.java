/**
 * 
 */
package com.sogeti.asses.leaseCompany.SogetiAssesmentLeaseCompany.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.service.LeaseCompanyService;

/**
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.SogetiAssesmentLeaseCompanyApplication.class})
@AutoConfigureMockMvc
public class CustomerControllerTest {
	@MockBean
	LeaseCompanyService service;
	
	@Autowired
	MockMvc mvc;

	
	@Test
	void testFind() throws JsonProcessingException, Exception  {
		Map<String, String> map = new HashMap<>();
		map.put("name", "a@b.com");
		map.put("name1", "c@d.com");
		when(service.getCustomers()).thenReturn(map);
		mvc.perform(get("/data").contentType(MediaType.APPLICATION_JSON))
				.andExpect(result ->{
					MockMvcResultMatchers.status().is(200);
					MockMvcResultMatchers.jsonPath("$.data").isMap();
					MockMvcResultMatchers.model().size(2);
				} );
	}
}
