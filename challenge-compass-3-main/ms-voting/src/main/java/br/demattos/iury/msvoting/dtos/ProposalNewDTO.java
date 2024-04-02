package br.demattos.iury.msvoting.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class ProposalNewDTO {
  public ProposalNewDTO() {
  }
  private Long id;
  @NotEmpty
  private String description;
  private LocalDateTime closeTime;

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

  public LocalDateTime getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(LocalDateTime closeTime) {
    this.closeTime = closeTime;
  }
}
