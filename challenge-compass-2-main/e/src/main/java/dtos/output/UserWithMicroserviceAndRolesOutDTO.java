package dtos.output;

import java.util.ArrayList;
import java.util.List;

public class UserWithMicroserviceAndRolesOutDTO {

    public UserWithMicroserviceAndRolesOutDTO(){

        microservices = new ArrayList<>();
    }

    private String name;
    private String email;

    private List<MicroserviceAndUserRoleOutDTO> microservices;

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

    public List <MicroserviceAndUserRoleOutDTO> getMicroservices() {
        return microservices;
    }

    public void setMicroservices(List<MicroserviceAndUserRoleOutDTO> microservices) {
        this.microservices = microservices;
    }

}
