package com.sogeti.asses.broker.sogetiassesmentbroker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * global customer exception Handler.
 *
 * @author vighn
 */
@ControllerAdvice
public class CustomerExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> exception(Exception exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
  }

}
