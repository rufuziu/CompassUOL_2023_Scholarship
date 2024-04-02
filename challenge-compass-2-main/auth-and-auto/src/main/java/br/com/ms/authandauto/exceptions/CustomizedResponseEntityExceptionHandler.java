package br.com.ms.authandauto.exceptions;

import br.com.ms.authandauto.exceptions.errors.ErrorDetails;
import br.com.ms.authandauto.exceptions.microservice.MicroserviceNotFoundException;
import br.com.ms.authandauto.exceptions.user.UserAlreadyBoundedWithMicroserviceException;
import br.com.ms.authandauto.exceptions.user.UserEmailAlreadyInUseException;
import br.com.ms.authandauto.exceptions.user.UserNotFoundException;
import br.com.ms.authandauto.exceptions.userToMicroservice.UserToMicroserviceNotFoundException;
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
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDetails> handleAllException(Exception ex,
                                                               WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserEmailAlreadyInUseException.class)
  public final ResponseEntity<ErrorDetails> handleUserEmailAlreadyUsedException(Exception ex,
                                                                                WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex,
                                                                        WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserAlreadyBoundedWithMicroserviceException.class)
  public final ResponseEntity<ErrorDetails> handleUserAlreadyBoundedWithMicroserviceException(Exception ex,
                                                                                              WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MicroserviceNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleMicroserviceNotFoundException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(UserToMicroserviceNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleUserToMicroserviceNotFoundException
          (Exception ex, WebRequest request) {
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