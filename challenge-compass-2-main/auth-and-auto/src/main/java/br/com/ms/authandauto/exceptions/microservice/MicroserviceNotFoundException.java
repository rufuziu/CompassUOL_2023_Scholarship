package br.com.ms.authandauto.exceptions.microservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class MicroserviceNotFoundException extends RuntimeException {
  public MicroserviceNotFoundException(String message) {
    super(message);
  }
}
