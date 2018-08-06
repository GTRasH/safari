package com.safari.apps.itraffic_system.Models;

import java.util.List;

public class LoginResponse {

    private Boolean ok;

    private String name;

    private List<String> roles = null;


    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
