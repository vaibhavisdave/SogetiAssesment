package com.sogeti.asses.broker.sogetiassesmentbroker.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.sogeti.asses.broker.sogetiassesmentbroker.dao.BrokerRespository;
import com.sogeti.asses.broker.sogetiassesmentbroker.dto.CustomerDto;
import com.sogeti.asses.broker.sogetiassesmentbroker.entity.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



/**
 * service to perform CURD for customer.
 *
 * @author vighn
 *
 */
@Service
public class BrokerService {

  @Autowired
  private BrokerRespository demoRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private EurekaClient eurekaClient;

  /**
   * creates customer.
   *
   * @param customerDto CustomerDto
   * @return long id
   */
  public long create(CustomerDto customerDto) {

    Customer cutomer = new Customer(customerDto.getName(), customerDto.getStreet(), 
                                    customerDto.getHouseNo(), customerDto.getZipCode(), 
                                    customerDto.getPlace(), customerDto.getEmail(), 
                                    customerDto.getPhNumber());
    return demoRepository.save(cutomer).getId();
  }

  /**
   * updates customer.
   *
   * @param customer CustomerDto
   * @return CustomerDto
   */
  public CustomerDto update(CustomerDto customer) {
    Customer oldCust = null;
    Optional<Customer> oldCustOpt = demoRepository.findById(customer.getId());
    if (oldCustOpt.isPresent()) {
      oldCust = oldCustOpt.get();
      oldCust.setEmail(customer.getEmail());
      oldCust.setHouseNo(customer.getHouseNo());
      oldCust.setName(customer.getName());
      oldCust.setPhNumber(customer.getPhNumber());
      oldCust.setPlace(customer.getPlace());
      oldCust.setStreet(customer.getStreet());
      oldCust.setZipCode(customer.getZipCode());
      oldCust = demoRepository.save(oldCust);

      return CustomerDto.builder()
                        .email(customer.getEmail())
                        .houseNo(customer.getHouseNo())
                        .name(customer.getName())
                        .phNumber(customer.getPhNumber())
                        .place(customer.getPlace())
                        .street(customer.getStreet())
                        .zipCode(customer.getZipCode())
                        .id(customer.getId())
                        .build();

    }
    return null;
  }

  /**
   * fetches customer by id.
   *
   * @return List of CustomerDto
   */
  public List<CustomerDto> findAll() {
    return demoRepository.findAll().stream().map(m -> {
      return CustomerDto.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .street(m.getStreet())
                        .houseNo(m.getHouseNo())
                        .place(m.getPlace())
                        .phNumber(m.getPhNumber())
                        .zipCode(m.getZipCode())
                        .email(m.getEmail())
                        .build();
    }).toList();
  }

  /**
   * fetches customer by id.
   *
   * @param id long
   *
   * @return Optional of CustomerDto
   */
  public Optional<CustomerDto> findById(long id) {
    Optional<Customer> custOpt = demoRepository.findById(id);
    if (custOpt.isPresent()) {
      Customer cust = custOpt.get();
      return Optional.of(CustomerDto.builder()
                                    .id(cust.getId())
                                    .name(cust.getName())
                                    .street(cust.getStreet())
                                    .houseNo(cust.getHouseNo())
                                    .place(cust.getPlace())
                                    .phNumber(cust.getPhNumber())
                                    .zipCode(cust.getZipCode())
                                    .email(cust.getEmail())
                                    .build());
    }
    return Optional.empty();

  }

  public void deleteById(long id) {
    demoRepository.deleteById(id);

  }

  /**
   * Calls LEASECOMPANYSERVICE to fetch cars data.
   *
   * @return Map of Key: String, value: Double
   */
  @SuppressWarnings("unchecked")
  public Map<String, Double> getCars() {
    Map<String, Double> map = new HashMap<String, Double>();
    Application app = eurekaClient.getApplication("LEASECOMPANYSERVICE");
    InstanceInfo instance = app.getInstances().get(0);
    String url = "http://" + instance.getIPAddr() + ":" + instance.getPort() + "/cars";
    System.out.println(url);
    @SuppressWarnings("rawtypes")
    List cars = restTemplate.getForObject(url, List.class);
    cars.stream().forEach(c -> {
      HashMap<String, Object> car = (HashMap<String, Object>) c;
      map.put(car.get("model").toString(), ((double) car.get("grossPrice")));
    });
    return map;
  }

}
