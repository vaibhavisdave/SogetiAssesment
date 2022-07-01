package com.sogeti.asses.broker.sogetiassesmentbroker.dao;

import com.sogeti.asses.broker.sogetiassesmentbroker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Customer.
 *
 * @author vighn
 *
 */
@Repository
public interface BrokerRespository extends JpaRepository<Customer, Long> {

}
