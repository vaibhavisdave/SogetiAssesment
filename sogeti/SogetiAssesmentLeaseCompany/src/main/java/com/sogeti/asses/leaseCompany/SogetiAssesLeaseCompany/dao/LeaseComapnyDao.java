package com.sogeti.asses.leasecompany.sogetiassesleasecompany.dao;

import com.sogeti.asses.leasecompany.sogetiassesleasecompany.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Daa repository for Car.
 *
 * @author vighn
 *
 */
@Repository
public interface LeaseComapnyDao extends JpaRepository<Car, Long> {

}
