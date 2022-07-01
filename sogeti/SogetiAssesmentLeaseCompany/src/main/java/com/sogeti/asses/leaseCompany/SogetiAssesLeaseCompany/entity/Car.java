package com.sogeti.asses.leasecompany.sogetiassesleasecompany.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * model representing car.
 *
 * @author vighn
 *
 */
@Data
@Entity
@Table(name = "CAR")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ApiModelProperty(example = "model name", dataType = "String", 
      required = true, value = "Car's model name")
  @Column(name = "model", nullable = false)
  private String model;

  @ApiModelProperty(example = "make", dataType = "String", value = "Car's model name")
  @Column(name = "make")
  private String make;

  @ApiModelProperty(example = "1", dataType = "Integer", required = true, value = "Car's version")
  @Column(name = "version", nullable = false)
  private int version;

  @ApiModelProperty(example = "4", dataType = "Integer", value = "Nos. of doors car have.")
  @Column(name = "noOfDoors")
  private int noOfDoors;

  @ApiModelProperty(example = "CO2Emission", dataType = "String", value = "CO2 Emission by the Car")
  @Column(name = "co2Emission")
  private String co2Emission;

  @ApiModelProperty(example = "10000", dataType = "double",
                    required = true, value = "Car's gross price")
  @Column(name = "grossPrice", nullable = false)
  private double grossPrice;

  @ApiModelProperty(example = "1000", dataType = "double", 
      required = true, value = "Car's nett Price")
  @Column(name = "nettPrice", nullable = false)
  private double nettPrice;

  @ApiModelProperty(example = "1000", dataType = "double",
      required = true, value = "Car's mileage")
  @Column(name = "mileage", nullable = false)
  private double mileage;

  @ApiModelProperty(example = "12", dataType = "Integer",
                    required = true, value = "The number of months in the contract")
  @Column(name = "duration", nullable = false)
  private int duration;

  @ApiModelProperty(example = "12-06-2022", dataType = "String",
                    required = true, value = "Contract's Start Date")
  @Column(name = "startDate", nullable = false)
  private String startDate;

  @ApiModelProperty(example = "0.8", dataType = "double",
                    required = true, value = "Contract's interest rate")
  @Column(name = "interestRate", nullable = false)
  private double interestRate;

  @Setter(value = AccessLevel.NONE)
  @Getter(value = AccessLevel.NONE)
  @ApiModelProperty(example = "1000", dataType = "double", 
                    required = true, value = "Contract's lease rate")
  @Column(name = "leaseRate", nullable = false)
  private double leaseRate;

  public Car() {
    super();
  }

  /**
   * parameterized constructor.
   *
   * @param model String
   * @param make String
   * @param version int
   * @param noOfDoors int
   * @param co2Emission String
   * @param grossPrice double
   * @param nettPrice double
   * @param mileage double
   * @param duration int
   * @param startDate String
   * @param interestRate double
   */
  public Car(String model, String make, int version, int noOfDoors,
      String co2Emission, double grossPrice,
      double nettPrice, double mileage, int duration, String startDate, double interestRate) {
    super();
    this.model = model;
    this.make = make;
    this.version = version;
    this.noOfDoors = noOfDoors;
    this.co2Emission = co2Emission;
    this.grossPrice = grossPrice;
    this.nettPrice = nettPrice;
    this.mileage = mileage;
    this.duration = duration;
    this.startDate = startDate;
    this.interestRate = interestRate;
    this.leaseRate = calculateLeaseRate();
  }

  public double calculateLeaseRate() {

    return (((mileage / 12) * duration) / nettPrice) + (((interestRate / 100) * nettPrice) / 12);
  }

  public void setLeaseRate() {
    this.leaseRate = calculateLeaseRate();
  }

  public double getLeaseRate() {
    return leaseRate;
  }
}
