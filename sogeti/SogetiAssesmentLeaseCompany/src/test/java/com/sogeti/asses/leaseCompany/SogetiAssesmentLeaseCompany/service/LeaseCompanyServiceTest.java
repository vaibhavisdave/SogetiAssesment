package com.sogeti.asses.leaseCompany.SogetiAssesmentLeaseCompany.service;

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

import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.dto.CarDto;
import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.service.LeaseCompanyService;
/**
 * Class to test LeaseCompanyService.
 *
 * @author vighn
 *
 */

@SpringBootTest(classes = {com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany
                           .SogetiAssesmentLeaseCompanyApplication.class })
public class LeaseCompanyServiceTest {

  @Autowired
  LeaseCompanyService service;

  @Test
  void testCreate() {
    CarDto car = getCarDto();

    long id = service.create(car);
    assertNotEquals(id, 0);
  }

  @Test
  void testCreateException() {
    CarDto car = getCarDto();
    car.setModel(null);

    Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
      service.create(car);
    });
  }

  @Test
  void testUpdate() {
    Optional<CarDto> carOptional = service.findById(1);
    carOptional.ifPresent(car -> {
      CarDto carEdit = car;
      carEdit.setMake("MakeUpdated");
      carEdit.setStartDate("21-06-2022");
      carEdit.setNoOfDoors(2);
      carEdit.setVersion(2);

      CarDto carUpdate = service.update(carEdit);
      assertEquals(carEdit, carUpdate);
    });

  }

  @Test
  void testUpdateException() {
    CarDto car = getCarDto();
    service.create(car);
    car.setId(10000L);
    CarDto carUpdate = service.update(car);
    assertNull(carUpdate);
  }

  @Test
  void testFind() {

    List<CarDto> cars = service.findAll();
    assertNotNull(cars);
    assertEquals(cars.size(), 1);
    Optional<CarDto> car = service.findById(1);
    assertNotNull(car);
    assertNotNull(car.get());
    assertEquals(car.get().getId(), 1);

  }

  @Test
  void testDelete() {

    service.deleteById(1L);
    Assertions.assertThrows(NoSuchElementException.class, () -> {
      service.findById(1L).get();
    });

  }

  private CarDto getCarDto() {
    return CarDto.builder()
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
