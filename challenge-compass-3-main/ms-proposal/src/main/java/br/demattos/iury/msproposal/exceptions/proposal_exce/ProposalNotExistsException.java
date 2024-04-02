package br.demattos.iury.msproposal.exceptions.proposal_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProposalNotExistsException extends RuntimeException {
  public ProposalNotExistsException(String message) {
    super(message);
  }
}
