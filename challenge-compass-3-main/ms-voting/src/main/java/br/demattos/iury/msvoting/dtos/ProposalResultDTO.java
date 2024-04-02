package br.demattos.iury.msvoting.dtos;

import br.demattos.iury.msvoting.enums.EResult;

import java.time.LocalDateTime;

public class ProposalResultDTO {
  public ProposalResultDTO() {
  }

  private Long id;
  private String description;
  private Long approve = 0L;
  private Long reject = 0L;
  private LocalDateTime initTime;
  private LocalDateTime closeTime;
  private EResult result;

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
    this.approve = approve;
  }

  public Long getReject() {
    return reject;
  }

  public void setReject(Long reject) {
    this.reject = reject;
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
}
