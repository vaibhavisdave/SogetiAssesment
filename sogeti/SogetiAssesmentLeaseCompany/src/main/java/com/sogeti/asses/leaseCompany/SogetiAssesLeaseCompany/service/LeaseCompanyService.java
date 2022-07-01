package com.sogeti.asses.leasecompany.sogetiassesleasecompany.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.sogeti.asses.leasecompany.sogetiassesleasecompany.dao.LeaseComapnyDao;
import com.sogeti.asses.leasecompany.sogetiassesleasecompany.dto.CarDto;
import com.sogeti.asses.leasecompany.sogetiassesleasecompany.entity.Car;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * service to perform CURD operation on cars.
 *
 * @author vighn
 *
 */
@Service
public class LeaseCompanyService {

  @Autowired
  LeaseComapnyDao comapnyDao;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private EurekaClient eurekaClient;

  /**
   * creates car.
   *
   * @param carDtO
   * 
   * @return long id
   */
  public long create(CarDto carDtO) {
    Car car = new Car(carDtO.getModel(), carDtO.getMake(), 
        carDtO.getVersion(), carDtO.getNoOfDoors(),
        carDtO.getCo2Emission(), carDtO.getGrossPrice(), carDtO.getNettPrice(), carDtO.getMileage(),
        carDtO.getDuration(), carDtO.getStartDate(), carDtO.getInterestRate());
    car.setLeaseRate();
    return comapnyDao.save(car).getId();
  }

  /**
   * updates car.
   *
   * @param car CarDto
   * @return CarDto
   */
  public CarDto update(CarDto car) {
    Car oldCar = null;
    Optional<Car> oldCustOpt = comapnyDao.findById(car.getId());
    if (oldCustOpt.isPresent()) {
      oldCar = oldCustOpt.get();
      oldCar.setCo2Emission(car.getCo2Emission());
      oldCar.setGrossPrice(car.getGrossPrice());
      oldCar.setMake(car.getMake());
      oldCar.setMileage(car.getMileage());
      oldCar.setModel(car.getModel());
      oldCar.setNoOfDoors(car.getNoOfDoors());
      oldCar.setVersion(car.getVersion());
      oldCar.setDuration(car.getDuration());
      oldCar.setInterestRate(car.getInterestRate());
      oldCar.setNettPrice(car.getNettPrice());
      oldCar.setLeaseRate();
      oldCar.setStartDate(car.getStartDate());
      oldCar = comapnyDao.save(oldCar);

      return CarDto.builder().co2Emission(oldCar.getCo2Emission()).duration(oldCar.getDuration())
          .grossPrice(oldCar.getGrossPrice())
          .id(oldCar.getId())
          .interestRate(oldCar
          .getInterestRate())
          .leaseRate(oldCar.getLeaseRate())
          .make(oldCar.getMake())
          .mileage(oldCar.getMileage())
          .model(oldCar.getModel())
          .nettPrice(oldCar.getNettPrice())
          .noOfDoors(oldCar.getNoOfDoors())
          .startDate(oldCar.getStartDate())
          .version(oldCar.getVersion()).build();
    }
    return null;
  }

  /**
   * fetches cars.
   *
   * @return List of CarDto
   */
  public List<CarDto> findAll() {
    return comapnyDao.findAll().stream().map(m -> {
      return CarDto.builder()
          .co2Emission(m.getCo2Emission())
          .duration(m.getDuration())
          .grossPrice(m.getGrossPrice())
          .id(m.getId())
          .interestRate(m.getInterestRate())
          .leaseRate(m.getLeaseRate())
          .make(m.getMake())
          .mileage(m.getMileage())
          .model(m.getModel())
          .nettPrice(m.getNettPrice())
          .noOfDoors(m.getNoOfDoors())
          .startDate(m.getStartDate())
          .version(m.getVersion())
          .build();
    }).toList();
  }

  /**
   * fetches car by Id.
   *
   * @param id long
   *
   * @return Optional of CarDto
   */
  public Optional<CarDto> findById(long id) {
    Optional<Car> carOpt = comapnyDao.findById(id);
    if (carOpt.isPresent()) {
      Car car = carOpt.get();
      CarDto carDto = CarDto.builder()
          .co2Emission(car.getCo2Emission())
          .duration(car.getDuration())
          .grossPrice(car.getGrossPrice())
          .id(car.getId())
          .interestRate(car.getInterestRate())
          .leaseRate(car.getLeaseRate())
          .make(car.getMake()).mileage(car.getMileage())
          .model(car.getModel())
          .nettPrice(car.getNettPrice())
          .noOfDoors(car.getNoOfDoors())
          .startDate(car.getStartDate())
          .version(car.getVersion())
          .build();
      return Optional.of(carDto);
    }
    return Optional.empty();

  }

  public void deleteById(long id) {
    comapnyDao.deleteById(id);
  }

  /**
   * Fetches customers from BROKERSERVICE.
   *
   * @return Map of  key: String value: String
   */
  @SuppressWarnings("unchecked")
  public Map<String, String> getCustomers() {
    Map<String, String> map = new HashMap<String, String>();
    Application app = eurekaClient.getApplication("BROKERSERVICE");
    InstanceInfo instance = app.getInstances().get(0);
    String url = "http://" + instance.getIPAddr() + ":" + instance.getPort() + "/customers";
    System.out.println(url);
    @SuppressWarnings("rawtypes")
    List cars = restTemplate.getForObject(url, List.class);
    cars.stream().forEach(c -> {
      HashMap<String, Object> cust = (HashMap<String, Object>) c;
      map.put(cust.get("name").toString(), (cust.get("email")).toString());
    });
    return map;
  }
}
