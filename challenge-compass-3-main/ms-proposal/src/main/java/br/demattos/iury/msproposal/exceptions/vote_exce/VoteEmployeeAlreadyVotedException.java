package br.demattos.iury.msproposal.exceptions.vote_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class VoteEmployeeAlreadyVotedException extends RuntimeException{
  public VoteEmployeeAlreadyVotedException(String message) {
    super(message);
  }
}
