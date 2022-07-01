package com.sogeti.asses.leasecompany.sogetiassesmentleasecompany.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogeti.asses.leasecompany.sogetiassesleasecompany.dto.CarDto;
import com.sogeti.asses.leasecompany.sogetiassesleasecompany.service.LeaseCompanyService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



/**
 * class to test  LeaseCompanyController.
 *
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.leasecompany.sogetiassesleasecompany
                           .SogetiAssesmentLeaseCompanyApplication.class})
@AutoConfigureMockMvc
public class LeaseCompanyControllerTest {
  @MockBean
  LeaseCompanyService service;

  @Autowired
  MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testCreate() throws JsonProcessingException, Exception {
    CarDto car = getCarDto();
    when(service.create(car)).thenReturn(1L);
    mvc.perform(post("/cars")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(car)))
        .andExpect(result -> {
          assertEquals(result.getResponse().getStatus(), 200);
        });
  }

  @Test
  void testUpdate() throws JsonProcessingException, Exception {
    CarDto car = getCarDto();
    car.setId(1L);
    when(service.update(car)).thenReturn(car);
    mvc.perform(put("/cars")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(car)))
        .andExpect(result -> {
          assertEquals(result.getResponse().getStatus(), 200);
        });

    when(service.update(car)).thenThrow(new DataIntegrityViolationException("junit Test"));
    mvc.perform(put("/cars")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(car)))
        .andExpect(result -> {
          assertEquals(result.getResponse().getStatus(), 409);
          assertEquals(result.getResolvedException().getClass(),
              DataIntegrityViolationException.class);
          assertEquals(result.getResolvedException().getMessage(), "junit Test");
        });
  }

  @Test
  void testFind() throws JsonProcessingException, Exception {
    CarDto car = getCarDto();
    car.setId(1L);
    when(service.findAll()).thenReturn(List.of(car));
    mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(result -> {
      assertEquals(result.getResponse().getStatus(), 200);
    });
    when(service.findById(1L)).thenReturn(Optional.of(car));
    mvc.perform(get("/cars/1").contentType(MediaType.APPLICATION_JSON)).andExpect(result -> {
      assertEquals(result.getResponse().getStatus(), 200);
    });
  }

  @Test
  void testDelete() throws JsonProcessingException, Exception {
    doNothing().when(service).deleteById(1);
    mvc.perform(delete("/cars/1").contentType(MediaType.APPLICATION_JSON)).andExpect(result -> {
      assertEquals(result.getResponse().getStatus(), 200);
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
