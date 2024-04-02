package br.com.ms.authandauto.dtos.userToMicroservice;

import br.com.ms.authandauto.entities.Microservice;
import br.com.ms.authandauto.entities.User;
import br.com.ms.authandauto.enums.ERole;

public class UserToMicroserviceDTO {
  public UserToMicroserviceDTO() {
  }

  private Long id;
  private User user;
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

  public UserToMicroserviceDTO(Long id, User user, Microservice microservice, ERole userRole) {
    this.id = id;
    this.user = user;
    this.microservice = microservice;
    this.userRole = userRole;
  }
}
