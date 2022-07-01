package com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *Global exception handler for lease company.
 *
 * @author vighn
 */
@ControllerAdvice
public class LeaseCompanyExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> exception(Exception exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
  }

}
