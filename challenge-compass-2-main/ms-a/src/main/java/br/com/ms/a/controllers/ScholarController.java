package br.com.ms.a.controllers;

import br.com.ms.a.dtos.MicroserviceDTO;
import br.com.ms.a.dtos.output.UserWithMicroservicesAndRolesOutDTO;
import br.com.ms.a.proxy.MsAProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/scholar")
public class ScholarController {
  @Autowired
  MsAProxy msAProxy;
  @GetMapping()
  public List<UserWithMicroservicesAndRolesOutDTO> getUsers()
  {
    return msAProxy.getAllUsersWithMicroservicesAndRoles();
  }

  @GetMapping("/microservice/{id-microservice}")
  public MicroserviceDTO getUser(
          @PathVariable("id-microservice")Long microserviceId)
  {

    return msAProxy.getMicroserviceAndUsers(microserviceId);
  }
}
