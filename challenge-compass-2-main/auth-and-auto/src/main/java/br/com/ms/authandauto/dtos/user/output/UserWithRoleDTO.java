package br.com.ms.authandauto.dtos.user.output;

import br.com.ms.authandauto.enums.ERole;

public class UserWithRoleDTO {
  public UserWithRoleDTO() {
  }
  private String name;
  private ERole role;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserWithRoleDTO(String name, ERole role) {
    this.name = name;
    this.role = role;
  }

  public ERole getRole() {
    return role;
  }

  public void setRole(ERole role) {
    this.role = role;
  }
}
