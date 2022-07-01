package com.sogeti.asses.leasecompany.sogetiassesleasecompany.controller;

import com.sogeti.asses.leasecompany.sogetiassesleasecompany.dto.CarDto;
import com.sogeti.asses.leasecompany.sogetiassesleasecompany.entity.Car;
import com.sogeti.asses.leasecompany.sogetiassesleasecompany.service.LeaseCompanyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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

/**
 *Rest APIS for perform CURD on Cars.
 *
 * @author vighn
 */
@RestController
@RequestMapping("/cars")
public class LeaseCompanyController {

  @Autowired
  LeaseCompanyService service;

  @ApiOperation(value = "Adds Car.")
  @ApiResponse(code = 200, message = "Car added successfully")
  @PostMapping
  public long add(@RequestBody CarDto car) {
    return service.create(car);
  }

  @ApiOperation(value = "Updates Car.")
  @ApiResponse(code = 200, message = "Car updated successfully")
  @PutMapping
  public CarDto update(@RequestBody CarDto car) {
    return service.update(car);
  }

  @ApiOperation(value = "Fetches all Cars.", response = List.class)
  @GetMapping
  public List<CarDto> find() {
    return service.findAll();
  }

  @ApiOperation(value = "Fetches Car by Id.", response = Car.class)
  @GetMapping(value = "/{id}")
  public Optional<CarDto> find(@PathVariable long id) {
    return service.findById(id);
  }

  @ApiOperation(value = "Deletes Car.")
  @ApiResponse(code = 200, message = "Car deleted successfully")
  @DeleteMapping(value = "/{id}")
  public String delete(@PathVariable long id) {
    service.deleteById(id);
    return "Car deleted successfully";
  }

}
