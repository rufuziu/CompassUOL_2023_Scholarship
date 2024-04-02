package br.demattos.iury.msproposal.exceptions.proposal_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProposalNotAbleToVoteException extends RuntimeException{
  public ProposalNotAbleToVoteException(String message) {
    super(message);
  }
}
