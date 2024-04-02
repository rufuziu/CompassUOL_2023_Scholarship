package br.demattos.iury.msproposal.controller;

import br.demattos.iury.msproposal.dtos.ProposalNewDTO;
import br.demattos.iury.msproposal.dtos.ProposalResultDTO;
import br.demattos.iury.msproposal.dtos.VoteDTO;
import br.demattos.iury.msproposal.exceptions.proposal_exce.ProposalNotAbleToVoteException;
import br.demattos.iury.msproposal.exceptions.proposal_exce.ProposalNotExistsException;
import br.demattos.iury.msproposal.services.ProposalService;
import br.demattos.iury.msproposal.services.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/")
public class ProposalResource {
  private final ProposalService proposalService;
  private final VoteService voteService;

  public ProposalResource(ProposalService proposalService,
                          VoteService voteService) {
    this.proposalService = proposalService;
    this.voteService = voteService;
  }

  @PostMapping("v1/proposals")
  public ResponseEntity<ProposalNewDTO> create(
          @RequestBody @Valid ProposalNewDTO proposalNewDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(proposalService.createProposal(proposalNewDTO));
  }

  @GetMapping("v1/proposals")
  public ResponseEntity<List<ProposalNewDTO>> getPoll() {
    return ResponseEntity.ok(proposalService.getPollingProposals());
  }

  @GetMapping("v1/proposals/{id}")
  public ResponseEntity<ProposalResultDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(proposalService.getById(id));
  }

  @GetMapping("v1/proposals/draw")
  public ResponseEntity<List<ProposalResultDTO>> getDraw() {
    return ResponseEntity.ok(proposalService.getAllDrawResult());
  }

  @GetMapping("v1/proposals/approved")
  public ResponseEntity<List<ProposalResultDTO>> getApproved() {
    return ResponseEntity.ok(proposalService.getAllApprovedResult());
  }

  @GetMapping("v1/proposals/rejected")
  public ResponseEntity<List<ProposalResultDTO>> getRejected() {
    return ResponseEntity.ok(proposalService.getAllRejectedResult());
  }

  @PostMapping("v1/votes")
  public ResponseEntity<Void> vote(@RequestBody @Valid
                                   VoteDTO voteDTO) {
    if (!proposalService.findProposalById(voteDTO.getProposalId())) {
      throw new ProposalNotExistsException("Proposal not exists.");
    } else if (proposalService.checkProposalIsAbleToVote(voteDTO.getProposalId())) {
      voteService.create(voteDTO);
      return ResponseEntity.ok().build();
    } else {
      throw new
              ProposalNotAbleToVoteException("Proposal not able to vote.");
    }
  }
}