package br.demattos.iury.msemployee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity(name ="employees")
public class Employee {
  public Employee() {
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

 @Column(unique = true)
 @Size(min = 11,max =11)
  private String cpf;

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
