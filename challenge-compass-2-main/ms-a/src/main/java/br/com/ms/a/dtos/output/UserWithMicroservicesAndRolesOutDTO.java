package br.com.ms.a.dtos.output;

import java.util.ArrayList;
import java.util.List;

public class UserWithMicroservicesAndRolesOutDTO {
  public UserWithMicroservicesAndRolesOutDTO() {
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
}
