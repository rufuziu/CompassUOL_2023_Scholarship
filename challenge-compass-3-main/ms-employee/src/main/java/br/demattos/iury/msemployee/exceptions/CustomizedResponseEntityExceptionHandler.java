package br.demattos.iury.msemployee.exceptions;

import br.demattos.iury.msemployee.exceptions.employee_exce.EmployeeCpfAlreadyInUseExcepetion;
import br.demattos.iury.msemployee.exceptions.employee_exce.EmployeeCpfNotFound;
import br.demattos.iury.msemployee.exceptions.error.ErrorDetails;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends
        ResponseEntityExceptionHandler {
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDetails> handleAllException(
          Exception ex,
          WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<ErrorDetails> handleConstraintViolationException(
          Exception ex,
          WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            "Invalid CPF.",
            request.getDescription(false)
    );

    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmployeeCpfAlreadyInUseExcepetion.class)
  public final ResponseEntity<ErrorDetails> handleEmployeeCpfAlreadyUsedException(
          Exception ex,
          WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
  }
  @ExceptionHandler(EmployeeCpfNotFound.class)
  public final ResponseEntity<ErrorDetails> handleEmployeeCpfNotFoundException(
          Exception ex,
          WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
  }
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatusCode status,
          WebRequest request) {
    String errors = "";
    for (int i = 0; i < ex.getErrorCount(); i++) {
      errors = errors.concat(ex.getFieldErrors().get(i).getDefaultMessage().concat(", "));
    }
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            errors,
            request.getDescription(false)
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
