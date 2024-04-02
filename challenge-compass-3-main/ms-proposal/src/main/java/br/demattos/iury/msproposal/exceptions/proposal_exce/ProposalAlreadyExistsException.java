package br.demattos.iury.msproposal.exceptions.proposal_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ProposalAlreadyExistsException extends RuntimeException {
  public ProposalAlreadyExistsException(String message) {
    super(message);
  }
}
