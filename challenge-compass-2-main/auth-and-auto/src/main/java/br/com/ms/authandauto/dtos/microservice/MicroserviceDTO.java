package br.com.ms.authandauto.dtos.microservice;

import br.com.ms.authandauto.dtos.user.output.UserWithRoleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MicroserviceDTO {
  public MicroserviceDTO() {
    users = new ArrayList<>();
  }
  @JsonProperty("microserviceId")
  private Long id;
  @JsonProperty("microserviceName")
  private String name;
  private List<UserWithRoleDTO> users;

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

  public MicroserviceDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public List<UserWithRoleDTO> getUsers() {
    return users;
  }

  public void setUsers(List<UserWithRoleDTO> users) {
    this.users = users;
  }
}
