/**
 * 
 */
package com.sogeti.asses.broker.sogetiAssesBroker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.asses.broker.sogetiAssesBroker.dto.CustomerDTO;
import com.sogeti.asses.broker.sogetiAssesBroker.entity.Customer;
import com.sogeti.asses.broker.sogetiAssesBroker.service.BrokerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author vighn
 *
 */
@RestController
@RequestMapping("/customers")
public class BrokerController {

	@Autowired
	BrokerService service;
	
	@ApiOperation(value = "Adds Customer.")
	@ApiResponse(code = 200, message = "Customer added successfully")
	@PostMapping
	public long add(@RequestBody CustomerDTO customer) {
		return service.create(customer);
	}
	
	@ApiOperation(value = "Updates Customer.")
	@ApiResponse(code = 200, message = "Customer updated successfully")
	@PutMapping
	public CustomerDTO update(@RequestBody CustomerDTO customer) {
		return service.update(customer);
	}
	
	@ApiOperation(value = "Fetches all Customers.", response = List.class)
	@GetMapping
	public List<CustomerDTO> find() {
		return service.findAll();
	}
	@ApiOperation(value = "Fetches Customer by Id.", response = Customer.class)
	@GetMapping(value = "/{id}")
	public Optional<CustomerDTO> findById(@PathVariable long id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Deletes Customer.")
	@ApiResponse(code = 200, message = "Customer deleted successfully")
	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable long id) {
		service.deleteById(id);
		return "Customer deleted successfully";
	}
}
