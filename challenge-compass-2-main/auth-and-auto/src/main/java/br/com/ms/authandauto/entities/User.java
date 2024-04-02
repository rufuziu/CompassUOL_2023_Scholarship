package br.com.ms.authandauto.entities;


import jakarta.persistence.*;

import java.util.Set;
//@JsonIgnoreProperties("relationships")
@Entity(name="users")
public class User {
  public User() {}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 64, nullable = false)
  private String name;
  @Column(unique = true, length = 255, nullable = false)
  private String email;
  @Column(length = 32, nullable = false)
  private String password;
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<UserToMicroservice> relationships;

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<UserToMicroservice> getRelationships() {
    return relationships;
  }

  public void setRelationships(Set<UserToMicroservice> relationships) {
    this.relationships = relationships;
  }

}
