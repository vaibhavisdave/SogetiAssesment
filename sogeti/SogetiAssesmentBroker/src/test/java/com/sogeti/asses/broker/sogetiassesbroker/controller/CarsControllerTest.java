package com.sogeti.asses.broker.sogetiassesbroker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sogeti.asses.broker.sogetiassesmentbroker.service.BrokerService;

/**
 * Class to test CarsController.
 *
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.sogeti.asses.broker.sogetiassesmentbroker
                           .SogetiAssesmentBrokerApplication.class})
@AutoConfigureMockMvc
public class CarsControllerTest {
  @MockBean
  BrokerService service;

  @Autowired
  MockMvc mvc;

  @Test
  void testFind() throws JsonProcessingException, Exception {
    Map<String, Double> map = new HashMap<>();
    map.put("name", 1000d);
    map.put("name1", 10d);
    when(service.getCars()).thenReturn(map);
    mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(result -> {
      assertEquals(result.getResponse().getStatus(), 200);
    });
  }
}
