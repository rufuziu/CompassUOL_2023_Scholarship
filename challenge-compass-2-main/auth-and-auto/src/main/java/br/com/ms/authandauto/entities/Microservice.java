package br.com.ms.authandauto.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name="microservices")
//@JsonIgnoreProperties("relationships")
public class Microservice {
  public Microservice(){}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 16, nullable = false)
  private String name;
  @OneToMany(mappedBy = "microservice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<UserToMicroservice> relationships;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<UserToMicroservice> getRelationships() {
    return relationships;
  }

  public void setRelationships(Set<UserToMicroservice> relationships) {
    this.relationships = relationships;
  }
}
