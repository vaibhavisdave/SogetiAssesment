/**
 * 
 */
package com.sogeti.asses.broker.SogetiAssesmentBroker.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogeti.asses.broker.sogetiAssesBroker.dto.CustomerDTO;
import com.sogeti.asses.broker.sogetiAssesBroker.entity.Customer;
import com.sogeti.asses.broker.sogetiAssesBroker.service.BrokerService;

/**
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.broker.sogetiAssesBroker.SogetiAssesmentBrokerApplication.class})
@AutoConfigureMockMvc
public class CarsControllerTest {
	@MockBean
	BrokerService service;
	
	@Autowired
	MockMvc mvc;

	
	@Test
	void testFind() throws JsonProcessingException, Exception  {
		Map<String, Double> map = new HashMap<>();
		map.put("name", 1000d);
		map.put("name1", 10d);
		when(service.getCars()).thenReturn(map);
		mvc.perform(get("/data").contentType(MediaType.APPLICATION_JSON))
				.andExpect(result ->{
					MockMvcResultMatchers.status().is(200);
					MockMvcResultMatchers.jsonPath("$.data").isMap();
					MockMvcResultMatchers.model().size(2);
				} );
	}
}
