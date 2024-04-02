package br.demattos.iury.msemployee.exceptions.employee_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class EmployeeInvalidCpf extends RuntimeException {
  public EmployeeInvalidCpf(String message) {
    super(message);
  }
}
