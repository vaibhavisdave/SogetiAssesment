/**
 * 
 */
package com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.controller.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.entity.Car;

/**
 * @author vighn
 *
 */
@Repository
public interface LeaseComapnyDao extends JpaRepository<Car, Long>{

}
