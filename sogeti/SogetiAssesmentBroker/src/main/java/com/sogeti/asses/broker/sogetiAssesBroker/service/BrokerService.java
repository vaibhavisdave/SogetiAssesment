/**
 * 
 */
package com.sogeti.asses.broker.sogetiAssesBroker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Functions;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.sogeti.asses.broker.sogetiAssesBroker.dao.BrokerRespository;
import com.sogeti.asses.broker.sogetiAssesBroker.entity.Customer;

/**
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

	public long create(Customer customer) {
		return demoRepository.save(customer).getId();
	}

	public Customer update(Customer customer, @PathVariable long id) {
		Customer oldCust = null;
		Optional<Customer> oldCustOpt = demoRepository.findById(id);
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
		}
		return oldCust;
	}

	public List<Customer> findAll() {
		return demoRepository.findAll();
	}

	public Optional<Customer> findById(long id) {
		// TODO Auto-generated method stub
		return demoRepository.findById(id);
	}

	public void deleteById(long id) {
		demoRepository.deleteById(id);

	}

	@SuppressWarnings("unchecked")
	public Map<String, Double> getCars() {
		Map<String, Double> map = new HashMap<String, Double>();
		Application app = eurekaClient.getApplication("LEASECOMPANYSERVICE");
		InstanceInfo instance = app.getInstances().get(0);
		String url = "http://" + instance.getIPAddr() + ":" + instance.getPort() + "/find";
		System.out.println(url);
		List cars = restTemplate.getForObject(url, List.class);
		cars.stream().forEach(c ->{
			HashMap<String,Object> car = (HashMap<String,Object>)c;
			map.put(car.get("model").toString(),((double)car.get("grossPrice")));
		});
		return map;
	}

}