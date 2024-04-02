package br.com.ms.authandauto.exceptions.userToMicroservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class UserToMicroserviceNotFoundException extends RuntimeException {
  public UserToMicroserviceNotFoundException(String message) {
    super(message);
  }
}
