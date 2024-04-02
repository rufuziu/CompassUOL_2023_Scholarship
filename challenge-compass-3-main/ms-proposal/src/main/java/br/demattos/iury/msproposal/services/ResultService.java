package br.demattos.iury.msproposal.services;

import br.demattos.iury.msproposal.dtos.ProposalDTO;
import br.demattos.iury.msproposal.enums.EResult;
import br.demattos.iury.msproposal.mqueue.ProposalResultPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
  private final ProposalService proposalService;
  private final ProposalResultPublisher proposalResultPublisher;

  public ResultService(ProposalService proposalService,
                       ProposalResultPublisher proposalResultPublisher) {
    this.proposalService = proposalService;
    this.proposalResultPublisher = proposalResultPublisher;
  }

  @Scheduled(fixedRateString = "${intervalTime}")
  public void closeEndedProposals() {
    if (proposalService.hasAnyProposalEnded()) {
      generateResult();
    }
  }

  public void generateResult() {
    List<ProposalDTO> list = proposalService.getAllEndedProposals();
    for (ProposalDTO p : list) {
      p.getVotes().stream().forEach(votes -> {
        if (votes.getVote()) p.setApprove(1L);
        else p.setReject(1L);
      });
    }
    for (ProposalDTO p : list) {
      if (p.getApprove() > p.getReject())
        p.setResult(EResult.APPROVED);
      else if (p.getReject() > p.getApprove())
        p.setResult(EResult.REJECTED);
      else p.setResult(EResult.DRAW);
    }
    for (ProposalDTO p : list) {
      proposalService.updateProposal(p);
      proposalResultPublisher.SendProposalResult(p);
    }
  }
}
