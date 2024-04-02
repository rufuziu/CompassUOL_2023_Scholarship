package br.com.ms.b.dtos;

import br.com.ms.b.enums.ERole;

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

  public ERole getRole() {
    return role;
  }

  public void setRole(ERole role) {
    this.role = role;
  }
}
