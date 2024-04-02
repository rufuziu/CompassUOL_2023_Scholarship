package br.demattos.iury.msproposal.entities;

import jakarta.persistence.*;

@Entity(name ="votes")
public class Vote {
  public Vote() {  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "proposal_id")
  private Proposal proposal;
  private String employeeCpf;
  private Boolean vote;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Proposal getProposal() {
    return proposal;
  }

  public void setProposal(Proposal proposal) {
    this.proposal = proposal;
  }

  public String getEmployeeCpf() {
    return employeeCpf;
  }

  public void setEmployeeCpf(String employeeCpf) {
    this.employeeCpf = employeeCpf;
  }

  public Boolean getVote() {
    return vote;
  }

  public void setVote(Boolean vote) {
    this.vote = vote;
  }
}
