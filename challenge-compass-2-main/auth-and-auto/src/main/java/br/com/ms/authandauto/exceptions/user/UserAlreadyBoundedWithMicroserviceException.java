package br.com.ms.authandauto.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class UserAlreadyBoundedWithMicroserviceException extends RuntimeException {
  public UserAlreadyBoundedWithMicroserviceException(String message) {
    super(message);
  }
}
