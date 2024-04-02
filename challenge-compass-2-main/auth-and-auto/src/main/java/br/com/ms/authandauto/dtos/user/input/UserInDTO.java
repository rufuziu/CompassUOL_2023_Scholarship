package br.com.ms.authandauto.dtos.user.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserInDTO {
  @NotBlank(message = "Name cannot be empty.")
  private String name;
  @Email(message = "Must be a valid email.")
  @NotBlank(message = "Email cannot be empty.")
  private String email;
  @NotBlank(message = "Password cannot be empty.")
  private String password;

  public UserInDTO() {
  }

  public UserInDTO(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
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
}
