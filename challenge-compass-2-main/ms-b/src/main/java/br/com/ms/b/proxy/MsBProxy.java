package br.com.ms.b.proxy;

import br.com.ms.b.dtos.MicroserviceDTO;
import br.com.ms.b.dtos.output.UserWithMicroservisesAndRolesOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-b", url = "localhost:8080")
public interface MsBProxy {
  @GetMapping("/api/users")
  public List<UserWithMicroservisesAndRolesOutDTO>
  getAllUsersWithMicroservicesAndRoles();

  @GetMapping("/api/users/microservice/{id-microservice}")
  public MicroserviceDTO getMicroserviceAndUsers(
          @PathVariable("id-microservice") Long id);
}
