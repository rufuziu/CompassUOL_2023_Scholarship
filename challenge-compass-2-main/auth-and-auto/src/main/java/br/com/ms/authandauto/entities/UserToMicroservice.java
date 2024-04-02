package br.com.ms.authandauto.entities;

import br.com.ms.authandauto.enums.ERole;
import jakarta.persistence.*;

@Entity(name = "users_to_microservices")
public class UserToMicroservice {
  public UserToMicroservice() {}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;
  @ManyToOne
  @JoinColumn(name = "microserviceId")
  private Microservice microservice;
  private ERole userRole;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Microservice getMicroservice() {
    return microservice;
  }

  public void setMicroservice(Microservice microservice) {
    this.microservice = microservice;
  }

  public ERole getUserRole() {
    return userRole;
  }

  public void setUserRole(ERole userRole) {
    this.userRole = userRole;
  }

  public UserToMicroservice(User user,
                            Microservice microservice,
                            ERole userRole) {
    this.user = user;
    this.microservice = microservice;
    this.userRole = userRole;
  }

}
