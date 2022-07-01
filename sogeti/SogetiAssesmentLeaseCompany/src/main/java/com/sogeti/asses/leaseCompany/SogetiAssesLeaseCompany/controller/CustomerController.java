package com.sogeti.asses.leasecompany.sogetiassesleasecompany.controller;

import com.sogeti.asses.leasecompany.sogetiassesleasecompany.service.LeaseCompanyService;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





/**
 * Rest APIS to communicate to BROKERSERVICE.
 *
 * @author vighn 
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  LeaseCompanyService service;

  @ApiOperation(value = "Fetches all Customers.", response = Map.class)
  @GetMapping
  public Map<String, String> getCustomers() {
    return service.getCustomers();
  }
}
