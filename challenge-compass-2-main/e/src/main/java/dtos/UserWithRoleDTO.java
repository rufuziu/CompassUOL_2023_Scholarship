package dtos;

import enums.ERole;

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
