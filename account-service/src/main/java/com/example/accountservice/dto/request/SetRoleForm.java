package com.example.accountservice.dto.request;

import com.example.accountservice.model.Role;

import java.util.HashSet;
import java.util.Set;

public class SetRoleForm {
    private String username;
    private Set<String> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
