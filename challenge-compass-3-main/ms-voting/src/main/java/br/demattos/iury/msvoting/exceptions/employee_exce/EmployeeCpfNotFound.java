package br.demattos.iury.msvoting.exceptions.employee_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class EmployeeCpfNotFound extends RuntimeException {
  public EmployeeCpfNotFound(String message) {
    super(message);
  }
}
