package br.demattos.iury.msproposal.dtos;

import br.demattos.iury.msproposal.enums.EResult;

import java.time.LocalDateTime;
import java.util.List;

public class ProposalDTO {
  public ProposalDTO() {
  }

  private Long id;
  private String description;
  private Long approve = 0L;
  private Long reject = 0L;
  private LocalDateTime initTime;
  private LocalDateTime closeTime;
  private EResult result;
  private List<VoteDTO> votes;

  public ProposalDTO(String description,
                     Long approve,
                     Long reject,
                     LocalDateTime initTime,
                     LocalDateTime closeTime,
                     EResult result) {
    this.description = description;
    this.approve = approve;
    this.reject = reject;
    this.initTime = initTime;
    this.closeTime = closeTime;
    this.result = result;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getApprove() {
    return approve;
  }

  public void setApprove(Long approve) {
    this.approve += approve;
  }

  public Long getReject() {
    return reject;
  }

  public void setReject(Long reject) {
    this.reject += reject;
  }

  public LocalDateTime getInitTime() {
    return initTime;
  }

  public void setInitTime(LocalDateTime initTime) {
    this.initTime = initTime;
  }

  public LocalDateTime getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(LocalDateTime closeTime) {
    this.closeTime = closeTime;
  }

  public EResult getResult() {
    return result;
  }

  public void setResult(EResult result) {
    this.result = result;
  }
  public List<VoteDTO> getVotes() {
    return votes;
  }

  public void setVotes(List<VoteDTO> votes) {
    this.votes = votes;
  }
}
