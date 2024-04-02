package br.com.ms.authandauto.dtos.user;

import br.com.ms.authandauto.dtos.microservice.MicroserviceDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
  public UserDTO() {
    microservices = new ArrayList<>();
  }

  private Long id;
  private String name;
  private String email;
  private List<MicroserviceDTO> microservices;

  public UserDTO(Long id, String name, String email, List<MicroserviceDTO> microservices) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.microservices = microservices;
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

  public List<MicroserviceDTO> getMicroservices() {
    return microservices;
  }

  public void setMicroservices(List<MicroserviceDTO> microservices) {
    this.microservices = microservices;
  }
}
