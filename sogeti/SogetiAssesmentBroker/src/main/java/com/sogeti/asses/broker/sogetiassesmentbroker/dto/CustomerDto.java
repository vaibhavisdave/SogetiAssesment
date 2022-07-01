package com.sogeti.asses.broker.sogetiassesmentbroker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Data transfer object for Customer.
 *
 * @author vighn
 *
 */
@Data
@Builder
public class CustomerDto {

  @ApiModelProperty(example = "1", dataType = "Long", required = true, value = "Customer Id")
  private Long id;

  @ApiModelProperty(example = "Customer name", dataType = "String", 
                    required = true, value = "Customer name")
  private String name;

  @ApiModelProperty(example = "street", dataType = "String", 
                    value = "Street name for customer address")
  private String street;

  @ApiModelProperty(example = "1", dataType = "Integer", value = "House no.of customer address")
  private int houseNo;

  @ApiModelProperty(example = "1111", dataType = "String", value = "Zip code of customer address")
  private String zipCode;

  @ApiModelProperty(example = "Utrecht", dataType = "String", value = "Place of customer address")
  private String place;

  @ApiModelProperty(example = "a@b.com", dataType = "String", 
                    required = true, value = "Customer email address")
  private String email;

  @ApiModelProperty(example = "123456", dataType = "String", 
                    required = true, value = "Phone no. of customer name")
  private String phNumber;

}
