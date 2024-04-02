package br.com.ms.b.controllers;

import br.com.ms.b.dtos.MicroserviceDTO;
import br.com.ms.b.dtos.output.UserWithMicroservisesAndRolesOutDTO;
import br.com.ms.b.proxy.MsBProxy;
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
  MsBProxy msBProxy;
  @GetMapping()
  public List<UserWithMicroservisesAndRolesOutDTO> getUsers()
  {
    return msBProxy.getAllUsersWithMicroservicesAndRoles();
  }

  @GetMapping("/microservice/{id-microservice}")
  public MicroserviceDTO getUser(
          @PathVariable("id-microservice")Long microserviceId)
  {
    return msBProxy.getMicroserviceAndUsers(microserviceId);
  }
}
