package br.demattos.iury.msvoting.controller;

import br.demattos.iury.msvoting.dtos.ProposalNewDTO;
import br.demattos.iury.msvoting.dtos.ProposalResultDTO;
import br.demattos.iury.msvoting.dtos.VoteDTO;
import br.demattos.iury.msvoting.services.EmployeeResourceService;
import br.demattos.iury.msvoting.services.ProposalResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Validated
public class VotingController {
  private final EmployeeResourceService employeeResourceService;
  private final ProposalResourceService proposalResourceService;

  public VotingController(EmployeeResourceService employeeResourceService,
                          ProposalResourceService proposalResourceService) {
    this.employeeResourceService = employeeResourceService;
    this.proposalResourceService = proposalResourceService;
  }

  @GetMapping("v1/voting/{cpf}")
  public ResponseEntity<String> canEmployeeVote(@PathVariable(name = "cpf")
                                                @NotBlank(message = "CPF can't be blank")
                                                @CPF String cpf) {
    return ResponseEntity.ok(employeeResourceService.canEmployeeVoteString(cpf));
  }

  @GetMapping("v1/voting")
  public ResponseEntity<List<ProposalNewDTO>> getProposalPoll() {
    return proposalResourceService.getPoll();
  }

  @PostMapping("v1/voting")
  public ResponseEntity<Void> vote(@RequestBody @Valid
                                   VoteDTO voteDTO) {
    if (employeeResourceService.canEmployeeVoteBoolean(voteDTO.getEmployeeCpf())) {
      return proposalResourceService.vote(voteDTO);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("v1/voting/result/{id}")
  public ResponseEntity<ProposalResultDTO> getProposal
          (@PathVariable Long id) {
    return proposalResourceService.getProposal(id);
  }

  @GetMapping("v1/voting/result/draw")
  public ResponseEntity<List<ProposalResultDTO>> getDraw() {
    return proposalResourceService.getDraws();
  }

  @GetMapping("v1/voting/result/approved")
  public ResponseEntity<List<ProposalResultDTO>> getApproved() {
    return proposalResourceService.getApproved();
  }

  @GetMapping("v1/voting/result/rejected")
  public ResponseEntity<List<ProposalResultDTO>> getRejected() {
    return proposalResourceService.getRejected();
  }
}
