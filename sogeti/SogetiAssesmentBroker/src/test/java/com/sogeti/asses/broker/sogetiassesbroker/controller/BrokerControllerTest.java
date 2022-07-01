package com.sogeti.asses.broker.sogetiassesbroker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogeti.asses.broker.sogetiassesmentbroker.dto.CustomerDto;
import com.sogeti.asses.broker.sogetiassesmentbroker.service.BrokerService;
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
 * class to test BrokerController.
 *
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.broker.sogetiassesmentbroker
                           .SogetiAssesmentBrokerApplication.class})
@AutoConfigureMockMvc
public class BrokerControllerTest {
  @MockBean
  BrokerService service;

  @Autowired
  MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testCreate() throws JsonProcessingException, Exception {
    CustomerDto cust = getCustomerCto();
    when(service.create(cust)).thenReturn(1L);
    mvc.perform(
        post("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cust)))
        .andExpect(result -> {
          assertEquals(result.getResponse().getStatus(), 200);
        });
  }

  @Test
  void testUpdate() throws JsonProcessingException, Exception {
    CustomerDto cust = getCustomerCto();
    cust.setId(1L);
    when(service.update(cust)).thenReturn(cust);
    mvc.perform(
        put("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cust)))
        .andExpect(result -> {
          assertEquals(result.getResponse().getStatus(), 200);
        });

    when(service.update(cust)).thenThrow(new DataIntegrityViolationException("junit Test"));
    mvc.perform(
        put("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cust)))
        .andExpect(result -> {
          assertEquals(result.getResponse().getStatus(), 409);
          assertEquals(result.getResolvedException().getClass(),
                        DataIntegrityViolationException.class);
          assertEquals(result.getResolvedException().getMessage(), "junit Test");
        });
  }

  @Test
  void testFind() throws JsonProcessingException, Exception {
    CustomerDto cust = getCustomerCto();
    cust.setId(1L);
    when(service.findAll()).thenReturn(List.of(cust));
    mvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON)).andExpect(result -> {
      assertEquals(result.getResponse().getStatus(), 200);
    });
    when(service.findById(1L)).thenReturn(Optional.of(cust));
    mvc.perform(get("/customers/1").contentType(MediaType.APPLICATION_JSON)).andExpect(result -> {
      assertEquals(result.getResponse().getStatus(), 200);
    });
  }

  @Test
  void testDelete() throws JsonProcessingException, Exception {
    doNothing().when(service).deleteById(1);
    mvc.perform(delete("/customers/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(result -> {
          assertEquals(result.getResponse().getStatus(), 200);
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
