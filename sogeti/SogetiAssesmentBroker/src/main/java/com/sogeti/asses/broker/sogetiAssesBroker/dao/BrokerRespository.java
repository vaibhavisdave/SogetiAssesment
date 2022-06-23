/**
 * 
 */
package com.sogeti.asses.broker.sogetiAssesBroker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sogeti.asses.broker.sogetiAssesBroker.entity.Customer;

/**
 * @author vighn
 *
 */
@Repository
public interface BrokerRespository extends JpaRepository<Customer, Long> {

}
