package com.sogeti.asses.broker.sogetiassesbroker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.sogeti.asses.broker.sogetiassesmentbroker.dto.CustomerDto;
import com.sogeti.asses.broker.sogetiassesmentbroker.service.BrokerService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Class to test BrokerService.
 *
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.broker.sogetiassesmentbroker
                          .SogetiAssesmentBrokerApplication.class})
public class BrokerServiceTest {

  @Autowired
  BrokerService service;

  @Test
  void testCreate() {
    long id = service.create(getCustomerCto());

    assertNotEquals(id, 0);
  }

  @Test
  void testCreateException() {
    CustomerDto cust = getCustomerCto();
    cust.setName(null);
    Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
      service.create(cust);
    });
  }

  @Test
  void testUpdate() {
    CustomerDto cust = getCustomerCto();
    long id = service.create(cust);
    CustomerDto custEdit = cust;
    custEdit.setEmail("x@y.com");
    custEdit.setName("updated");
    custEdit.setPlace("updatedPlace");
    custEdit.setId(id);
    CustomerDto custUpdate = service.update(custEdit);
    assertEquals(custEdit, custUpdate);
  }

  @Test
  void testUpdateException() {
    CustomerDto cust = getCustomerCto();
    long id = service.create(cust);
    cust.setId(id + 2);
    CustomerDto custUpdate = service.update(cust);
    assertNull(custUpdate);
  }

  @Test
  void testFind() {

    List<CustomerDto> cars = service.findAll();
    assertNotNull(cars);
    assertEquals(cars.size(), 1);
    Optional<CustomerDto> car = service.findById(1);
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

  private CustomerDto getCustomerCto() {
    return CustomerDto.builder()
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
