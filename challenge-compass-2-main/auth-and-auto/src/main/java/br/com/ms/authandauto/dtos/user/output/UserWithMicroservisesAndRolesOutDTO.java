package br.com.ms.authandauto.dtos.user.output;

import br.com.ms.authandauto.dtos.microservice.output.MicroserviceAndUserRoleOutDTO;
import br.com.ms.authandauto.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserWithMicroservisesAndRolesOutDTO {
  public UserWithMicroservisesAndRolesOutDTO() {
    microservices = new ArrayList<>();
  }
  private String name;
  private String email;
  private List<MicroserviceAndUserRoleOutDTO> microservices;
  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }
  public List<MicroserviceAndUserRoleOutDTO> getMicroservices() {
    return microservices;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setMicroservices(List<MicroserviceAndUserRoleOutDTO> microservices) {
    this.microservices = microservices;
  }

  public UserWithMicroservisesAndRolesOutDTO(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.microservices = new ArrayList<>();
  }
}
