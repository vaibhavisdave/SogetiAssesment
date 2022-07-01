package com.sogeti.asses.broker.sogetiassesmentbroker.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity for customer.
 *
 * @author vighn
 *
 */
@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ApiModelProperty(example = "Customer name", dataType = "String",
                    required = true, value = "Customer name")
  @Column(name = "name", nullable = false)
  private String name;

  @ApiModelProperty(example = "street", dataType = "String",
                    value = "Street name for customer address")
  @Column(name = "street")
  private String street;

  @ApiModelProperty(example = "1", dataType = "Integer", value = "House no.of customer address")
  @Column(name = "houseNo")
  private int houseNo;

  @ApiModelProperty(example = "1111", dataType = "String", value = "Zip code of customer address")
  @Column(name = "zipCode")
  private String zipCode;

  @ApiModelProperty(example = "Utrecht", dataType = "String", value = "Place of customer address")
  @Column(name = "place")
  private String place;

  @ApiModelProperty(example = "a@b.com", dataType = "String",
                    required = true, value = "Customer email address")
  @Column(name = "email", nullable = false)
  private String email;

  @ApiModelProperty(example = "123456", dataType = "String", 
                    required = true, value = "Phone no. of customer name")
  @Column(name = "phNumber", nullable = false)
  private String phNumber;

  /**
   * Parameterize constructor. 
   *
   * @param name String
   * @param street String
   * @param houseNumber int
   * @param zipCode String
   * @param place String
   * @param email String
   * @param phNumber String
   */
  public Customer(String name, String street, int houseNumber, 
                  String zipCode, String place, String email,
                  String phNumber) {
    super();
    this.name = name;
    this.street = street;
    this.houseNo = houseNumber;
    this.zipCode = zipCode;
    this.place = place;
    this.email = email;
    this.phNumber = phNumber;
  }

  public Customer() {
    super();
  }

}
