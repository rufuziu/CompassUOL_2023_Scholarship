package br.demattos.iury.msemployee.dto;

import org.hibernate.validator.constraints.br.CPF;

public class EmployeeDTO {
  public EmployeeDTO() {}
  private Long id;
  @CPF
  private String cpf;

  public EmployeeDTO(String cpf) {
    this.cpf = cpf;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
