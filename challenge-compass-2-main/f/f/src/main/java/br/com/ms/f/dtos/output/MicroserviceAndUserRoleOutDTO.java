package br.com.ms.f.dtos.output;

import br.com.ms.f.enums.ERole;

public class MicroserviceAndUserRoleOutDTO {
  public MicroserviceAndUserRoleOutDTO() {
  }
  private String name;
  private ERole roleUser;

  public String getName() {
    return name;
  }

  public ERole getRoleUser() {
    return roleUser;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRoleUser(ERole roleUser) {
    this.roleUser = roleUser;
  }

  public MicroserviceAndUserRoleOutDTO(String name, ERole roleUser) {
    this.name = name;
    this.roleUser = roleUser;
  }
}
