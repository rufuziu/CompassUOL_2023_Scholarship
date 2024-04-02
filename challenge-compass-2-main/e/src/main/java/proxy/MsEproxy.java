package proxy;

import dtos.MicroserviceDTO;
import dtos.output.UserWithMicroserviceAndRolesOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient
public interface MsEproxy {

    @GetMapping("/api/users")
    public List<UserWithMicroserviceAndRolesOutDTO>
    getAllUsersWithMicrosericesAndRoles();

    @GetMapping("/api/users/microservice/{id-micoservice}")
    public MicroserviceDTO getMicroServiceAndUsers(
            @PathVariable("id-microservice") Long id);
}
