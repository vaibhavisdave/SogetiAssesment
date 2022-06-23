/**
 * 
 */
package com.sogeti.asses.broker.SogetiAssesmentBroker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.sogeti.asses.broker.sogetiAssesBroker.dto.CustomerDTO;
import com.sogeti.asses.broker.sogetiAssesBroker.service.BrokerService;

/**
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.broker.sogetiAssesBroker.SogetiAssesmentBrokerApplication.class})
public class BrokerServiceTest {

	@Autowired
	BrokerService service;

	@Test
	void testCreate() {
		long id = service.create(getCustomerCTO());
		
		
		assertNotEquals(id, 0);
	}
	
	@Test
	void testCreateException() {
		CustomerDTO cust = getCustomerCTO();
		cust.setName(null);
		Assertions.assertThrows(DataIntegrityViolationException.class,() -> {service.create(cust);});
	}
	;
	@Test
	void testUpdate() {
		CustomerDTO cust = getCustomerCTO();
		long id = service.create(cust);
		CustomerDTO custEdit = cust;
		custEdit.setEmail("x@y.com");
		custEdit.setName("updated");
		custEdit.setPlace("updatedPlace");
		custEdit.setId(id);
		CustomerDTO custUpdate = service.update(custEdit);
		assertEquals(custEdit, custUpdate);
	}
	
	@Test
	void testUpdateException() {
		CustomerDTO cust = getCustomerCTO();
		long id = service.create(cust);
		cust.setId(id+2);
		CustomerDTO custUpdate = service.update(cust);
		assertNull(custUpdate);
	}
	
	@Test
	void testFind() {

		List<CustomerDTO>cars =service.findAll();
		assertNotNull(cars);
		assertEquals(cars.size(),1);
		Optional<CustomerDTO> car = service.findById(1);
		assertNotNull(car);
		assertNotNull(car.get());
		assertEquals(car.get().getId(), 1);
		
	}
	
	@Test
	void testDelete() {

		service.deleteById(1L);
		Assertions.assertThrows(NoSuchElementException.class,() -> {service.findById(1L).get();});
		
	}
	

	private CustomerDTO getCustomerCTO() {
		return CustomerDTO.builder()
		.email("a@b.com")
		.houseNo(24)
		.name("name")
		.phNumber("123456")
		.place("place")
		.street("street")
		.zipCode("11236")
		.build();

	}
}
