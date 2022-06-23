/**
 * 
 */
package com.sogeti.asses.leaseCompany.SogetiAssesmentLeaseCompany.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;
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
import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.dto.CarDTO;
import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.service.LeaseCompanyService;

/**
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.SogetiAssesmentLeaseCompanyApplication.class})
@AutoConfigureMockMvc
public class LeaseCompanyControllerTest {
	@MockBean
	LeaseCompanyService service;
	
	@Autowired
	MockMvc mvc;
	
	@Autowired
	  private ObjectMapper objectMapper;
	
	@Test
	void testCreate() throws JsonProcessingException, Exception  {
		CarDTO car = getCarDTO();
		when(service.create(car)).thenReturn(1l);
		mvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(car)))
		.andExpect(result ->{
			MockMvcResultMatchers.status().is(200);
			MockMvcResultMatchers.jsonPath("$.data").value(1);
		} );
	}
	
	@Test
	void testUpdate() throws JsonProcessingException, Exception  {
		CarDTO car = getCarDTO();
		car.setId(1l);
		when(service.update(car)).thenReturn(car);
		mvc.perform(put("/cars/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(car)))
				.andExpect(result ->{
					MockMvcResultMatchers.status().is(200);
					MockMvcResultMatchers.jsonPath("$.data").value(car);
				} );
	}
	
	@Test
	void testFind() throws JsonProcessingException, Exception  {
		CarDTO car = getCarDTO();
		car.setId(1l);
		when(service.findAll()).thenReturn(List.of(car));
		mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON))
				.andExpect(result ->{
					MockMvcResultMatchers.status().is(200);
					MockMvcResultMatchers.jsonPath("$.data").isNotEmpty();
					MockMvcResultMatchers.model().size(1);
				} );
		when(service.findById(1l)).thenReturn(Optional.of(car));
		mvc.perform(get("/cars/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(result ->{
					MockMvcResultMatchers.status().is(200);
					MockMvcResultMatchers.jsonPath("$.data").isNotEmpty();
					MockMvcResultMatchers.jsonPath("$.data").value(car);;
				} );
	}

	@Test
	void testDelete() throws JsonProcessingException, Exception  {
		doNothing().when(service).deleteById(1);
		mvc.perform(delete("/cars/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(result ->{
					MockMvcResultMatchers.status().is(200);
					MockMvcResultMatchers.jsonPath("$.data").value("Car deleted successfully");
				} );
	}

	private CarDTO getCarDTO() {
		return CarDTO.builder()
		.co2Emission("co2Emission")
		.duration(6)
		.grossPrice(100.00)
		.interestRate(0.8)
		.make("make")
		.mileage(50)
		.model("model")
		.nettPrice(1000)
		.noOfDoors(4)
		.startDate("21-06-2022")
		.version(1)
		.build();
	}

}
