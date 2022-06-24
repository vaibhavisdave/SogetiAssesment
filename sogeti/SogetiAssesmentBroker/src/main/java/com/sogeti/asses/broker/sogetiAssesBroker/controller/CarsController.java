package com.sogeti.asses.broker.sogetiAssesBroker.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.asses.broker.sogetiAssesBroker.service.BrokerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cars")
public class CarsController {

	@Autowired
	BrokerService service;
	
	@ApiOperation(value = "Fetches all Cars.", response = Map.class)
	@GetMapping(value = "/data")
	public Map<String, Double> getCars() {
		return service.getCars();
	}
}
