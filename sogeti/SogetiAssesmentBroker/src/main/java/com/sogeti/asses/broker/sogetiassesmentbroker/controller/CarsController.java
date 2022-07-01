package com.sogeti.asses.broker.sogetiassesmentbroker.controller;

import com.sogeti.asses.broker.sogetiassesmentbroker.service.BrokerService;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API to communicate LEASECOMPANYSERVICE.
 *
 * @author vighn
 */
@RestController
@RequestMapping("/cars")
public class CarsController {

  @Autowired
  BrokerService service;

  @ApiOperation(value = "Fetches all Cars.", response = Map.class)
  @GetMapping
  public Map<String, Double> getCars() {
    return service.getCars();
  }
}
