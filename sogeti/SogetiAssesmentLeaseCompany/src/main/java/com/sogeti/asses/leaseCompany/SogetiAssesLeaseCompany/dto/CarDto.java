package com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Builder;
import lombok.Data;

/**
 * data transfer object for cars.
 *
 * @author vighn
 *
 */
@Data
@Builder
public class CarDto {

  @ApiModelProperty(example = "1", dataType = "Long", 
                    value = "Car's id", accessMode = AccessMode.READ_ONLY)
  private Long id;

  @ApiModelProperty(example = "model name", dataType = "String", 
                    required = true, value = "Car's model name")
  private String model;

  @ApiModelProperty(example = "make", dataType = "String", value = "Car's model name")
  private String make;

  @ApiModelProperty(example = "1", dataType = "Integer", required = true, value = "Car's version")
  private int version;

  @ApiModelProperty(example = "4", dataType = "Integer", value = "Nos. of doors car have.")
  private int noOfDoors;

  @ApiModelProperty(example = "CO2Emission", dataType = "String", value = "CO2 Emission by the Car")
  private String co2Emission;

  @ApiModelProperty(example = "10000", dataType = "double",
                    required = true, value = "Car's gross price")
  private double grossPrice;

  @ApiModelProperty(example = "1000", dataType = "double", 
                    required = true, value = "Car's nett Price")
  private double nettPrice;

  @ApiModelProperty(example = "1000", dataType = "double", 
                    required = true, value = "Car's mileage")
  private double mileage;

  @ApiModelProperty(example = "12", dataType = "Integer", 
                    required = true, value = "The number of months in the contract")
  private int duration;

  @ApiModelProperty(example = "12-06-2022", dataType = "String", 
                    required = true, value = "Contract's Start Date")
  private String startDate;

  @ApiModelProperty(example = "0.8", dataType = "double",
                    required = true, value = "Contract's interest rate")
  private double interestRate;

  @ApiModelProperty(example = "1000", dataType = "double", 
                    required = true, value = "Contract's lease rate")
  private double leaseRate;

}
