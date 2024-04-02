package controllers;


import dtos.output.UserWithMicroserviceAndRolesOutDTO;
import dtos.MicroserviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proxy.MsEproxy;

import java.util.List;

@RestController
@RequestMapping("api/scholar")
public class ScholarController{
    @Autowired
    MsEproxy msEProxy;
    @GetMapping()
    public List<UserWithMicroserviceAndRolesOutDTO> getUsers()
    {
        return msEProxy.getAllUsersWithMicrosericesAndRoles();
    }

    @GetMapping("/microservice/{id-microservice}")
    public MicroserviceDTO getUser(
            @PathVariable("id-microservice")Long microserviceId)
    {

        return msEProxy.getMicroServiceAndUsers(microserviceId);
    }
}
