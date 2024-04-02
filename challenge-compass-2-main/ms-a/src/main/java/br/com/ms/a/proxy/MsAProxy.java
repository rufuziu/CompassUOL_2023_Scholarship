package br.com.ms.a.proxy;

import br.com.ms.a.dtos.MicroserviceDTO;
import br.com.ms.a.dtos.output.UserWithMicroservicesAndRolesOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-a", url = "localhost:8080")
public interface MsAProxy {
  @GetMapping("/api/users")
  public List<UserWithMicroservicesAndRolesOutDTO>
  getAllUsersWithMicroservicesAndRoles();

  @GetMapping("/api/users/microservice/{id-microservice}")
  public MicroserviceDTO getMicroserviceAndUsers(
          @PathVariable("id-microservice") Long id);
}
