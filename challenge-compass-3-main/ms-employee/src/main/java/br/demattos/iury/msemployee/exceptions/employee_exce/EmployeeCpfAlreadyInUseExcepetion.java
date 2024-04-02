package br.demattos.iury.msemployee.exceptions.employee_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT)
public class EmployeeCpfAlreadyInUseExcepetion extends RuntimeException {
  public EmployeeCpfAlreadyInUseExcepetion(String message) {
    super(message);
  }
}
