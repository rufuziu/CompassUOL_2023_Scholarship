package br.com.ms.authandauto.dtos.userToMicroservice.input;

public class AuthInDTO {
  public AuthInDTO() {
  }
  private String emailUser;
  private String nameMicroservice;

  public String getEmailUser() {
    return emailUser;
  }

  public void setEmailUser(String emailUser) {
    this.emailUser = emailUser;
  }

  public String getNameMicroservice() {
    return nameMicroservice;
  }

  public void setNameMicroservice(String nameMicroservice) {
    this.nameMicroservice = nameMicroservice;
  }

  public AuthInDTO(String emailUser, String nameMicroservice) {
    this.emailUser = emailUser;
    this.nameMicroservice = nameMicroservice;
  }
}
