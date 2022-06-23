/**
 * 
 */
package com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.entity.Car;
import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.service.LeaseCompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author vighn
 *
 */
@RestController
public class LeaseCompanyController {

	@Autowired
	LeaseCompanyService service;
	
	@ApiOperation(value = "Adds Car.")
	@ApiResponse(code = 200, message = "Car added successfully")
	@PostMapping(value = "/add")
	public long add(@RequestBody Car car) {
		return service.create(car);
	}
	
	@ApiOperation(value = "Updates Car.")
	@ApiResponse(code = 200, message = "Car updated successfully")
	@PutMapping(value = "/update/{id}")
	public Car update(@RequestBody Car car,  @PathVariable long id) {
		return service.update(car, id);
	}
	
	@ApiOperation(value = "Fetches all Car.", response = List.class)
	@GetMapping(value = "/find")
	public List<Car> find() {
		return service.findAll();
	}
	@ApiOperation(value = "Fetches Car by Id.", response = Car.class)
	@GetMapping(value = "/{id}")
	public Optional<Car> find(@PathVariable long id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Deletes Car.")
	@ApiResponse(code = 200, message = "Car deleted successfully")
	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable long id) {
		service.deleteById(id);
		return "Car deleted successfully";
	}
	
	@ApiOperation(value = "Fetches all Customers.", response = Map.class)
	@GetMapping(value = "/getCustomers")
	public Map<String,String> getCustomers() {
		return service.getCustomers();
	}
}
